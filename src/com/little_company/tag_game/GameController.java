package com.little_company.tag_game;

import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * 
 * @author umnikum
 *
 */

public class GameController{

	private TurnMemory memory;
	private Board board;
	Tag current;
	private IntegerProperty score;
	public static final int START = 0, WIN = 1, TURN = 2, UNDO = 3, TIME = 4;
	
	/**
	 * Constructor initialize {@link TurnMemory} and {@link Board} for later use
	 * @param size size of called board
	 */
	public GameController(int size) {
		memory = new TurnMemory();
		board = new Board(size);
		score = new SimpleIntegerProperty();
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
	
	public void updateScore(int reason) {
		int size = board.size();
		switch(reason) {
		case TURN:
			score.set(score.get() - 10);
			break;
		case UNDO:
			score.set(score.get() + 5);
			break;
		case TIME:
			score.set(score.get() - 1);
			break;
		case WIN:
			score.set(score.get() + 1000 * size * size);
			break;
		case START:
			score.set(1000 * size * size);
		}
	}
	
	public int getScore() {
		return score.get();
	}
	
	public IntegerProperty getScoreProperty() {
		return score;
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
