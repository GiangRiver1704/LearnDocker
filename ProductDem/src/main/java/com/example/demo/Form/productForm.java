package com.example.demo.Form;


import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.productInfor;

public class productForm {
	private String id;
	private String nameProduct;
	private String price;
	private MultipartFile fileData;
	private String quantity;
	private boolean newProduct;
	
	public productForm() {
	}
	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}
	public productForm(productInfor prInfor) {
		super();
		this.id = prInfor.getId();
		this.nameProduct = prInfor.getNameProduct();
		String price=prInfor.getPrice();
		this.price = price;
		this.quantity = String.valueOf(prInfor.getQuantity());
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public MultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	

}
