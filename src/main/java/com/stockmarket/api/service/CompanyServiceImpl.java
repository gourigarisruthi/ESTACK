package com.stockmarket.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmaket.api.dto.CompanyRequest;
import com.stockmaket.api.dto.CompanyResponse;
import com.stockmarket.api.entity.Company;
import com.stockmarket.api.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	private static final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Override
	public List<Company> getAllCompanies() {
		List<Company> companyList = new ArrayList<>();
		try {
			companyList = (List<Company>) companyRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return companyList;
	}

	@Override
	public String companyRegistration(CompanyRequest request) {
		
		log.info(this.getClass().getName(), "Inside companyRegistration method request " + request.toString());
		// String message = null;
		Optional<Company> company = null;
		if (request.getCompanyCode() != null) {
			company = companyRepository.findById(request.getCompanyCode());
			if (company.isPresent()) {
				log.info(this.getClass().getName(), "Company Code " + request.getCompanyCode()
						+ " is Already exits. Please try with other Company Code");
				return "Company Code " + request.getCompanyCode()
						+ " is Already exits. Please try with other Company Code";
			} else {
				if (request.getCeo() == null) {
					log.info(this.getClass().getName(), "companyRegistration ceo field is null");
					return "Please Provide the CEO details";
				}
				if (request.getCompanyName() == null) {
					log.info(this.getClass().getName(), "companyRegistration Company Name field is null");
					return "Please Provide the Company Name";
				}
				if (request.getStockExchange() == null) {
					log.info(this.getClass().getName(), "companyRegistration Stock Exchange field is null");
					return "Please Provide the Stock Exchange details";
				}
				if (request.getTurnOver() == null) {
					log.info(this.getClass().getName(), "companyRegistration Turn Over field is null");
					return "Please Provide the Turn Over details";
				}
				if (request.getWebsite() == null) {
					log.info(this.getClass().getName(), "companyRegistration Web site field is null");
					return "Please Provide the Web site details";
				}
				try {

					Company newCompany = new Company();
					BeanUtils.copyProperties(request, newCompany);
					companyRepository.save(newCompany);
				} catch (Exception e) {

					log.error(this.getClass().getName(), "companyRegistration ceo field is null",e);
					e.printStackTrace();
				}
			}
		} else {
			log.info(this.getClass().getName(), "companyRegistration Company Code field is null");
			return "Please Provide the Company Code ";
		}
		return "SUCCESS";
	}

	@Override
	public CompanyResponse fetchCompanyDetailByCompanyCode(String companyCode) {
		System.out.println(companyCode);
		log.info(this.getClass().getName(), "Inside fetcheCompanyDetailByCompanyCode method request " + companyCode);
		CompanyResponse response = new CompanyResponse();
		try {
			Optional<Company> company =  companyRepository.findByCompanyCode(companyCode);
			if (company.isPresent()) {
				response.setCompany(company.get());
			} else {
				response.setMessage("Not Available");
				log.info(this.getClass().getName(), "companyRegistration Company Code "+companyCode+" is not available Please provide valid Company Code");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public String DeleteCompanyDetails(String companyCode) {
		try {
			Optional<Company> company =  companyRepository.findByCompanyCode(companyCode);
			if (company.isPresent()) {
				companyRepository.delete(company.get());
			} else {
				log.info(this.getClass().getName(), "companyRegistration Company Code "+companyCode+" is not available Please provide valid Company Code");
				return "There is no company available with the Company Code "+companyCode;
			}
		}catch(Exception e) {
			log.error("Exception occured while deleting the company : "+e.getStackTrace());
			return "Error occured while deleting the company! Please try again later";
		}
		return "Deleted Successfully";
	}

}
