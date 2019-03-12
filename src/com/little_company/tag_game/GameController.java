package com.little_company.tag_game;

import java.util.Random;

/**
 * 
 * @author umnikum
 *
 */

public class GameController{

	TurnMemory memory;
	Board board;
	Tag current;
	
	/**
	 * Constructor initialize {@link TurnMemory} and {@link Board} for later use
	 * @param size size of called board
	 */
	public GameController(int size) {
		memory = new TurnMemory();
		board = new Board(size);
	}
	
	/**
	 * Make size^4 amount of random turns to create unfilled board state
	 */
	protected void generate() {
		Random gen = new Random();
		int maxIterations = board.size() * board.size() * board.size() * board.size();
		for(int i = 0; i < maxIterations; i++) {
			swap(gen.nextInt(4));
		}
	}
	
	/**
	 * Method that performs turn operation
	 * @param direction integer value representing four standard directions starting from right counterclockwise
	 * @return true if turn was correct and was performed
	 */
	public boolean swap(int direction) {
		return board.genericTurn(direction);
	}
	
	/**
	 * Writes specified direction of turn in {@link TurnMemory} object
	 * @param direction integer value representing four standard directions starting from right counterclockwise
	 */
	public void writeToMemory(int direction) {
		memory.add(direction);
	}
	
	/**
	 * Undo last turn written in {@link TurnMemory} instance
	 * @return true if turn was undone and false otherwise
	 */
	public boolean undo() {
		board.reverseTurn(memory.getLast());
		return memory.undo();
	}
	
	/**
	 * Return amount of performed turns
	 * @return size of {@link TurnMemory} object
	 */
	public int turnCounter() {
		return memory.size();
	}
	
	/**
	 * Return link to special empty tag
	 * @return link to special {@link Tag} object
	 */
	public Tag getEmptyTag() {
		return board.current;
	}
	
	/**
	 * Delegate request for board state to {@link Board} object
	 * @return {@link Tag} array of board elements
	 */
	public Tag[][] getBoardState() {
		return board.getBoard();
	}
	
	/**
	 * Delegate request for correct state check to {@link Board} object
	 * @return
	 */
	public boolean isCorrect() {
		return board.isCorrect();
	}
}
