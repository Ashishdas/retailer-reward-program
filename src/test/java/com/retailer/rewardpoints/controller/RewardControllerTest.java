package com.retailer.rewardpoints.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailer.rewardpoints.model.CustomerTransactions;
import com.retailer.rewardpoints.model.MonthlyTransaction;
import com.retailer.rewardpoints.model.Transaction;
import com.retailer.rewardpoints.service.RewardPointsService;

@WebMvcTest(RewardController.class)
public class RewardControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private RewardPointsService rewardPointsService;

	@InjectMocks
	private RewardController rewardController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(rewardController).build();
	}

	@Test
	public void testCalculateRewardPoints() throws Exception {
		// Prepare test data
		Map<String, MonthlyTransaction> transactionsByMonth = new HashMap<>();
		transactionsByMonth.put("January",
				new MonthlyTransaction("January", List.of(new Transaction(120), new Transaction(85))));
		transactionsByMonth.put("February",
				new MonthlyTransaction("February", List.of(new Transaction(40), new Transaction(75))));
		transactionsByMonth.put("March",
				new MonthlyTransaction("March", List.of(new Transaction(200), new Transaction(95))));

		CustomerTransactions customerTransactions = new CustomerTransactions("12345", transactionsByMonth);

		Map<String, Integer> monthlyPoints = Map.of("January", 125, "February", 25, "March", 295);

		int totalPoints = 445;

		// Mocking service methods
		when(rewardPointsService.calculateMonthlyPoints(any())).thenReturn(monthlyPoints);
		when(rewardPointsService.calculateTotalPoints(any())).thenReturn(totalPoints);

		// Performing POST request
		mockMvc.perform(post("/api/rewards/calculate").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customerTransactions))).andExpect(status().isOk())
				.andExpect(jsonPath("$.monthlyPoints.January", is(125)))
				.andExpect(jsonPath("$.monthlyPoints.February", is(25)))
				.andExpect(jsonPath("$.monthlyPoints.March", is(295))).andExpect(jsonPath("$.totalPoints", is(445)));
	}

	// converting object to JSON string
	private static String asJsonString(Object obj) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
