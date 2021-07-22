package com.stockmarket.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stockmaket.api.dto.CompanyRequest;
import com.stockmaket.api.dto.CompanyResponse;
import com.stockmarket.api.entity.Company;

@Service
public interface CompanyService {

	List<Company> getAllCompanies();

	String companyRegistration(CompanyRequest request);

	CompanyResponse fetchCompanyDetailByCompanyCode(String companyCode);

	String deleteCompanyDetails(String companyCode);

}
