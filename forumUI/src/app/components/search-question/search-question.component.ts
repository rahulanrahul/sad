import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ForumService } from '../../services/forum.service';
import { AlertService } from '../../services/alert.service';
import { first } from 'rxjs/operators';
import { Answer} from '../../models/answer.model'


@Component({
  selector: 'app-search-question',
  templateUrl: './search-question.component.html',
  styleUrls: ['./search-question.component.css']
})
export class SearchQuestionComponent implements OnInit {
  searchStringForm: FormGroup;
  searchCategoryForm: FormGroup;
  answerForm:FormGroup;
  answerModel:Answer
  userId: any;
  submitted = false;
  submittedCategory = false;
  loadingCategory = false;
  discusssionResult: any[];
  categories = ['Food', 'Travel', 'Health', 'Academics', 'Accomodation', 'Others'];
  
  constructor(private router:Router,
    private formBuilder: FormBuilder,
    private forumService : ForumService,
    private alertService: AlertService
    ) { 
      this.userId=localStorage.getItem('currentUser');     
      this.searchCategoryForm = this.formBuilder.group({
        category: ['Food'] 
      });
      this.answerForm = this.formBuilder.group({
        answer: ['',Validators.required] ,
        userId: [this.userId],
        questionId:0
      });
    }
  
  ngOnInit() {
    this.searchStringForm = this.formBuilder.group({
      searchString: ['', Validators.required]
    });
  }

  get f() { return this.searchStringForm.controls; }

  get fanswer() { return this.answerForm.controls; }

  onSubmitSearchString(){
    this.submitted = true;

    if (this.searchStringForm.invalid) {
      return;
    }

    this.forumService.getDiscussionsSearchString(this.searchStringForm.get('searchString').value)
      .subscribe(
      data => {
        this.discusssionResult=data;
      });
  }
  onSubmitCategory(){
    //this.submittedCategory = true;

    if (this.searchCategoryForm.invalid) {
      return;
    }
    this.forumService.getDiscussionsCategory(this.searchCategoryForm.get('category').value)
      .subscribe(
      data => {
        this.discusssionResult=data;
      });
   console.log(this.searchCategoryForm.get('category').value)
  }

  onPostAnswer(questionId:any){
    this.submitted = true;
    if (this.answerForm.invalid) {
      return;
    }
    console.log(questionId,this.userId,this.answerForm.get('answer').value);
    this.answerModel={
      answer:this.answerForm.get('answer').value,
      questionId:questionId,
      userId:this.userId
    };
    this.forumService.postAnswer(this.answerModel)
    .pipe(first())
    .subscribe(
      data=>{
        this.alertService.success('Answer added in system successfully')
        console.log(data)
      },
      error => {
        this.alertService.error("Answer not added");
        //this.loading = false;
        return;
      }
    )
  }
}
