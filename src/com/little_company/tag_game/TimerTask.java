package com.little_company.tag_game;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventTarget;

public class TimerTask implements Runnable {

	private boolean paused;
	private EventTarget address;
	
	public TimerTask(EventTarget address) {
		paused = true;
		this.address = address;
	}
	
	@Override
	public void run() {
		if(!paused)
			Event.fireEvent(address, new ActionEvent());
	}
	
	public void pause() {
		paused = true;
	}
	
	public void unpause() {
		paused = false;
	}
}
