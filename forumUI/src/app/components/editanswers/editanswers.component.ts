import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ForumService } from '../../services/forum.service';
import { AlertService } from '../../services/alert.service';

@Component({
  selector: 'app-editanswers',
  templateUrl: './editanswers.component.html',
  styleUrls: ['./editanswers.component.css']
})
export class EditanswersComponent implements OnInit {
  loading = false;
  submitted = false;
  answerId: any;
  answerDesc: string;
  editAnswerForm: FormGroup;
  constructor(private formBuilder: FormBuilder,
    private forumService: ForumService,
    private router: Router,
    private alertService: AlertService) {
  }
  
  get f() { return this.editAnswerForm.controls; }

  ngOnInit() {
    this.answerId = localStorage.getItem('answerId');
    this.answerDesc = localStorage.getItem('answerDesc');
    this.editAnswerForm = this.formBuilder.group({
      answer: [this.answerDesc, Validators.required],
      answerId: [this.answerId],
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.editAnswerForm.invalid) {
      return;
    }
    this.loading = true;
    this.forumService.updateAnswer(this.answerId, this.editAnswerForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success("Answer Updated Successfully", true);
          this.router.navigate(['/myanswers']);
        },
        error => {
          this.alertService.error("Answer Updation Failed");
          this.loading = false;
          return;
        });
  }

}
