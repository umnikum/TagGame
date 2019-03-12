package com.little_company.tag_game;

import javafx.scene.control.Button;

/**
 * 
 * @author umnikum
 *
 */

public class Tag {

	protected int x, y;
	protected String data;
	private Button viewLink;
	
	/**
	 * Constructor fill basic properties of tag container for usage by the model class {@link Board}
	 * @param x horizontal coordinate
	 * @param y vertical coordinate
	 * @param data number that is written on the tag
	 */
	public Tag(int x, int y, String data) {
		this.x = x;
		this.y = y;
		this.data = data;
	}
	
	/**
	 * This method changes number, changing tag stored at (x, y) position in a process
	 * @param tag Link to another tag that should be presented at current position
	 */
	public void swap(Tag tag) {
		data = tag.value();
	}
	
	/**
	 * This method changes tag to special empty state
	 */
	public void abnull() {
		data = "";
	}
	
	/**
	 * Provide value of unchangeable horizontal coordinate
	 * @return x coordinate of the tag
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Provide value of unchangeable vertical coordinate
	 * @return y coordinate of the tag
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Set link to representation of stored tag
	 * @param link
	 */
	public void setViewLink(Button link) {
		viewLink = link;
	}
	
	public Button getView() {
		return viewLink;
	}
	
	/**
	 * Provide state currently stored by the tag
	 * @return number of stored tag
	 */
	public String value() {
		return data;
	}
}
