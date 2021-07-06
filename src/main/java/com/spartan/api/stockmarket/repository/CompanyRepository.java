package com.spartan.api.stockmarket.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.spartan.api.stockmarket.entity.Company;

@EnableScan
public interface CompanyRepository extends CrudRepository<Company, String> {

	Optional<Company> findByCompanyCode(String companyCode);

}
