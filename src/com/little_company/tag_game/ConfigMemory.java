package com.little_company.tag_game;

import java.util.ArrayList;
import java.util.List;

public class ConfigMemory{

	private boolean mode;
	private int size;
	private String userName;
	private List<List<Record>> timedHighScores;
	private List<List<Record>> untimedHighScores;
	
	public ConfigMemory() {
		mode = false;
		size = 3;
		userName = System.getProperty("user.name");
		initializeScore();
	}

	private void initializeScore() {
		timedHighScores = new ArrayList<List<Record>>(7);
		for(int i = 3; i < 10; i++) {
			timedHighScores.add(new ArrayList<Record>(10));
		}
		untimedHighScores = new ArrayList<List<Record>>(7);
		for(int i = 3; i < 10; i++) {
			untimedHighScores.add(new ArrayList<Record>(10));
		}
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
	
	public void setUserName(String name) {
		userName = name;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public boolean addRecord(int score) {
		Record record = new Record(userName, score);
		List<Record> currentScores;
		boolean edited = false;
		if(mode) {
			currentScores = timedHighScores.get(size - 3);
		}else currentScores = untimedHighScores.get(size - 3);
		if(currentScores.size() - 1 == 0) {
			currentScores.add(record);
		}else	for(Record i:currentScores) {
					if(record.moreThen(i)) {
						currentScores.add(currentScores.indexOf(i), record);
						if(currentScores.size() > 10)	currentScores.remove(10);
						edited = true;
						break;
					}
		}
		return edited;
	}
	
	public List<Record> getScore(){
		if(mode) {
			return timedHighScores.get(size - 3);
		}else return untimedHighScores.get(size - 3);
	}
}
