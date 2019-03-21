package com.takeaway.gameofthree.common.component;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import com.takeaway.gameofthree.common.constants.Constants;

@Component
public class Calculator {
	
	
	public long getNext(Long current) {
		return Math.round(((double) current)/3);
	}
	
	public int generateGameId() {
		return ThreadLocalRandom.current().nextInt(Constants.MIN_GAME_ID, Constants.MAX_GAME_ID);
	}
	
	public long generateFirstNumber() {
		return ThreadLocalRandom.current().nextLong(Constants.ZERO, Long.MAX_VALUE);
	}

}
