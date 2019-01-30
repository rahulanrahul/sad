import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { AlertService } from '../../services/alert.service';


@Component({
  selector: 'app-editprofile',
  templateUrl: './editprofile.component.html',
  styleUrls: ['./editprofile.component.css']
})
export class EditprofileComponent implements OnInit {

  userId: any;
  userForm: FormGroup;
  firstName: any;
  lastName: any;
  emailId: any;
  phoneNumber: any;
  isDisabled: boolean = false;
  constructor(private userService: UserService,
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService
  ) {
    this.userForm = this.formBuilder.group({
      firstName: [{ value: '', disabled: this.isDisabled }, Validators.required],
      lastName: [{ value: '', disabled: this.isDisabled }, Validators.required],
      emailId: [{ value: '', disabled: this.isDisabled }, Validators.required],
      phoneNumber: [{ value: '', disabled: this.isDisabled }, Validators.required],
    });
  }

  ngOnInit() {
    this.userId = localStorage.getItem('currentUser');
    this.userService.getUser(this.userId)
      .pipe(first())
      .subscribe(
        data => {
          console.log(data);
          this.firstName = data.firstName;
          this.lastName = data.lastName;
          this.emailId = data.emailId;
          this.phoneNumber = data.phoneNumber;
          this.userForm = this.formBuilder.group({
            firstName: [this.firstName],
            lastName: [this.lastName],
            emailId: [this.emailId],
            phoneNumber: [this.phoneNumber],
          });
        },
        error => {
          console.log(error);
          return;
        });
  }

  deleteAccount(){
    this.userId = localStorage.getItem('currentUser');
    this.userService.deleteUser(this.userId)
    .pipe(first())
      .subscribe(
        data => {
          this.alertService.success("Profile Deleted Successfully", true);
        },
        error => {
          return;
        });
    this.authenticationService.logout();
  }  

  onSubmit() {
    this.userId = localStorage.getItem('currentUser');
    this.userService.updateUser(this.userId, this.userForm.value)
    .pipe(first())
      .subscribe(
        data => {
          this.alertService.success("Profile Updated Successfully", true);
          this.router.navigate(['/myprofile']);
        },
        error => {
          return;
        });
  }

}
