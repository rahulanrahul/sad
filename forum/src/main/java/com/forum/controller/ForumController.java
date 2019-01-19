package com.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forum.service.ForumService;
import com.forum.model.AnswerModel;
import com.forum.model.DiscussionModel;
@RestController
@RequestMapping("/forum")
public class ForumController {
	@Autowired
	ForumService forumService;

	@GetMapping("/search")
	public ResponseEntity<List<DiscussionModel>> detailedSearch(@RequestParam(required = false) String searchString,
			@RequestParam(required = false) String category, @RequestParam(required = false) Integer userId) {
		return forumService.getDiscussions(searchString, category, userId);
	}

	@GetMapping("/login-verification")
	public ResponseEntity<String> verifyUser(@RequestParam (required = false)  String userName, @RequestParam (required = false)  String password) {
		return forumService.verifyUser(userName, password);
	}
	
	@PostMapping("/post-question")
	public ResponseEntity<String> postQuestions(@RequestBody DiscussionModel questionModel) {
		return forumService.postQuestion(questionModel);
	}

	@PostMapping("/answer-question")
	public ResponseEntity<String> answerQuestions(@RequestBody AnswerModel answerModel) {
		return forumService.answerQuestion(answerModel);
	}
}
