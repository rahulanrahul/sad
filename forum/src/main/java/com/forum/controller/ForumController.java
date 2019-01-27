package com.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forum.model.AnswerModel;
import com.forum.model.DiscussionModel;
import com.forum.service.ForumService;

@RestController
@RequestMapping("/forum")
public class ForumController {
	@Autowired
	ForumService forumService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/questions")
	public ResponseEntity<String> postQuestions(@RequestBody DiscussionModel questionModel) {
		return forumService.postQuestion(questionModel);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/questions/{questionId}")
	public ResponseEntity<String> editQuestions(@PathVariable int questionId, @RequestBody DiscussionModel questionModel) {
		questionModel.setQuestionId(questionId);
		return forumService.editQuestion(questionModel);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/questions/close-thread/{questionId}")
	public ResponseEntity<String> closeQuestions(@PathVariable int questionId) {
		return forumService.closeQuestion(questionId);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/questions/{questionId}")
	public ResponseEntity<String> deleteQuestions(@PathVariable int questionId) {
		//return forumService.delete(questionId, answerId);
		return forumService.deleteQuestion(questionId);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/questions")
	public ResponseEntity<List<DiscussionModel>> detailedSearch(@RequestParam(required = false) String searchString,
			@RequestParam(required = false) String category, @RequestParam(required = false) Integer userId) {
		return forumService.getDiscussions(searchString, category, userId);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/answers")
	public ResponseEntity<String> answerQuestions(@RequestBody AnswerModel answerModel) {
		return forumService.answerQuestion(answerModel);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/answers/{answerId}")
	public ResponseEntity<String> editAnswers(@PathVariable int answerId, @RequestBody AnswerModel answerModel) {
		answerModel.setAnswerId(answerId);
		return forumService.editAnswer(answerModel);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/answers/{answerId}")
	public ResponseEntity<String> deleteAnswers(@PathVariable int answerId) {
		return forumService.deleteAnswer(answerId);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/answers")
	public ResponseEntity<List<DiscussionModel>> getAnswersByUserId(@RequestParam Integer userId) {
		return forumService.getAnswerByUserId(userId);
	}

}
