package com.takeaway.gameofthree.common.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.takeaway.gameofthree.common.component.Calculator;
import com.takeaway.gameofthree.common.constants.Constants;

public class CalculatorTest {

	private static final long MAX_GENERATED_NUMBER = Long.MAX_VALUE;
	private static final int MAX_GAME_ID = 100;
	private static final long FIRST_TEST_NUMBER = 9;
	private static final long SECOND_TEST_NUMBER = 8;
	private static final long THIRD_TEST_NUMBER = 10;

	private static final long CONTROL_RESULT = 3;

	private Calculator calculator = new Calculator();

	@Test
	public void testGetNextRoundMax() {
		assertTrue(calculator.getNext(SECOND_TEST_NUMBER) == CONTROL_RESULT);
	}

	@Test
	public void testGetNextRoundMin() {
		assertTrue(calculator.getNext(THIRD_TEST_NUMBER)  == CONTROL_RESULT);
	}

	@Test
	public void testGetNextNoRound() {
		assertTrue(calculator.getNext(FIRST_TEST_NUMBER) == CONTROL_RESULT);
	}

	@Test
	public void testGenerateGameId() {
		assertTrue(calculator.generateGameId() > Constants.ZERO && calculator.generateGameId() < MAX_GAME_ID);
	}

	@Test
	public void testGenerateFirstNumber() {
		assertTrue(calculator.generateFirstNumber() > Constants.ZERO && calculator.generateFirstNumber() < MAX_GENERATED_NUMBER);
	}
}
