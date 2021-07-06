package com.spartan.api.stockmarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spartan.api.stockmaket.dto.CompanyRequest;
import com.spartan.api.stockmaket.dto.CompanyResponse;
import com.spartan.api.stockmarket.entity.Company;

@Service
public interface CompanyService {

	List<Company> getAllCompanies();

	String companyRegistration(CompanyRequest request);

	CompanyResponse fetchCompanyDetailByCompanyCode(String companyCode);

	String DeleteCompanyDetails(String companyCode);

}
