package com.takeaway.gameofthree.player.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.jms.core.JmsTemplate;
import static org.mockito.Mockito.verify; 

import com.takeaway.gameofthree.common.component.Calculator;
import com.takeaway.gameofthree.common.dto.GameMessage;
import com.takeaway.gameofthree.component.PlayerMessageHandler;

public class PlayerControllerTest {

	@InjectMocks
	PlayerMessageHandler handler;

	@Mock
	JmsTemplate JmsTemplate;
	
	@Spy
	Calculator calculator;
	
	@Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testCorrectNuberReceivedFromQueue() {
		GameMessage message = new GameMessage();
		message.setGameId(4);
		message.setNumber((long) 100);
		try {
			handler.receiveMessage(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(message.getNumber() == 33);
	}

}
