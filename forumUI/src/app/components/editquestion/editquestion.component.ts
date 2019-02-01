import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ForumService } from '../../services/forum.service';
import { AlertService } from '../../services/alert.service';
@Component({
  selector: 'app-editquestion',
  templateUrl: './editquestion.component.html',
  styleUrls: ['./editquestion.component.css']
})
export class EditquestionComponent implements OnInit {
  loading = false;
  submitted = false;
  questionId: any;
  questionDesc: any;
  editQuestionForm: FormGroup;
  constructor(private formBuilder: FormBuilder,
    private forumService: ForumService,
    private router: Router,
    private alertService: AlertService) {
  }

  get f() { return this.editQuestionForm.controls; }

  ngOnInit() {
    this.questionId = localStorage.getItem('questionId');
    this.questionDesc = localStorage.getItem('question');
    this.editQuestionForm = this.formBuilder.group({
      question: [this.questionDesc, Validators.required],
      questionId: [this.questionId],
    });

  }

  onSubmit() {
    this.submitted = true;
    if (this.editQuestionForm.invalid) {
      return;
    }
    this.loading = true;
    this.forumService.updateQuestion(this.questionId, this.editQuestionForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success("Question Updated Successfully", true);
          this.router.navigate(['/myquestions']);
        },
        error => {
          this.alertService.error("Question Updation Failed");
          this.loading = false;
          return;
        });
  }

}
