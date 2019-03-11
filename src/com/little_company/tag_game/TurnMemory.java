package com.little_company.tag_game;

import java.util.ArrayList;
import java.util.List;

public class TurnMemory {

	List<String> memory;
	private int last;
	
	public TurnMemory() {
		memory = new ArrayList<String>();
		last = 0;
	}
	
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
			default: break;
			}
		}
	}
	
	public int getLast() {
		switch(memory.get(last)) {
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
	}
	
	public void undo() {
		if(last > 0) {
			memory.remove(last - 1);
			last--;
		}
	}

	public int size() {
		return last;
	}
	
}
