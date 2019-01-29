import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-myprofile',
  templateUrl: './myprofile.component.html',
  styleUrls: ['./myprofile.component.css']
})
export class MyprofileComponent implements OnInit {
  userId: any;
  userForm: FormGroup;
  firstName: any;
  lastName: any;
  emailId: any;
  phoneNumber: any;
  isDisabled: boolean = true;
  constructor(private userSerive: UserService,
              private formBuilder: FormBuilder,
              private router: Router
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
    this.userSerive.getUser(this.userId)
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
            lastName: [this.lastName, Validators.required],
            emailId: [this.emailId, Validators.required],
            phoneNumber: [this.phoneNumber, Validators.required],
          });
        },
        error => {
          console.log(error);
          return;
        });
  }

  onSubmit() {
    this.router.navigate(['/editprofile']);
  }

}
