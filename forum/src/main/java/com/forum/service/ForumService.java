package com.forum.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.forum.model.AnswerModel;
import com.forum.model.DiscussionModel;

public interface ForumService {

	public ResponseEntity<List<DiscussionModel>> getDiscussions(String searchString, String category, Integer userId);

	public ResponseEntity<String> verifyUser(String userName, String password);

	public ResponseEntity<String> postQuestion(DiscussionModel discussionModel);

	public ResponseEntity<String> answerQuestion(AnswerModel answerModel);
}
