package com.jaredfwaya.onlineshop.dao;

import com.jaredfwaya.onlineshop.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends GenericDao<Product, Integer> {

	byte[] findImage(Integer id);

}
