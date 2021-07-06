package com.spartan.api.stockmarket.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.spartan.api.stockmaket.dto.CompanyRequest;
import com.spartan.api.stockmarket.service.CompanyService;
import com.spartan.api.stockmarket.stock.EStockMarketApplication;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = EStockMarketApplication.class)
@SpringBootTest
class CompanyControllerTest {
	
	private MockMvc mockMvc;

	@InjectMocks
	private CompanyController companyControllerTest;
	
	@Mock
	private CompanyService companyServiceTest;

	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(companyControllerTest).build();
	}
	
	private  CompanyRequest mockCompany()  {
		String company = "{\r\n" + 
				"    \"companyCode\" : \"Test CC\",\r\n" + 
				"    \"companyName\" : \"Test CN\",\r\n" + 
				"    \"ceo\" : \"TEST CEO\",\r\n" + 
				"    \"turnOver\" : \"10000000000\",\r\n" + 
				"    \"website\" : \"test@test.com\",\r\n" + 
				"    \"stockExchange\" : \"TEST SE\"\r\n" + 
				"}";
		Gson gson = new Gson();
		CompanyRequest companyRequest = gson.fromJson(company, CompanyRequest.class);
		return companyRequest;
	}
	
	@Test
	public void testCompanyRegistration() throws Exception {
		when(companyServiceTest.companyRegistration(mockCompany())).thenReturn("SUCCESS");
		this.mockMvc
				.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(mockCompany())))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	}
	
	static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
