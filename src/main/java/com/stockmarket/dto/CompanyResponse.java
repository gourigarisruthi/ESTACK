package com.stockmarket.dto;

import com.stockmarket.entity.Company;


public class CompanyResponse {

	private Company company;
	private String message;
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
