package com.takeaway.gameofthree.playerone.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.takeaway.gameofthree.common.component.Calculator;
import com.takeaway.gameofthree.common.constants.Constants;
import com.takeaway.gameofthree.common.dto.GameMessage;



@Component
public class PlayerOneMessageHandler {

	@Autowired
	private JmsTemplate jmsQueueTemplate;


	@Autowired
	Calculator calculator;

	private static final Logger LOGGER = LogManager.getLogger(PlayerOneMessageHandler.class);


	public void sendMessage(GameMessage message) {
		try {
			jmsQueueTemplate.convertAndSend(Constants.PLAYER_TWO,  message);
		}
		catch (JmsException ex) {
			LOGGER.error("Failed to connect to ActiveMQ broker", ex);
		}
	}



	@JmsListener(destination = Constants.PLAYER_ONE, containerFactory = "myFactory")
	public void receiveMessage(GameMessage message) throws InterruptedException {
		long current = message.getNumber();
		LOGGER.info(Constants.PLAYER_ONE + " :: GAME_ID :" + message.getGameId() + " :: recieved number : " + current);
		Long next  =  calculator.getNext(current);
		if(next == 1) {
			LOGGER.info(Constants.PLAYER_ONE + " :: I WIN !!!");
		}
		else {
			message.setNumber(next);
			sendMessage(message);
		}
	}




}
