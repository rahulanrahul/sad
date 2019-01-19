package com.forum.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.forum.model.DiscussionModel;
import com.forum.service.ForumService;
import com.forum.dao.CategoryDao;
import com.forum.dao.ForumDao;

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

		} else {

		}

		return response;
	}

	private ResponseEntity<List<DiscussionModel>> searchOnCategory(String category) {
		int categoryid = categoryDao.getCategoryIdFromCategoryName(category.trim());
		List<DiscussionModel> listOfDiscussionModel = forumDao.searchOnCategory(categoryid);
		return new ResponseEntity<>(listOfDiscussionModel, HttpStatus.OK);
	}

	private ResponseEntity<List<DiscussionModel>> searchOnKeyword(String searchString) {
		// List<DiscussionModel> listOfDiscussionModel =
		// discussionDao.searchOnKeyword(searchString);
		// checkIfCollectionIsEmpty(listOfDiscussionModel);
		// return new ResponseEntity<>(listOfDiscussionModel, HttpStatus.OK);
		return null;
	}

	@Override
	public ResponseEntity<String> verifyUser(String userName, String password) {
		// long count=forumDao.verifyUserCount(userName.trim(),password);
		System.out.println();
		// if (count==1)
		String dbPass = forumDao.getDBPassword(userName.trim());
		if (dbPass.equals(password))
			return new ResponseEntity<>("User authentication successful.", HttpStatus.OK);
		else
			return new ResponseEntity<>("Invalid Username or Password. Please try again.", HttpStatus.NOT_FOUND);
	}
}
