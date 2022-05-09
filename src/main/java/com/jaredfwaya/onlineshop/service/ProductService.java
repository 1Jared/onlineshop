package com.jaredfwaya.onlineshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaredfwaya.onlineshop.dao.ProductDao;
import com.jaredfwaya.onlineshop.model.Product;

@Service
public class ProductService {

	@Autowired
	ProductDao productDao;
	
	public void saveProduct(Product product) {
		productDao.save(product);
	}

	public void delete(Integer id) {
		productDao.delete(id);
	}

	public void updateProduct(Product product) {
		productDao.update(product);
	}
	
}
