package com.forum.dao;

import java.util.List;

import com.forum.entity.AnswersEntity;
import com.forum.entity.QuestionsEntity;
import com.forum.entity.UserDetailsEntity;
import com.forum.model.DiscussionModel;

public interface ForumDao {

	public List<DiscussionModel> searchOnCategory(Integer categoryId);

	public List<DiscussionModel> getAnswersByUserId(Integer userId);

	public void postQuestions(QuestionsEntity questionEntity);

	public void postAnswers(AnswersEntity answersEntity);

	public QuestionsEntity getQuestionEntityFromQuestionId(int questionId);

	public List<DiscussionModel> searchOnKeyword(String searchString);

	public List<DiscussionModel> searchOnUserId(Integer userId);

	public void editQuestions(int questionId, Integer categoryId, String question);

	public void editAnswers(int questionId, String answer);

	public void deleteQuestion(Integer questionId);

	public void deleteAnswer(Integer answerId);

	public void closeQuestions(Integer questionId);

	public UserDetailsEntity verifyUserCount(String userName, String password);
}
