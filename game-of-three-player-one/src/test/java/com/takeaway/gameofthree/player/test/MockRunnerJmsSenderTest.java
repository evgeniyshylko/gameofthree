package com.takeaway.gameofthree.player.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.takeaway.gameofthree.common.dto.GameMessage;
import com.takeaway.gameofthree.playerone.component.PlayerOneMessageHandler;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext-mockrunner.xml")
public class MockRunnerJmsSenderTest {
	
	@Autowired
	PlayerOneMessageHandler handler;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	private static final long NUMBER_TO_SEND = 19;
	private static final long RECIEVED_NUMBER = 6;


	@Test
	public void validateSendRecieveJMS()
	{
		GameMessage message = new GameMessage();
		message.setGameId(100);
		message.setNumber(NUMBER_TO_SEND);
		handler.sendMessage(message);
		
		try {
			handler.receiveMessage(message);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		assertTrue(message.getNumber() == RECIEVED_NUMBER);
		
		
	}
}