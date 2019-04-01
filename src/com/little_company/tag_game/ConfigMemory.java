package com.little_company.tag_game;

public class ConfigMemory{

	private boolean mode;
	private int size;
	
	public ConfigMemory() {
		mode = false;
		size = 3;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setMode(boolean mode) {
		this.mode = mode;
	}
	
	public boolean getMode() {
		return mode;
	}
}
