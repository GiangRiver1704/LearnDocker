package com.example.demo.dao;

import java.io.IOException;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Form.productForm;
import com.example.demo.entity.product;
import com.example.demo.model.productInfor;

@Transactional
@Repository
public class productDao {
	@PersistenceContext
	EntityManager entityManager;
	
	public List<product> listProduct(){
		List<product> listproduct=null;
		String sql="select e from product e" ;
		listproduct=entityManager.createQuery(sql,product.class).getResultList();
		return listproduct;
	}
	
	public List<product> findProduct(String nameProduct) {
		if(nameProduct!=null && !nameProduct.equals("")) {
			List<product> products=null;
			String sql="select e from product e where e.nameProduct like :nameProduct";
			String name="%"+nameProduct+"%";
			products=entityManager.createQuery(sql,product.class).setParameter("nameProduct", name).getResultList();
			return products;
		}
		System.out.println("name search:"+nameProduct);
		return null;
	}
	
	public void addProduct(productForm productInfor) {
		
		
		byte[] image=null;
		if(productInfor!=null) {
		try {
			 image=productInfor.getFileData().getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		System.out.println("add price:"+productInfor.getPrice());
		 Query query = entityManager.createNativeQuery("INSERT INTO product (id, nameProduct,price,image,quantity) " +
		            " VALUES(?,?,?,?,?)");
		        query.setParameter(1, productInfor.getId());
		        query.setParameter(2, productInfor.getNameProduct());
		        query.setParameter(3, productInfor.getPrice());
		        query.setParameter(4, image);
		        query.setParameter(5, Integer.parseInt(productInfor.getQuantity()));
		      int a=  query.executeUpdate();
		      
	}
	public void updateProduct(productInfor productForm) {
		System.out.println("update");
		byte[] image=null;
		product pr=null;
		if(productForm!=null) {
			pr=this.findbyId(productForm.getId());
			if(pr!=null) {
			 image=productForm.getImage();
			 pr=new product();
			 pr.setId(productForm.getId());
			 pr.setNameProduct(productForm.getNameProduct());
			 pr.setPrice(Double.parseDouble(productForm.getPrice()));
			 pr.setImage(image);
			 pr.setQuantity(productForm.getQuantity());
			 entityManager.merge(pr);
			}
		}
		
	}
	public void deleteProduct(productInfor productInfor) {
		product pr=this.findbyId(productInfor.getId());
		if(pr!=null) {
		entityManager.remove(pr);
		}
	}
	public product findbyId(String id) {
		product product=entityManager.find(product.class, id);
		if(product!=null) {
			return product;
		}
		return null;
	}
	
	
}
