package com.retailer.rewardpoints.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.retailer.rewardpoints.model.MonthlyTransaction;

@Service
public class RewardPointsService {

	public Map<String, Integer> calculateMonthlyPoints(Map<String, MonthlyTransaction> transactionsByMonth) {
		return transactionsByMonth.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getTransactions().stream()
						.mapToInt(transaction -> calculatePoints(transaction.getAmount())).sum()));
	}

	public int calculateTotalPoints(Map<String, Integer> monthlyPoints) {
		return monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();
	}

	public int calculatePoints(double amount) {
		if (amount > 100) {
			return 2 * (int) (amount - 100) + 50;
		} else if (amount >= 50) {
			return (int) (amount - 50);
		}
		return 0;
	}

}
