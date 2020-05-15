package com.example.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;




@Entity
@Table(name = "product")
public class product {
	@Id
	@Column(name = "id", length = 45, nullable = false)
	private String id;

	@Column(name = "nameproduct", length = 128, nullable = false)
	private String nameProduct;

	@Column(name = "price", nullable = false)
	private double price;

	@Lob
	@Column(name = "image", nullable = false)
	private byte[] image;
	
	@Column(name = "quantity",nullable = false)
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	

}
