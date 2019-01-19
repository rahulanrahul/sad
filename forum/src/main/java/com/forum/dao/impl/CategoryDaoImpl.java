package com.forum.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forum.dao.CategoryDao;
import com.forum.entity.CategoryEntity;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	SessionFactory sessionFactory;

	public int getCategoryIdFromCategoryName(String categoryName)   {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<CategoryEntity> criteriaQuery = criteriaBuilder.createQuery(CategoryEntity.class);
		Root<CategoryEntity> questionsRoot = criteriaQuery.from(CategoryEntity.class);
		criteriaQuery.select(questionsRoot);
		criteriaQuery.where(criteriaBuilder.equal(questionsRoot.get("categoryName"), categoryName));
		Integer categoryId = session.createQuery(criteriaQuery).getSingleResult().getCategoryId();
		return categoryId;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
