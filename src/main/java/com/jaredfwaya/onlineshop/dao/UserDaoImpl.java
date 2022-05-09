package com.jaredfwaya.onlineshop.dao;


import com.jaredfwaya.onlineshop.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericJpaDao<User, String> implements UserDao {

	public UserDaoImpl() {
	       super(User.class);
	}

}
