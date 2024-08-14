package com.retailer.rewardpoints.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RewardServiceTest {

	private RewardPointsService rewardPointsService;

	@BeforeEach
	public void setUp() {
		rewardPointsService = new RewardPointsService();
	}

	@Test
	public void testCalculatePoints_Over100() {
		assertEquals(90, rewardPointsService.calculatePoints(120));
	}

	@Test
	public void testCalculatePoints_Between50And100() {
		assertEquals(35, rewardPointsService.calculatePoints(85));
	}

	@Test
	public void testCalculatePoints_Below50() {
		assertEquals(0, rewardPointsService.calculatePoints(40));
	}

	@Test
	public void testCalculatePoints_Exactly100() {
		assertEquals(50, rewardPointsService.calculatePoints(100));
	}
}
