package com.takeaway.gameofthree.playerone.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takeaway.gameofthree.common.component.Calculator;
import com.takeaway.gameofthree.common.constants.Constants;
import com.takeaway.gameofthree.common.dto.GameMessage;
import com.takeaway.gameofthree.playerone.component.PlayerOneMessageHandler;

@RestController
@RequestMapping("/gameofthree")
public class PlayerOneController {

	@Autowired
	PlayerOneMessageHandler playerOneMessageHandler;
	
	@Autowired
	Calculator calculator;

	private static final Logger LOGGER = LogManager.getLogger(PlayerOneController.class);

	@GetMapping("/playerOne")
	public String generateNumber() {
		
		int gameId = calculator.generateGameId();
		long number = calculator.generateFirstNumber();
		
		GameMessage message = new GameMessage(gameId, number);
		LOGGER.info(Constants.PLAYER_ONE + " :: GAME STARTED | ID = " + gameId + " | GENERATED NUMBER = " + number);
		playerOneMessageHandler.sendMessage(message);

		return Constants.GAME_STARTED;

	}

}
