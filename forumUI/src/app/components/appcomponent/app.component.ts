import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from '../../services/authentication.service';
import { User } from '../../models/user.model';
import { AlertService } from '../../services/alert.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ForumUI';
  currentUser: any;

  constructor(
    private router: Router,
    private alertService: AlertService,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    console.log(this.currentUser);
    if (!this.currentUser) {
      console.log(this.currentUser);
      this.router.navigate(['/login']);
    }
  }

  logout() {
    this.currentUser = "";
    this.authenticationService.logout();
  }
}
