import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { ForumService } from '../../services/forum.service';
import { AlertService } from '../../services/alert.service';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-postquestion',
  templateUrl: './postquestion.component.html',
  styleUrls: ['./postquestion.component.css']
})
export class PostquestionComponent implements OnInit {
  loading = false;
  submitted = false;
  userId: any;
  postQuestionForm: FormGroup;
  categories = ['Food', 'Travel', 'Health', 'Academics', 'Accomodation', 'Others'];
  constructor(private formBuilder: FormBuilder,
    private forumService: ForumService,
    private router: Router,
    private alertService: AlertService) {
    this.userId = localStorage.getItem('currentUser');
    this.postQuestionForm = this.formBuilder.group({
      category: ['Food'],
      question: ['', Validators.required],
      userId: [this.userId]
    });
  }

  get f() { return this.postQuestionForm.controls; }

  ngOnInit() {
  }

  onSubmit() {
    this.submitted = true;
    if (this.postQuestionForm.invalid) {
      return;
    }
    this.loading = true;
    this.forumService.postQuestion(this.postQuestionForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Question Posted successful', true);
          this.router.navigate(['/dashboard']);
        },
        error => {
          this.alertService.error("Posting Failed");
          this.loading = false;
          return;
        });
  }

}
