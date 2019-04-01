package com.little_company.tag_game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameState {

	public static final int INGAME = 1, IDLE = 0;
	private IntegerProperty state;
	
	public GameState() {
		state = new SimpleIntegerProperty(IDLE);
	}

	public void changeTo(int state) {
		switch(state) {
		case IDLE:
		case INGAME:
			this.state.set(state);
			break;
		default: System.out.println("IllegalStateException");
		}
	}
	
	public int getState() {
		return state.getValue();
	}
}
