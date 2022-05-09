package com.jaredfwaya.onlineshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaredfwaya.onlineshop.dao.UserDao;
import com.jaredfwaya.onlineshop.model.User;
import com.jaredfwaya.onlineshop.model.UserRole;

@Service("customUserDetailsService")
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
									throws UsernameNotFoundException {
		
		User user = userDao.findOne(username);
		if(user == null){
			throw new UsernameNotFoundException("Username not found");
		} 
		
		List<GrantedAuthority> authorities = getGrantedAuthority(
													user.getUserRoles());
		
		return new org.springframework.security.core.userdetails.User(
											 		user.getUsername(),
											 		user.getPassword(),
													user.isEnabled(),
													true, true, true, 
													authorities);
	}

	private List<GrantedAuthority> getGrantedAuthority(Set<UserRole> userRoles) {
		
		List<GrantedAuthority> setAuthorities = new ArrayList<GrantedAuthority>();
		for(UserRole userRole : userRoles){
			setAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return setAuthorities;
	}

}
