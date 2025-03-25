package com.blanco.interbanking;

import com.blanco.interbanking.infrastructure.controller.company.dto.CompanyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompanyRestControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Order(1)
	@DisplayName("Should register a new company successfully")
	void shouldRegisterCompany() throws Exception {
		var request = new CompanyDTO(
				UUID.randomUUID().toString(),
				"New Tech Corp",
				"30-99999999-9",
				LocalDateTime.now()
		);

		mockMvc.perform(post("/api/v.1/company/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andExpect(jsonPath("$.companyName").value("New Tech Corp"))
				.andExpect(jsonPath("$.cuit").value("30-99999999-9"));
	}

	@Test
	@Order(2)
	@DisplayName("Should not register a new company with wrong cuit")
	void notRegisterCompanyWithWrongCuit() throws Exception {
		var request = new CompanyDTO(
				UUID.randomUUID().toString(),
				"New Tech Corp",
				"30-9",
				LocalDateTime.now()
		);

		mockMvc.perform(post("/api/v.1/company/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.detail").value("Cuit format not valid."))
				.andExpect(jsonPath("$.errorType").value("BUSINESS"))
				.andExpect(jsonPath("$.httpCode").value(400));
	}

	@Test
	@Order(3)
	@DisplayName("Should not register a new company with wrong adhesionDate")
	void notRegisterCompanyWithWrongadhesionDate() throws Exception {
		var request = new CompanyDTO(
				UUID.randomUUID().toString(),
				"New Tech Corp",
				"30-99999999-9",
				LocalDateTime.now().plusMonths(12)
		);

		mockMvc.perform(post("/api/v.1/company/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.detail").value("The date of accession cannot be in the future."))
				.andExpect(jsonPath("$.errorType").value("BUSINESS"))
				.andExpect(jsonPath("$.httpCode").value(400));
	}
	@Test
	@Order(4)
	@DisplayName("Should return companies registered in the last month")
	void shouldReturnCompaniesRegisteredLastMonth() throws Exception {
		mockMvc.perform(get("/api/v.1/company/adhered_last_month")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.companies").isArray())
				.andExpect(jsonPath("$.companies.length()").value(3));
	}

	@Test
	@Order(5)
	@DisplayName("Should return companies with transfers in the last month")
	void shouldReturnCompaniesWithTransfersLastMonth() throws Exception {
		mockMvc.perform(get("/api/v.1/company/transfer_last_month")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.companies").isArray())
				.andExpect(jsonPath("$.companies.length()").value(2));
	}
}
