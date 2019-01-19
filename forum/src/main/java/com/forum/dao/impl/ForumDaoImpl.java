package com.forum.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forum.dao.ForumDao;
import com.forum.entity.AnswersEntity;
import com.forum.entity.QuestionsEntity;
import com.forum.entity.UserDetailsEntity;
import com.forum.model.DiscussionModel;

@Repository
public class ForumDaoImpl implements ForumDao {
	@Autowired
	SessionFactory sessionFactory;

	public List<DiscussionModel> searchOnCategory(Integer categoryId) {

		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<QuestionsEntity> criteriaQuery = criteriaBuilder.createQuery(QuestionsEntity.class);
		Root<QuestionsEntity> questionsRoot = criteriaQuery.from(QuestionsEntity.class);
		criteriaQuery.select(questionsRoot);
		criteriaQuery.where(criteriaBuilder.equal(questionsRoot.get("questionCategoryId"), categoryId),
				criteriaBuilder.equal(questionsRoot.get("isQuestionActive"), true));
		List<QuestionsEntity> listOfQuestionsWithGivenCategory = session.createQuery(criteriaQuery).getResultList();
		return getListOfDiscussionModelFromQuestionsEntity(listOfQuestionsWithGivenCategory);

	}

	private List<DiscussionModel> getListOfDiscussionModelFromQuestionsEntity(
			List<QuestionsEntity> listOfQuestionsWithGivenCategory) {
		List<DiscussionModel> listOfDiscussionModel = new ArrayList<>();

		listOfQuestionsWithGivenCategory.forEach(questionEntity -> {
			DiscussionModel discussionModel = new DiscussionModel();
			discussionModel.setQuestionId(questionEntity.getQuestionId());
			discussionModel.setQuestion(questionEntity.getQuestionDescription());
			discussionModel.setUserId(questionEntity.getQuestionPostedByUserId());
			discussionModel.setDiscussionThreadActive(questionEntity.isDiscussionThreadActive());
			List<AnswersEntity> listOfAnswers = getListOfAnswersBasedOnQuestionId(questionEntity.getQuestionId());
			discussionModel.setAnswer(listOfAnswers);
			discussionModel.setCategoryId(questionEntity.getQuestionCategoryId());
			discussionModel.setGroupId(questionEntity.getQuestionGroupId());
			discussionModel.setTimestamp(questionEntity.getQuestionCreationDateTime());
			listOfDiscussionModel.add(discussionModel);
		});
		return listOfDiscussionModel;
	}

	private List<AnswersEntity> getListOfAnswersBasedOnQuestionId(Integer questionId) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<AnswersEntity> criteriaQuery = criteriaBuilder.createQuery(AnswersEntity.class);
		Root<AnswersEntity> questionsRoot = criteriaQuery.from(AnswersEntity.class);
		criteriaQuery.select(questionsRoot);
		criteriaQuery.where(criteriaBuilder.equal(questionsRoot.get("questionId"), questionId),
				criteriaBuilder.equal(questionsRoot.get("answerIsActive"), true));
		return session.createQuery(criteriaQuery).getResultList();
	}

//	@Override
//	public int verifyUserCount(String userName, String password) {
//		Session session = sessionFactory.getCurrentSession();
////		int count = (Integer) session.createQuery(
////				"select count(1) from user_details where user_name='" + userName + "' and password='" + password + "'")
////				.getSingleResult();
////		Root<UserDetailsEntity> questionsRoot = criteriaQuery.from(UserDetailsEntity.class);
////		criteriaQuery.multiselect(criteriaBuilder.count(questionsRoot));
////		criteriaQuery.where(criteriaBuilder.equal(questionsRoot.get("userName"), userName),
////				criteriaBuilder.equal(questionsRoot.get("password"), password));		
////		return session.createQuery(criteriaQuery).getSingleResult();
//		
//		@SuppressWarnings("rawtypes")
//		Query query = session
//				.createQuery(
//						"select count(1) from UserDetailsEntity ud where ud.userName=:USERNAME and ud.password=:PASSWORD")
//				.setParameter("USERNAME", userName).setParameter("PASSWORD", password);
//		
//		int count =  query.getFirstResult();
//		System.out.println("Output of query"+count);
//		return count;
//	}

	@Override
	public String getDBPassword(String userName) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<UserDetailsEntity> criteriaQuery = criteriaBuilder.createQuery(UserDetailsEntity.class);
		Root<UserDetailsEntity> usersRoot = criteriaQuery.from(UserDetailsEntity.class);
		criteriaQuery.select(usersRoot);
		criteriaQuery.where(criteriaBuilder.equal(usersRoot.get("userName"), userName));
		return session.createQuery(criteriaQuery).getSingleResult().getPassword();
	}

}
