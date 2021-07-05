package com.spartan.api.stockmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spartan.api.stockmaket.dto.CompanyRequest;
import com.spartan.api.stockmaket.dto.CompanyResponse;
import com.spartan.api.stockmarket.entity.Company;
import com.spartan.api.stockmarket.service.CompanyService;
import com.spartan.api.stockmarket.service.CompanyServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	CompanyServiceImpl companyService;
	
	@GetMapping(value = "/getAll")
	public ResponseEntity <List<Company>> getAllCompanyDetails() {
		return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);

	}
	
	@PostMapping(value = "/register", produces = { MediaType.APPLICATION_JSON_VALUE })
	public String companyRegistration(@RequestBody CompanyRequest request) {
		return companyService.companyRegistration(request);

	}
	

	@GetMapping(value = "/info/{companyCode}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CompanyResponse fetcheCompanyDetailByCompanyCode(@PathVariable("companyCode") String companyCode) {
		return companyService.fetcheCompanyDetailByCompanyCode(companyCode);

	}
}
