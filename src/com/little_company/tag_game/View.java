package com.little_company.tag_game;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * 
 * @author umnikum
 *
 */

public class View extends Application{

    private int size;
	private Stage primaryStage;
    private BorderPane rootLayout;
    private GameController controller;
    private GridPane gameScene;
    private ViewController viewController;
    private GameState state;
    
    /**
     * Creates basic root layout for application
     */
    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(View.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            viewController = loader.getController();
            viewController.setView(this);
            primaryStage.setScene(scene);
            viewController.initializeExecutor();
            state = new GameState();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    /**
     * Initialize new game scene
     */
    private void initGameScene() {
        gameScene = new GridPane();
        initializeTags();
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	if(state.getState() == GameState.INGAME) {
	            	Tag oldEmptyTag = controller.getEmptyTag();
		            if(makeTurn(chooseDirection(event))) {
		                correctView(oldEmptyTag);
		                if(controller.isCorrect()) endGame();
		            }
            	}
            	event.consume();
            }
        });
        rootLayout.setCenter(gameScene);
    }
    
    /**
     * Analyze {@link KeyEvent} and request for specific action
     * @param event given {@link KeyEvent}
     * @return direction integer value representing four standard directions starting from right counterclockwise
     */
    private int chooseDirection(KeyEvent event) {
    	switch (event.getCode()) {
        case A:	return 0;
        case S:	return 1;
        case D:	return 2;
        case W:	return 3;
        case R:	return 4;
        default:System.out.println(event.getCode() + " is not used by this program!");
        		return -1;
    	}
    }
    
    /**
     * Delegates turn action to {@link GameController} object
     * @param direction integer value representing four standard directions starting from right counterclockwise
     * @return true if turn was correct and was performed and false otherwise
     */
    private boolean makeTurn(int direction) {
    	boolean correctTurn = false;
    	if(direction >= 0) {
        	if(direction < 4) {
        		correctTurn = controller.swap(direction);
        		if(correctTurn) {
        			controller.updateScore(GameController.TURN);
        			controller.writeToMemory(direction);
        		}
        	}else {
        		correctTurn = controller.undo();
        		if(correctTurn) controller.updateScore(GameController.UNDO);
        	}
        }
    	return correctTurn;
    }
    
    /**
     * Corrects representation to latest state of the model
     * @param emptyTag older position of special empty tag
     */
    private void correctView(Tag emptyTag) {
    	Button viewReserve = emptyTag.getView();
    	emptyTag.setViewLink(controller.getEmptyTag().getView());
    	controller.getEmptyTag().setViewLink(viewReserve);
    	gameScene.getChildren().removeAll(emptyTag.getView(), controller.getEmptyTag().getView());
    	gameScene.add(emptyTag.getView(), emptyTag.getX(), emptyTag.getY());
    	gameScene.add(controller.getEmptyTag().getView(), controller.getEmptyTag().getX(), controller.getEmptyTag().getY());
    }
    
    /**
     * Creates {@link Button} objects representing tags and install them in game scene
     */
    private void initializeTags() {
    	Tag[][] board = controller.getBoardState();
        for(int i = 0; i < size; i++) {
        	ColumnConstraints cc = new ColumnConstraints(50, 50, Double.MAX_VALUE);
        	RowConstraints rc = new RowConstraints(50, 50, Double.MAX_VALUE);
        	cc.setHgrow(Priority.SOMETIMES);
        	rc.setVgrow(Priority.SOMETIMES);
        	gameScene.getColumnConstraints().add(cc);
        	gameScene.getRowConstraints().add(rc);
        	for(int j = 0; j < size; j++) {
        		Button button = new Button(board[i][j].value());
        		gameScene.add(button, i, j);
        		button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        		board[i][j].setViewLink(button);
        		if(board[i][j].value().equals("")) {
        			 button.setStyle("-fx-base: #ffffff;");
        		}else button.setStyle("-fx-font: 25 arial;");
        	}
        }
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Tag Game");
        initRootLayout();
	}
	
	@Override
	public void stop() {
		viewController.shutdownExecutor();
	}
	
	/**
	 * Method that finishing the game
	 */
	private void endGame() {
		state.changeTo(GameState.IDLE);
		viewController.endGame();
	}
	
	/**
	 * Setting preferred size of the board
	 * @param size linear size of square board
	 */
	public void setGameSize(int size) {
    	this.size = size;
    }
	
	/**
	 * Starts new round of the game
	 */
	public void newGame() {
		controller = new GameController(size);
		controller.generate();
		controller.updateScore(GameController.START);
		initGameScene();
		state.changeTo(GameState.INGAME);
	}
		
	public GameController getGameController() {
		return controller;
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}
