package com.frame.test;

import java.util.Date;

/**
 * author chenqi
 * time 2017/8/2 11:03
 */
public class Product {
	private String product_name;

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Date getProduct_date() {
		return product_date;
	}

	public void setProduct_date(Date product_date) {
		this.product_date = product_date;
	}

	private Date product_date;
}
