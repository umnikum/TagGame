package com.little_company.tag_game;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author umnikum
 *
 */

public class TurnMemory {

	List<String> memory;
	private int last;
	
	/**
	 * Constructor create an instance of {@link ArrayList} and specify amount of elements to zero
	 */
	public TurnMemory() {
		memory = new ArrayList<String>();
		last = 0;
	}
	
	/**
	 * Adds new element to storage
	 * @param direction integer value representing four standard directions starting from right counterclockwise
	 */
	public void add(int direction) {
		if((direction >= 0)&&(direction < 4)) {
			switch(direction) {
			case 0:
				memory.add("right");
				last++;
				break;
			case 1:
				memory.add("up");
				last++;
				break;
			case 2:
				memory.add("left");
				last++;
				break;
			case 3:
				memory.add("down");
				last++;
				break;
			}
		}
	}
	
	/**
	 * Return last instance of stored values
	 * @return integer value representing four standard directions starting from right counterclockwise
	 */
	public int getLast() {
		if(last > 0) {
			switch(memory.get(last - 1)) {
			case "right":
				return 0;
			case "up":
				return 1;
			case "left":
				return 2;
			case "down":
				return 3;
			default: return -1;
			}
		}else return -1;
	}
	
	/**
	 * Remove last instance of stored values
	 * @return true if more than one turn performed and false otherwise
	 */
	public boolean undo() {
		if(last > 0) {
			memory.remove(last - 1);
			last--;
			return true;
		}else return false;
	}

	/**
	 * Return number of stored elements
	 * @return number of stored elements which higher then last index by one
	 */
	public int size() {
		return last;
	}
	
}
