package com.jaredfwaya.onlineshop.dao;

import com.jaredfwaya.onlineshop.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends GenericDao<User, String>{
	
}
