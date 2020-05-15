package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Form.productForm;
import com.example.demo.dao.productDao;
import com.example.demo.entity.product;
import com.example.demo.model.productInfor;

@Service
public class ProductService {
	@Autowired
	productDao prDao;

	public List<productInfor> listProduct() {
		List<productInfor> listProductInfors = new ArrayList<productInfor>();
		List<product> listpr = this.prDao.listProduct();
		for (product pr : listpr) {
			productInfor prInfor = new productInfor();
			prInfor.setId(pr.getId());
			prInfor.setNameProduct(pr.getNameProduct());
			String price=String.format("%.2f%n", pr.getPrice());
			prInfor.setPrice(price);
			prInfor.setImage(pr.getImage());
			prInfor.setQuantity(pr.getQuantity());
			listProductInfors.add(prInfor);
		}
		return listProductInfors;
	}

	public List<productInfor> findProduct(String nameProduct) {
		List<productInfor> listprInfor = new ArrayList<productInfor>();
		List<product> pro = null;
		if (nameProduct != null || nameProduct.equals("")) {
			pro = prDao.findProduct(nameProduct);
		}
		if (pro != null) {
			for (product product : pro) {
				productInfor prInfor = new productInfor();
				prInfor.setId(product.getId());
				prInfor.setNameProduct(product.getNameProduct());
				String price=String.format("%.2f%n", product.getPrice());
				prInfor.setPrice(price);
				prInfor.setImage(product.getImage());
				prInfor.setQuantity(product.getQuantity());
				listprInfor.add(prInfor);

			}
			return listprInfor;
		}
		return null;
	}

	public void addProduct(productForm productForm) {
		prDao.addProduct(productForm);
	}

	public void update(productInfor productInfor) {
		prDao.updateProduct(productInfor);
	}

	public void deleteProduct(productInfor prInfor) {
		prDao.deleteProduct(prInfor);
	}

	public productInfor findbyId(String id) {
		productInfor proInfor = null;
		product product = this.prDao.findbyId(id);
		if (product != null) {
			proInfor = new productInfor();
			proInfor.setId(product.getId());
			proInfor.setNameProduct(product.getNameProduct());
			String price=String.format("%.2f%n", product.getPrice());
			proInfor.setPrice(price);
			proInfor.setImage(product.getImage());
			proInfor.setQuantity(product.getQuantity());
			return proInfor;
		}
		return null;
	}

}
