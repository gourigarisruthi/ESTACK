package com.stockmarket.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.stockmarket.EStockMarketApplication;
import com.stockmarket.dto.CompanyRequest;
import com.stockmarket.dto.CompanyResponse;
import com.stockmarket.entity.Company;
import com.stockmarket.repository.CompanyRepository;
import com.stockmarket.service.CompanyServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EStockMarketApplication.class)
@WebMvcTest(value = CompanyServiceImpl.class)
public class CompanyServiceImplTest {

	@SpyBean
	CompanyServiceImpl companyServiceImpl;

	@MockBean
	CompanyRepository companyRepository;

	@Test
	public void getAllTest() {
		List<Company> companyList1 = new ArrayList<>();
		when(companyRepository.findAll()).thenReturn(companyList1);
		List<Company> companyList = companyServiceImpl.getAllCompanies();
		assertEquals(companyList.size(), 0);
		verify(companyRepository, times(1)).findAll();

	}

	@Test
	public void getAllExceptionTest() {
		when(companyRepository.findAll()).thenThrow(IllegalArgumentException.class);
		List<Company> companyList = companyServiceImpl.getAllCompanies();
		assertEquals(companyList.size(), 0);
		verify(companyRepository, times(1)).findAll();

	}

	@Test
	public void companyRegistrationTest() {
		Company company = new Company();
		company.setCompanyCode("STC");
		when(companyRepository.findByCompanyCode(Mockito.anyString())).thenReturn(Optional.of(company));
		CompanyRequest request = new CompanyRequest();
		String message = companyServiceImpl.companyRegistration(request);
		assertEquals(message, "Please Provide the Company Code");
		verify(companyRepository, times(0)).findByCompanyCode(Mockito.anyString());

		request.setCompanyCode("CTS");
		message = companyServiceImpl.companyRegistration(request);
		assertEquals(message, "Please Provide the CEO details");
		verify(companyRepository, times(1)).findByCompanyCode(Mockito.anyString());

		request.setCeo("Sruthi");
		message = companyServiceImpl.companyRegistration(request);
		assertEquals(message, "Please Provide the Company Name");
		verify(companyRepository, times(2)).findByCompanyCode(Mockito.anyString());

		request.setCompanyName("Cognizant");
		message = companyServiceImpl.companyRegistration(request);
		assertEquals(message, "Please Provide the Stock Exchange details");
		verify(companyRepository, times(3)).findByCompanyCode(Mockito.anyString());

		request.setStockExchange("NSE");
		message = companyServiceImpl.companyRegistration(request);
		assertEquals(message, "Please Provide the Turn Over details");
		verify(companyRepository, times(4)).findByCompanyCode(Mockito.anyString());

		request.setTurnOver(1000000.0);
		message = companyServiceImpl.companyRegistration(request);
		assertEquals(message, "Company Turn Over should be greater than 10Cr.");
		verify(companyRepository, times(5)).findByCompanyCode(Mockito.anyString());

		request.setTurnOver(100000000.0);
		message = companyServiceImpl.companyRegistration(request);
		assertEquals(message, "Please Provide the Web site details");
		verify(companyRepository, times(6)).findByCompanyCode(Mockito.anyString());

		request.setWebsite("https://onecognizant.com");
		message = companyServiceImpl.companyRegistration(request);
		assertEquals(message, "SUCCESS");
		verify(companyRepository, times(7)).findByCompanyCode(Mockito.anyString());
		verify(companyRepository, times(1)).save(Mockito.any());

		company.setCompanyCode("CTS");
		when(companyRepository.findByCompanyCode(Mockito.anyString())).thenReturn(Optional.of(company));
		message = companyServiceImpl.companyRegistration(request);
		assertEquals(message,
				"Company Code " + request.getCompanyCode() + " is Already exits. Please try with other Company Code");
		verify(companyRepository, times(8)).findByCompanyCode(Mockito.anyString());

	}

	@Test
	public void companyRegistrationExceptionTest() {
		CompanyRequest request = new CompanyRequest();
		request.setCompanyCode("STC");
		when(companyRepository.findByCompanyCode(Mockito.anyString())).thenThrow(IllegalArgumentException.class);
		companyServiceImpl.companyRegistration(request);
		verify(companyRepository, times(1)).findByCompanyCode(Mockito.anyString());
	}

	@Test
	public void fetchCompanyDetailByCompanyCodeTest() {
		Company company = new Company();
		company.setCompanyCode("CTS");
		when(companyRepository.findByCompanyCode(Mockito.anyString())).thenReturn(Optional.of(company));
		CompanyResponse response = companyServiceImpl.fetchCompanyDetailByCompanyCode("CTS");
		assertEquals(response.getCompany().getCompanyCode(), "CTS");
		response = companyServiceImpl.fetchCompanyDetailByCompanyCode("STC");
		assertEquals(response.getMessage(), "Not Available");
		verify(companyRepository, times(2)).findByCompanyCode(Mockito.anyString());

	}

	@Test
	public void fetchCompanyDetailByCompanyCodeExceptionTest() {
		when(companyRepository.findByCompanyCode(Mockito.anyString())).thenThrow(IllegalArgumentException.class);
		CompanyResponse response = companyServiceImpl.fetchCompanyDetailByCompanyCode("CTS");
		assertEquals(response.getMessage(), null);
		verify(companyRepository, times(1)).findByCompanyCode(Mockito.anyString());

	}
	
	@Test
	public void deleteCompanyDetailsTest() {
		Company company = new Company();
		company.setCompanyCode("CTS");
		when(companyRepository.findByCompanyCode(Mockito.anyString())).thenReturn(Optional.of(company));
		String response = companyServiceImpl.deleteCompanyDetails("CTS");
		assertEquals(response, "Deleted Successfully");
		response = companyServiceImpl.deleteCompanyDetails("STC");
		assertEquals(response, "There is no company available with the Company Code STC");
		verify(companyRepository, times(2)).findByCompanyCode(Mockito.anyString());

	}

	@Test
	public void deleteCompanyDetailsExceptionTest() {
		when(companyRepository.findByCompanyCode(Mockito.anyString())).thenThrow(IllegalArgumentException.class);
		String response = companyServiceImpl.deleteCompanyDetails("CTS");
		assertEquals(response, "Error occured while deleting the company! Please try again later");
		verify(companyRepository, times(1)).findByCompanyCode(Mockito.anyString());

	}
}
