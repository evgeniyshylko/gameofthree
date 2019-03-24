package com.takeaway.gameofthree.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.takeaway.gameofthree.common.component.Calculator;
import com.takeaway.gameofthree.common.constants.Constants;
import com.takeaway.gameofthree.common.dto.GameMessage;



@Component
public class PlayerMessageHandler {

	@Autowired
	private JmsTemplate jmsQueueTemplate;


	@Autowired
	Calculator calculator;
	
	@Value("${playerId}")
	private String playerId;
	
	@Value("${nextPlayer}")
	private String nextPlayerQueue;
	
	@Value("${gameLog}")
	private String gameLogQueue;
	
	public void sendMessage(GameMessage message, String queueName) {
			jmsQueueTemplate.convertAndSend(queueName,  message);
	}

	@JmsListener(destination = "${playerId}", containerFactory = "myFactory")
	public void receiveMessage(GameMessage message) throws InterruptedException {
		message.setPlayerId(playerId);
		sendMessage(message, gameLogQueue);
		long current = message.getNumber();
		System.out.println(playerId + " :: GAME_ID :" + message.getGameId() + " :: recieved number : " + current);
		Long next  =  calculator.getNext(current);
		if(next == 1) {
			System.out.println(playerId + " :: I WIN !!!");
			message.setWinner(true);
			sendMessage(message, gameLogQueue);
		}
		else {
			message.setNumber(next);
			sendMessage(message, nextPlayerQueue);
		}
	}




}
