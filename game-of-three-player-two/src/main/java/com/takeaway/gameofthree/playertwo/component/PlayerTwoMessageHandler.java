package com.takeaway.gameofthree.playertwo.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.takeaway.gameofthree.common.component.Calculator;
import com.takeaway.gameofthree.common.constants.Constants;
import com.takeaway.gameofthree.common.dto.GameMessage;

@Component
public class PlayerTwoMessageHandler {

	@Autowired
	private JmsTemplate jmsQueueTemplateTwo;

	
	@Autowired
	Calculator calculator;
	
	private static final Logger LOGGER = LogManager.getLogger(PlayerTwoMessageHandler.class);


	public void sendMessage(GameMessage message) {
		jmsQueueTemplateTwo.convertAndSend(Constants.PLAYER_ONE,  message);
	}

	@JmsListener(destination = Constants.PLAYER_TWO, containerFactory = "myFactory")
	public void receiveMessage(GameMessage message) throws InterruptedException {
		long current = message.getNumber();
		LOGGER.info(Constants.PLAYER_TWO + " :: GAME_ID :" + message.getGameId() + " :: recieved number : " + current);
		Long next  =  calculator.getNext(current);
		if(next == 1) {
			LOGGER.info(Constants.PLAYER_TWO + " :: I WIN !!!");
		}
		else {
			message.setNumber(next);
			sendMessage(message);
		}
	}




}
