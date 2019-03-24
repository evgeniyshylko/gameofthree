package com.takeaway.gameofthree.common.dto;

import java.io.Serializable;

public class GameMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int gameId;
	Long number;
	String playerId;
	boolean isWinner;

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

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

	@Override
	public String toString() {
		return "GameMessage [gameId=" + gameId + ", number=" + number + ", playerId=" + playerId + ", isWinner="
				+ isWinner + "]";
	}

	
	
	

}
