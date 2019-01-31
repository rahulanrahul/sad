import { Component, OnInit } from '@angular/core';

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
    private alertService: AlertService) { }

  ngOnInit() {
    this.userId = localStorage.getItem('currentUser');
    this.forumService.getDiscussionsByUserId(this.userId)
      .subscribe(
        data => {
          this.discussions = data;
          console.log(this.discussions);
        },
        error => {
          this.alertService.error("No Questions to Display");
          return;
        }
      );
  }

  onEdit(userId: any) {
    console.log(userId);
  }

  onClose(userId: any) {
    console.log(userId);
  }

  onDelete(userId: any) {
    console.log(userId);
  }

}
