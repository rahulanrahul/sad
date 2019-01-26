package com.forum.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.forum.model.AnswerModel;
import com.forum.model.DiscussionModel;
import com.forum.model.UserDetailsModel;

public interface ForumService {

	public ResponseEntity<List<DiscussionModel>> getDiscussions(String searchString, String category, Integer userId);

	public ResponseEntity<String> postQuestion(DiscussionModel questionModel);

	public ResponseEntity<String> answerQuestion(AnswerModel answerModel);

	public ResponseEntity<String> editQuestion(DiscussionModel questionModel);

	public ResponseEntity<String> editAnswer(AnswerModel answerModel);

	public ResponseEntity<String> delete(Integer questionId, Integer answerId);

	public ResponseEntity<String> closeQuestion(DiscussionModel questionModel);

	public ResponseEntity<List<DiscussionModel>> getAnswerByUserId(Integer userId);

	public ResponseEntity<String> validateUser(UserDetailsModel userModel);
}
