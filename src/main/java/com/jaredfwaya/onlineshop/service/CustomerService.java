package com.jaredfwaya.onlineshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaredfwaya.onlineshop.dao.CustomerDao;
import com.jaredfwaya.onlineshop.model.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	public void saveCustomer(Customer customer){
		customer.setEnabled(true);
		customer.getAddress().setCustomer(customer);
		customerDao.saveCustomer(customer);
	}
	
	
}
