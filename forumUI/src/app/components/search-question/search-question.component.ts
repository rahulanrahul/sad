import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ForumService } from '../../services/forum.service';
import { ShowDiscussionsComponent } from '../show-discussions/show-discussions.component';

@Component({
  selector: 'app-search-question',
  templateUrl: './search-question.component.html',
  styleUrls: ['./search-question.component.css']
})
export class SearchQuestionComponent implements OnInit {
  searchStringForm: FormGroup;
  submitted = false;
  discusssionResult: any[];
  constructor(private router:Router,
    private formBuilder: FormBuilder,
    private forumService : ForumService
    ) { }
  loading = false;
  ngOnInit() {
    this.searchStringForm = this.formBuilder.group({
      searchString: ['', Validators.required]
    });
  }

  get f() { return this.searchStringForm.controls; }

  onSubmitSearchString(){
    this.submitted = true;

    if (this.searchStringForm.invalid) {
      return;
    }
    this.loading = true;
    console.log(this.searchStringForm.get('searchString').value);
    this.forumService.getDiscussionsSearchString(this.searchStringForm.get('searchString').value)
      .subscribe(
      data => {
        //this.router.navigate(['/show-discussion']);
        //console.log(data[0].question);
        this.discusssionResult=data;
        console.log(this.discusssionResult)
        //showDiscussionsComponent(data)
      });
  }
}
