package com.little_company.tag_game;

public class Board {

	protected int size;
	protected Tag[][] board;
	protected Tag current;
	
	public Board(int size) {
		this.size = size;
		board = new Tag[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if((i == size - 1) && (i == j)) {
					board[i][j] = new Tag(i, j, "");
					current = board[i][j];
				}else	board[i][j] = new Tag(i, j, "" + (i + 1 + (j * size)));
			}
		}
	}
	
	protected boolean genericTurn(int direction) {
		switch(direction) {
			case 0:
				if(current.getX() < size - 1) {
					current.swap(board[current.getX() + 1][current.getY()]);
					board[current.getX() + 1][current.getY()].abnull();
					current = board[current.getX() + 1][current.getY()];
					return true;
				}else return false;
			case 1:
				if(current.getY() > 0) {
					current.swap(board[current.getX()][current.getY() - 1]);
					board[current.getX()][current.getY() - 1].abnull();
					current = board[current.getX()][current.getY() - 1];
					return true;
				}else return false;
			case 2:
				if(current.getX() > 0) {
					current.swap(board[current.getX() - 1][current.getY()]);
					board[current.getX() - 1][current.getY()].abnull();
					current = board[current.getX() - 1][current.getY()];
					return true;
				}else return false;
			case 3:
				if(current.getY() < size - 1) {
					current.swap(board[current.getX()][current.getY() + 1]);
					board[current.getX()][current.getY() + 1].abnull();
					current = board[current.getX()][current.getY() + 1];
					return true;
				}else return false;
			default: return false;
		}
	}
	
	public void reverseTurn(int direction) {
		switch(direction) {
			case 0:
				if(current.getX() > 0) {
					current.swap(board[current.getX() - 1][current.getY()]);
					board[current.getX() - 1][current.getY()].abnull();
					current = board[current.getX() - 1][current.getY()];
				}else 
				break;
			case 1:
				if(current.getY() < size - 1) {
					current.swap(board[current.getX()][current.getY() + 1]);
					board[current.getX()][current.getY() + 1].abnull();
					current = board[current.getX()][current.getY() + 1];
				}
				break;
			case 2:
				if(current.getX() < size - 1) {
					current.swap(board[current.getX() + 1][current.getY()]);
					board[current.getX() + 1][current.getY()].abnull();
					current = board[current.getX() + 1][current.getY()];
				}
				break;
			case 3:
				if(current.getY() > 0) {
					current.swap(board[current.getX()][current.getY() - 1]);
					board[current.getX()][current.getY() - 1].abnull();
					current = board[current.getX()][current.getY() - 1];
				}
				break;
		}
	}
	
	public boolean isCorrect() {
		boolean state = true;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if((i == size - 1) && (i == j)) {
					if(!(board[i][j].value().equals(""))) {
						state = false;
					}
				}else if(!(board[i][j].value().equals("" + (i + 1 + (j * size))))) {
					state = false;
					break;
				}
			}
		}
		return state;
	}
	
	public Tag[][] getBoard(){
		return board;
	}
	
	public int size() {
		return size;
	}
}
