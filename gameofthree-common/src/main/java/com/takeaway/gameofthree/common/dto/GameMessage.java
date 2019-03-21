package com.takeaway.gameofthree.common.dto;

import java.io.Serializable;

public class GameMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int gameId;
	
	Long number;

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
	
	public GameMessage(int gameId, Long number) {
		this.gameId = gameId;
		this.number = number;
	}
	
	public GameMessage() {
		
	}

	@Override
	public String toString() {
		return "GameMessage [gameId=" + gameId + ", number=" + number + "]";
	}
	
	

}
