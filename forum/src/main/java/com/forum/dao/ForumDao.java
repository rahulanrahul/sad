package com.forum.dao;

import java.util.List;

import com.forum.model.DiscussionModel;

public interface ForumDao {

	public List<DiscussionModel> searchOnCategory(Integer categoryId) ;

	public String getDBPassword(String userName);
}
