package com.takeaway.gameofthree.logger.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.takeaway.gameofthree.common.constants.Constants;
import com.takeaway.gameofthree.common.dto.GameMessage;

@Component
public class LoggsHandler {
	
	@Value("${queueName}")
	private String queueName;
	
	private static final Logger LOGGER = LogManager.getLogger(LoggsHandler.class);
	
	@JmsListener(destination = "${queueName}", containerFactory = "myFactory")
	public void receiveMessage(GameMessage message) throws InterruptedException {
		LOGGER.info("GAME ID: " + message.getGameId() + " :: Number = " + message.getNumber()
		+ " recieved by "  + message.getPlayerId() 
		+ " .Player wins = " + message.isWinner());
		
		
	}

}
