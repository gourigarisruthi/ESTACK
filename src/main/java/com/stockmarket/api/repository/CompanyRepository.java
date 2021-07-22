package com.stockmarket.api.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.stockmarket.api.entity.Company;

@EnableScan
public interface CompanyRepository extends CrudRepository<Company, String> {

	Optional<Company> findByCompanyCode(String companyCode);

}
