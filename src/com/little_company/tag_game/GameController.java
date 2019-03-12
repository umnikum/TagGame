package com.little_company.tag_game;

import java.util.Random;

public class GameController{

	TurnMemory memory;
	Board board;
	Tag current;
	
	public GameController(int size) {
		memory = new TurnMemory();
		board = new Board(size);
	}
	
	protected void generate() {
		Random gen = new Random();
		int maxIterations = board.size() * board.size() * board.size() * board.size();
		for(int i = 0; i < maxIterations; i++) {
			swap(gen.nextInt(4));
		}
	}
	
	public boolean swap(int direction) {
		return board.genericTurn(direction);
	}
	
	public void writeToMemory(int direction) {
		memory.add(direction);
	}
	
	public boolean undo() {
		board.reverseTurn(memory.getLast());
		memory.undo();
		return true;
	}
	
	public int turnCounter() {
		return memory.size();
	}
	
	public Tag getEmptyTag() {
		return board.current;
	}
	
	public Tag[][] getBoardState() {
		return board.getBoard();
	}
	
	public boolean isCorrect() {
		return board.isCorrect();
	}
}
