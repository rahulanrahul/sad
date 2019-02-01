import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { ForumService } from '../../services/forum.service';
import { AlertService } from '../../services/alert.service';
import { Answer} from '../../models/Answer.model'

@Component({
  selector: 'app-myanswers',
  templateUrl: './myanswers.component.html',
  styleUrls: ['./myanswers.component.css']
})
export class MyanswersComponent implements OnInit {
  discussions: [];
  userId: any;
  answerModel:Answer;
  constructor(private forumService: ForumService,
    private alertService: AlertService,
    private router: Router) { }

  ngOnInit() {
    this.userId = localStorage.getItem('currentUser');
    this.forumService.getAnswersByUserId(this.userId)
      .subscribe(
        data => {
          this.discussions = data;
          console.log(this.discussions);
        },
        error => {
          this.alertService.error("No Answers to Display");
          return;
        }
      );
  }

  onDelete(answerId: any) {
    console.log(answerId);
    this.forumService.deleteAnswer(answerId)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success("Answer Deleted Successfully", true);
          this.discussions=null;
          this.ngOnInit();
        },
        error => {
          this.alertService.error("Answer Deletion Failed");
          return;
        });
  }

}
