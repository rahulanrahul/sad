package com.forum.dao.impl;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.forum.dao.UserDao;
import com.forum.entity.UserDetailsEntity;
import com.forum.model.UserDetailsModel;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	SessionFactory sessionFactory;

	public UserDetailsModel getUserDetails(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		UserDetailsEntity userDetailsEntity = session.get(UserDetailsEntity.class, userId);
		UserDetailsModel userDetailsModel = new UserDetailsModel();
		userDetailsModel.setUserId(userId);
		userDetailsModel.setFirstName(userDetailsEntity.getFirstName());
		userDetailsModel.setLastName(userDetailsEntity.getLastName());
		userDetailsModel.setEmailId(userDetailsEntity.getEmailId());
		userDetailsModel.setPhoneNumber(userDetailsEntity.getPhoneNumber());
		return userDetailsModel;
	}

	public void postUserDetails(UserDetailsEntity userEntity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(userEntity);
	}

	public void editUserDetails(Integer userId, String firstName, String lastName, String emailId, String phoneNumber) {
		Session session = sessionFactory.getCurrentSession();
		UserDetailsEntity existingUserDetailsEntity = getUserDetailsEntity(userId, session);
		existingUserDetailsEntity.setFirstName(firstName);
		existingUserDetailsEntity.setLastName(lastName);
		existingUserDetailsEntity.setEmailId(emailId);
		existingUserDetailsEntity.setPhoneNumber(phoneNumber);
		existingUserDetailsEntity.setUserModificationDateTime(new Timestamp(System.currentTimeMillis()));
		session.update(existingUserDetailsEntity);
	}

	public void deleteUserDetails(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		UserDetailsEntity userDetailsEntity = getUserDetailsEntity(userId, session);
		userDetailsEntity.setUserIsActive(false);
		session.update(userDetailsEntity);
	}

	private UserDetailsEntity getUserDetailsEntity(Integer userId, Session session) {
		UserDetailsEntity userDetailsEntity = session.get(UserDetailsEntity.class, userId);
		return userDetailsEntity;
	}
	
	@Override
	public UserDetailsEntity verifyUser(String userName, String password) {
		Session session = sessionFactory.getCurrentSession();
		try {
		UserDetailsEntity user = (UserDetailsEntity) session.createQuery(
				"from UserDetailsEntity ud where ud.userName='" + userName + "'and ud.password='" + password + "' and ud.userIsActive='"+true+"'").getSingleResult();
		return user;
		}
		catch (Exception e)
		{
			UserDetailsEntity user =new UserDetailsEntity();
			return user;
		}		
	}
}
