package com.stockmarket.service;

import java.util.List;

import com.stockmarket.dto.CompanyRequest;
import com.stockmarket.dto.CompanyResponse;
import com.stockmarket.entity.Company;


public interface CompanyService {

	List<Company> getAllCompanies();

	String companyRegistration(CompanyRequest request);

	CompanyResponse fetchCompanyDetailByCompanyCode(String companyCode);

	String deleteCompanyDetails(String companyCode);

}
