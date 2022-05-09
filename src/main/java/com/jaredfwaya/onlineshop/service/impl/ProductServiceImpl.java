package com.jaredfwaya.onlineshop.service.impl;

import com.jaredfwaya.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaredfwaya.onlineshop.dao.ProductDao;
import com.jaredfwaya.onlineshop.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	
	@Override
	public void saveProduct(Product product) {
		productDao.save(product);
	}

	@Override
	public void delete(Integer id) {
		productDao.delete(id);
	}

	@Override
	public void updateProduct(Product product) {
		productDao.update(product);
	}
	
}
