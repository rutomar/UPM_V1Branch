package com.sogeti.upm.dao;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sogeti.upm.data.User;
import com.sogeti.upm.data.UserOTP;


@Repository
@Transactional
public class UPMDaoImpl {

	@PersistenceContext
	private EntityManager em;

	public User getUserById(String loginId){
		return (User)em.find(User.class, loginId);
	}
	
	public UserOTP getUserOTP(long id){
		return (UserOTP)em.find(UserOTP.class, id);
	}

	public void createUserOTP(UserOTP userOTP) {
		em.persist(userOTP);
	}
	

	
}
