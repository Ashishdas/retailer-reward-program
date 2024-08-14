package com.retailer.rewardpoints.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.rewardpoints.model.CustomerTransactions;
import com.retailer.rewardpoints.service.RewardPointsService;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

	@Autowired
	private RewardPointsService rewardPointsService;

	@PostMapping("/calculate")
	public Map<String, Object> calculateRewardPoints(@RequestBody CustomerTransactions customerTransactions) {
		// This will Calculate monthly points
		Map<String, Integer> monthlyPoints = rewardPointsService
				.calculateMonthlyPoints(customerTransactions.getTransactionsByMonth());

		// This will Calculate total points
		int totalPoints = rewardPointsService.calculateTotalPoints(monthlyPoints);
		return Map.of("monthlyPoints", monthlyPoints, "totalPoints", totalPoints);
	}
}
