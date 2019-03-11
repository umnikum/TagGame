package com.little_company.tag_game;

import javafx.scene.control.Button;

public class Tag {

	protected int x, y;
	protected String data;
	private Button viewLink;
	
	public Tag(int x, int y, String data) {
		this.x = x;
		this.y = y;
		this.data = data;
	}
	
	public void swap(Tag tag) {
		data = tag.value();
	}
	
	public void abnull() {
		data = "";
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setViewLink(Button link) {
		viewLink = link;
	}
	
	public Button getView() {
		return viewLink;
	}
	
	public String value() {
		return data;
	}
}
