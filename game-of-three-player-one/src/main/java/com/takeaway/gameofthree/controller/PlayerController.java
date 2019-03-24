package com.takeaway.gameofthree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takeaway.gameofthree.common.component.Calculator;
import com.takeaway.gameofthree.common.constants.Constants;
import com.takeaway.gameofthree.common.dto.GameMessage;
import com.takeaway.gameofthree.component.PlayerMessageHandler;

@RestController
@RequestMapping("${rootUrl}")
public class PlayerController {

	@Autowired
	PlayerMessageHandler playerOneMessageHandler;
	
	@Autowired
	Calculator calculator;
	
	@Value("${playerId}")
	private String playerId;
	
	@Value("${nextPlayer}")
	private String nextPlayer;

	@GetMapping("${playerUrl}")
	public String generateNumber() {
		
		int gameId = calculator.generateGameId();
		long number = calculator.generateFirstNumber();
		
		GameMessage message = new GameMessage();
		message.setGameId(gameId);
		message.setNumber(number);
		message.setPlayerId(playerId);
		System.out.println(playerId + " :: GAME STARTED | ID = " + gameId + " | GENERATED NUMBER = " + number);
		playerOneMessageHandler.sendMessage(message, nextPlayer);
		return playerId + " Starts the game";

	}

}
