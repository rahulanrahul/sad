package com.forum.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.forum.dao.CategoryDao;
import com.forum.dao.ForumDao;
import com.forum.entity.AnswersEntity;
import com.forum.entity.QuestionsEntity;
import com.forum.model.AnswerModel;
import com.forum.model.DiscussionModel;
import com.forum.model.UserDetailsModel;
import com.forum.service.ForumService;

@Service
@Transactional
public class ForumServiceImpl implements ForumService {

	@Autowired
	CategoryDao categoryDao;
	@Autowired
	ForumDao forumDao;

	public ResponseEntity<List<DiscussionModel>> getDiscussions(String searchString, String category, Integer userId) {
		ResponseEntity<List<DiscussionModel>> response = null;
		if (StringUtils.isNotEmpty(searchString) && StringUtils.isEmpty(category) && ObjectUtils.isEmpty(userId)) {
			response = searchOnKeyword(searchString);
		} else if (StringUtils.isEmpty(searchString) && StringUtils.isNotEmpty(category)
				&& ObjectUtils.isEmpty(userId)) {
			response = searchOnCategory(category);

		} else if (StringUtils.isEmpty(searchString) && StringUtils.isEmpty(category) && !ObjectUtils.isEmpty(userId)) {
			response = searchOnUserId(userId);
		}

		return response;
	}

	private ResponseEntity<List<DiscussionModel>> searchOnCategory(String category) {
		int categoryid = categoryDao.getCategoryIdFromCategoryName(category.trim());
		List<DiscussionModel> listOfDiscussionModel = forumDao.searchOnCategory(categoryid);
		return new ResponseEntity<>(listOfDiscussionModel, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> postQuestion(DiscussionModel questionModel) {
		QuestionsEntity questionEntity = new QuestionsEntity();
		questionEntity.setQuestionCategoryId(categoryDao.getCategoryIdFromCategoryName(questionModel.getCategory()));
		questionEntity.setQuestionCreationDateTime(new Timestamp(System.currentTimeMillis()));
		questionEntity.setQuestionDescription(questionModel.getQuestion().trim());
		questionEntity.setQuestionActive(true);
		questionEntity.setDiscussionThreadActive(true);
		questionEntity.setQuestionPostedByUserId(questionModel.getUserId());
		forumDao.postQuestions(questionEntity);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> editQuestion(DiscussionModel questionModel) {
		int questionId = questionModel.getQuestionId();
		int questionCategory = categoryDao.getCategoryIdFromCategoryName(questionModel.getCategory());
		String questionDescription = questionModel.getQuestion();
		forumDao.editQuestions(questionId, questionCategory, questionDescription);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> answerQuestion(AnswerModel answerModel) {
		AnswersEntity answersEntity = new AnswersEntity();
		answersEntity.setAnswerDescription(answerModel.getAnswer().trim());
		answersEntity.setAnswerIsActive(true);
		answersEntity.setQuestionId(answerModel.getQuestionId());
		answersEntity.setAnswerPostedByUserId(answerModel.getUserId());
		answersEntity.setAnswerDateTime(new Timestamp(System.currentTimeMillis()));
		answersEntity.setAnswerPostedByUserId(answerModel.getUserId());
		if ((!forumDao.getQuestionEntityFromQuestionId(answerModel.getQuestionId()).isDiscussionThreadActive())
				|| (!forumDao.getQuestionEntityFromQuestionId(answerModel.getQuestionId()).isQuestionActive())) {
			return new ResponseEntity<>("Question Inactive", HttpStatus.NO_CONTENT);
		} else {
			forumDao.postAnswers(answersEntity);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}

	@Override
	public ResponseEntity<String> editAnswer(AnswerModel answerModel) {
		int answerId = answerModel.getAnswerId();
		String answerDescription = answerModel.getAnswer();
		forumDao.editAnswers(answerId, answerDescription);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> delete(Integer questionId, Integer answerId) {
		if (!(ObjectUtils.isEmpty(questionId))) {
			forumDao.deleteQuestion(questionId);
		} else {
			forumDao.deleteAnswer(answerId);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> closeQuestion(DiscussionModel questionModel) {
		int questionId = questionModel.getQuestionId();
		forumDao.closeQuestions(questionId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public ResponseEntity<List<DiscussionModel>> getAnswerByUserId(Integer userId) {
		List<DiscussionModel> listOfDiscussionModel = forumDao.getAnswersByUserId(userId);
		if (CollectionUtils.isEmpty(listOfDiscussionModel)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(listOfDiscussionModel, HttpStatus.OK);
		}
	}

	private ResponseEntity<List<DiscussionModel>> searchOnUserId(Integer userId) {
		List<DiscussionModel> listOfDiscussionModel = forumDao.searchOnUserId(userId);
		if (CollectionUtils.isEmpty(listOfDiscussionModel)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(listOfDiscussionModel, HttpStatus.OK);
		}
	}

	private ResponseEntity<List<DiscussionModel>> searchOnKeyword(String searchString) {
		List<DiscussionModel> listOfDiscussionModel = forumDao.searchOnKeyword(searchString);
		if (CollectionUtils.isEmpty(listOfDiscussionModel)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(listOfDiscussionModel, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<String> verifyUser(String userName, String password) {
		String result = forumDao.verifyUserCount(userName.trim(), password);
		if (result.equals("Success"))
			return new ResponseEntity<>("User authentication successful.", HttpStatus.OK);
		else
			return new ResponseEntity<>("User authentication failed. Invalid Username or Password. Please try again.",
					HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<String> validateUser(UserDetailsModel userModel) {
		String userName = userModel.getUserName();
		String password = userModel.getPassword();
		String result = forumDao.verifyUserCount(userName, password);
		if (result.equals("Success"))
			return new ResponseEntity<>("User authentication successful.", HttpStatus.OK);
		else
			return new ResponseEntity<>("User authentication failed. Invalid Username or Password. Please try again.",
					HttpStatus.NOT_FOUND);
	}
}
