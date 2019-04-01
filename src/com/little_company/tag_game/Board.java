package com.little_company.tag_game;

/**
 * 
 * @author umnikum
 *
 */

public class Board {

	protected int size;
	protected Tag[][] board;
	protected Tag current;
	
	/**
	 * Constructor generates finished state of the board of specified size filled with {@link Tag} elements
	 * @param size size of called board
	 */
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
	
	/**
	 * Depending on relative position of special empty tag swap it with specified by direction neighbor then {@link Tag#abnull()} that neighbor
	 * @param direction integer value representing four standard directions starting from right counterclockwise
	 * @return true if action was correct and was performed and false otherwise
	 */
	protected boolean genericTurn(int direction) {
		int x = current.getX(), y = current.getY();
		switch(direction) {
			case 0:
				if(current.getX() < size - 1) {
					current.swap(board[x + 1][y]);
					board[x + 1][y].abnull();
					current = board[x + 1][y];
					return true;
				}else return false;
			case 1:
				if(current.getY() > 0) {
					current.swap(board[x][y - 1]);
					board[x][y - 1].abnull();
					current = board[x][y - 1];
					return true;
				}else return false;
			case 2:
				if(current.getX() > 0) {
					current.swap(board[x - 1][y]);
					board[x - 1][y].abnull();
					current = board[x - 1][y];
					return true;
				}else return false;
			case 3:
				if(current.getY() < size - 1) {
					current.swap(board[x][y + 1]);
					board[x][y + 1].abnull();
					current = board[x][y + 1];
					return true;
				}else return false;
			default: return false;
		}
	}
	
	/**
	 * This method makes reverse {@link Board#genericTurn(int)}
	 * @param direction integer value representing four standard directions starting from right counterclockwise
	 */
	public void reverseTurn(int direction) {
		int x = current.getX(), y = current.getY();
		switch(direction) {
			case 0:
				if(current.getX() > 0) {
					current.swap(board[x - 1][y]);
					board[x - 1][y].abnull();
					current = board[x - 1][y];
				}
				break;
			case 1:
				if(current.getY() < size - 1) {
					current.swap(board[x][y + 1]);
					board[x][y + 1].abnull();
					current = board[x][y + 1];
				}
				break;
			case 2:
				if(current.getX() < size - 1) {
					current.swap(board[x + 1][y]);
					board[x + 1][y].abnull();
					current = board[x + 1][y];
				}
				break;
			case 3:
				if(current.getY() > 0) {
					current.swap(board[x][y - 1]);
					board[x][y - 1].abnull();
					current = board[x][y - 1];
				}
				break;
		}
	}
	
	/**
	 * Check board state for correct order of tags
	 * @return true if correct and false otherwise
	 */
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
	
	/**
	 * Return current state of board
	 * @return {@link Tag} array of board elements
	 */
	public Tag[][] getBoard(){
		return board;
	}
	
	/**
	 * Return size of the board
	 * @return linear size of square board
	 */
	public int size() {
		return size;
	}
}
