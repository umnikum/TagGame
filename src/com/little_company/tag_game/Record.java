package com.little_company.tag_game;

public class Record {

	private String name;
	private int score;
	
	public Record(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean moreThen(Record record) {
		if(score > record.getScore()) {
			return true;
		}else return false;
	}
}
