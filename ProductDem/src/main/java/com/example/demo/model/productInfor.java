package com.example.demo.model;

public class productInfor {
	private String id;
	private String nameProduct;
	private String price;
	private byte[] image;
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public productInfor() {
		super();
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

	

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	
}
