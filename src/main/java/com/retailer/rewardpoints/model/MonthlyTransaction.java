package com.retailer.rewardpoints.model;

import java.util.List;

public class MonthlyTransaction {
	private String month;
	private List<Transaction> transactions;

	public MonthlyTransaction() {
	}

	public MonthlyTransaction(String month, List<Transaction> transactions) {
		this.month = month;
		this.transactions = transactions;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
