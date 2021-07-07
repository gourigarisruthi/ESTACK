package com.spartan.api.stockmarket.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.spartan.api.stockmaket.dto.CompanyRequest;
import com.spartan.api.stockmarket.service.CompanyService;
import com.spartan.api.stockmarket.stock.EStockMarketApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EStockMarketApplication.class)
@WebMvcTest(value = CompanyController.class)
class CompanyControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@SpyBean
	private CompanyController companyControllerTest;

	@MockBean
	private CompanyService companyServiceTest;

	private CompanyRequest mockCompany() {
		String company = "{\r\n" + "    \"companyCode\" : \"Test CC\",\r\n" + "    \"companyName\" : \"Test CN\",\r\n"
				+ "    \"ceo\" : \"TEST CEO\",\r\n" + "    \"turnOver\" : \"10000000000\",\r\n"
				+ "    \"website\" : \"test@test.com\",\r\n" + "    \"stockExchange\" : \"TEST SE\"\r\n" + "}";
		Gson gson = new Gson();
		CompanyRequest companyRequest = gson.fromJson(company, CompanyRequest.class);
		return companyRequest;
	}

	@Test
	public void testCompanyRegistration() throws Exception {
		this.mockMvc
				.perform(post("/company/register").contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(mockCompany())))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	}

	@Test
	public void testGetAllCompanies() throws Exception {
		this.mockMvc.perform(get("/company/getAll").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
	}

	@Test
	public void testfetchCompanyDetailByCompanyCode() throws Exception {
		this.mockMvc.perform(get("/company/info/CTS").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	}

	@Test
	public void testdeleteCompanyDetails() throws Exception {
		this.mockMvc.perform(delete("/company//delete/CTS").contentType(MediaType.APPLICATION_JSON))
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
