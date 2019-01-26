package com.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forum.model.AnswerModel;
import com.forum.model.DiscussionModel;
import com.forum.model.UserDetailsModel;
import com.forum.service.ForumService;

@RestController
@RequestMapping("/forum")
public class ForumController {
	@Autowired
	ForumService forumService;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/search")
	public ResponseEntity<List<DiscussionModel>> detailedSearch(@RequestParam(required = false) String searchString,
			@RequestParam(required = false) String category, @RequestParam(required = false) Integer userId) {
		return forumService.getDiscussions(searchString, category, userId);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/my-answers")
	public ResponseEntity<List<DiscussionModel>> getAnswersByUserId(@RequestParam(required = false) Integer userId) {
		return forumService.getAnswerByUserId(userId);
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login-validation")
	public ResponseEntity<String> validateLogin(@RequestBody UserDetailsModel userModel) {
		return forumService.validateUser(userModel);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/post-question")
	public ResponseEntity<String> postQuestions(@RequestBody DiscussionModel questionModel) {
		return forumService.postQuestion(questionModel);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/answer-question")
	public ResponseEntity<String> answerQuestions(@RequestBody AnswerModel answerModel) {
		return forumService.answerQuestion(answerModel);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/edit-question")
	public ResponseEntity<String> editQuestions(@RequestBody DiscussionModel questionModel) {
		return forumService.editQuestion(questionModel);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/edit-answer")
	public ResponseEntity<String> editAnswers(@RequestBody AnswerModel answerModel) {
		return forumService.editAnswer(answerModel);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteQuestionsOrAnswers(@RequestParam(required = false) Integer questionId,
			@RequestParam(required = false) Integer answerId) {
		return forumService.delete(questionId, answerId);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/close-question")
	public ResponseEntity<String> closeQuestions(@RequestBody DiscussionModel questionModel) {
		return forumService.closeQuestion(questionModel);
	}

}
