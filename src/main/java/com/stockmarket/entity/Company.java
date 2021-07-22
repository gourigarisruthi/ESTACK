package com.stockmarket.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;

@Data
@DynamoDBTable(tableName = "company")
public class Company {
	

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@DynamoDBHashKey(attributeName = "company_code")
	private String companyCode;
	
	@DynamoDBAttribute(attributeName = "company_name")
	private String companyName;
	
	@DynamoDBAttribute(attributeName = "ceo")
	private String ceo;
	
	@DynamoDBAttribute(attributeName = "turnover")
	private double turnOver;
	
	@DynamoDBAttribute(attributeName = "website")
	private String website;
	
	@DynamoDBAttribute(attributeName = "stock_exchange")
	private String stockExchange;
	
	
}
