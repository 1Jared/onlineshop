package com.jaredfwaya.onlineshop.dao;

import com.jaredfwaya.onlineshop.model.Customer;

public interface CustomerDao extends GenericDao<Customer, Integer> {

	void saveCustomer(Customer customer);
}
