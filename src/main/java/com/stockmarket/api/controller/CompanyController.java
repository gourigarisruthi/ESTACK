package com.stockmarket.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmaket.api.dto.CompanyRequest;
import com.stockmaket.api.dto.CompanyResponse;
import com.stockmarket.api.entity.Company;
import com.stockmarket.api.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@GetMapping(value = "/getAll")
	public ResponseEntity <List<Company>> getAllCompanyDetails() {
		return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);

	}
	
	@PostMapping(value = "/register", produces = { MediaType.APPLICATION_JSON_VALUE })
	public String companyRegistration(@RequestBody CompanyRequest request) {
		return companyService.companyRegistration(request);

	}
	

	@GetMapping(value = "/info/{companyCode}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CompanyResponse fetchCompanyDetailByCompanyCode(@PathVariable("companyCode") String companyCode) {
		return companyService.fetchCompanyDetailByCompanyCode(companyCode);

	}
	
	@DeleteMapping(value = "/delete/{companyCode}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> DeleteCompanyDetails(@PathVariable("companyCode") String companyCode) {
		return new ResponseEntity<>(companyService.DeleteCompanyDetails(companyCode),HttpStatus.OK);
	}
	
}
