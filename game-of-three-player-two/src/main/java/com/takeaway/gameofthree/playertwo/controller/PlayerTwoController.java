package com.takeaway.gameofthree.playertwo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takeaway.gameofthree.common.component.Calculator;
import com.takeaway.gameofthree.common.constants.Constants;
import com.takeaway.gameofthree.common.dto.GameMessage;
import com.takeaway.gameofthree.playertwo.component.PlayerTwoMessageHandler;

@RestController
@RequestMapping("/gameofthree")
public class PlayerTwoController {

	@Autowired
	PlayerTwoMessageHandler playerTwoMessageHandler;
	
	@Autowired
	Calculator calculator;

	private static final Logger LOGGER = LogManager.getLogger(PlayerTwoController.class);

	@GetMapping("/playerTwo")
	public String generateNumber() {
		int gameId = calculator.generateGameId();
		long number = calculator.generateFirstNumber();
		
		GameMessage message = new GameMessage(gameId, number);
		LOGGER.info(Constants.PLAYER_TWO + " :: GAME STARTED | ID = " + gameId + " | GENERATED NUMBER = " + number);
		playerTwoMessageHandler.sendMessage(message);
	
		return Constants.GAME_STARTED;

	}
}

