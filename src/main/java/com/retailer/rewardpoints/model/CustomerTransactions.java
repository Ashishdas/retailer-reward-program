package com.retailer.rewardpoints.model;

import java.util.Map;

public class CustomerTransactions {
	private String customerId;
	private Map<String, MonthlyTransaction> transactionsByMonth;

	public CustomerTransactions() {
	}

	public CustomerTransactions(String customerId, Map<String, MonthlyTransaction> transactionsByMonth) {
		this.customerId = customerId;
		this.transactionsByMonth = transactionsByMonth;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Map<String, MonthlyTransaction> getTransactionsByMonth() {
		return transactionsByMonth;
	}

	public void setTransactionsByMonth(Map<String, MonthlyTransaction> transactionsByMonth) {
		this.transactionsByMonth = transactionsByMonth;
	}
}
