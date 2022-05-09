package com.jaredfwaya.onlineshop.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jaredfwaya.onlineshop.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl extends GenericJpaDao<Product, Integer> implements ProductDao {
	
	@PersistenceContext
	EntityManager manager;

	public ProductDaoImpl() {
	       super(Product.class);
	}
	
	@Override
	public byte[] findImage(Integer id) {
		String jpql = "select p from Product p where p.id = :id";
		Product product = this.manager
				.createQuery(jpql, Product.class)
				.setParameter("id", id)
				.getSingleResult();
		return product.getImage();
	}

}
