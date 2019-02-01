import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { ForumService } from '../../services/forum.service';
import { AlertService } from '../../services/alert.service';

@Component({
  selector: 'app-myquestions',
  templateUrl: './myquestions.component.html',
  styleUrls: ['./myquestions.component.css']
})
export class MyquestionsComponent implements OnInit {
  discussions: [];
  userId: any;
  constructor(private forumService: ForumService,
    private alertService: AlertService,
    private router: Router) { }

  ngOnInit() {
    this.userId = localStorage.getItem('currentUser');
    this.forumService.getDiscussionsByUserId(this.userId)
      .subscribe(
        data => {
          this.discussions = data;
        },
        error => {
          this.alertService.error("No Questions to Display");
          return;
        }
      );
  }

  onEdit(questionId: any, question: any) {
    localStorage.setItem('questionId', questionId);
    localStorage.setItem('question', question);
    this.router.navigate(['/editquestion']);
  }

  onClose(questionId: any) {
    this.forumService.closeQuestion(questionId)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success("Question Closed Successfully", true);
          this.ngOnInit();
        },
        error => {
          this.alertService.error("Question Closure Failed");
          return;
        });
  }

  onDelete(questionId: any) {
    this.forumService.deleteQuestion(questionId)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success("Question Deleted Successfully", true);
          this.ngOnInit();
        },
        error => {
          this.alertService.error("Question Deletion Failed");
          return;
        });
  }
}
