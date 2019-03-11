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

public class View extends Application{

	private Stage primaryStage;
    private BorderPane rootLayout;
    private GameController gc;
    
    
	public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(View.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    public void initGameScene() {
    	int size = 4;
        gc = new GameController(size);
        gc.generate();
        Tag[][] board = gc.getBoardState();
        GridPane gameScene = new GridPane();
        
        for(int i= 0; i < size; i++) {
        	ColumnConstraints cc = new ColumnConstraints(50, 50, Double.MAX_VALUE);
        	RowConstraints rc = new RowConstraints(50, 50, Double.MAX_VALUE);
        	gameScene.getColumnConstraints().add(cc);
        	gameScene.getRowConstraints().add(rc);
        	cc.setHgrow(Priority.SOMETIMES);
        	rc.setVgrow(Priority.SOMETIMES);
        	for(int j = 0; j < size; j++) {
        		Button button= new Button(board[i][j].value());
        		gameScene.add(button, i, j);
        		button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        		board[i][j].setViewLink(button);
        		if(board[i][j].value().equals("")) {
        			 button.setStyle("-fx-base: #ffffff;");
        		}else button.setStyle("-fx-font: 25 arial;");
        	}
        }
        
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	Tag emptyTag = gc.getEmptyTag();
            	int direction = -1;
            	boolean correctTurn = false;
                switch (event.getCode()) {
                    case A:	direction = 0; break;
                    case S:	direction = 1; break;
                    case D:	direction = 2; break;
                    case W:	direction = 3; break;
                    case R:	direction = 4; break;
                    default: System.out.println(event.getCode() + " is not used by this program!");
                }
                if(direction >= 0) {
                	if(direction < 4) {
                		correctTurn = gc.swap(direction);
                		if(correctTurn) gc.writeToMemory(direction);
                	}else correctTurn = gc.undo();
                }
                if(correctTurn) {
                	Button viewReserve = emptyTag.getView();
                	emptyTag.setViewLink(gc.getEmptyTag().getView());
                	gc.getEmptyTag().setViewLink(viewReserve);
                	gameScene.getChildren().removeAll(emptyTag.getView(), gc.getEmptyTag().getView());
                	gameScene.add(emptyTag.getView(), emptyTag.getX(), emptyTag.getY());
                	gameScene.add(gc.getEmptyTag().getView(), gc.getEmptyTag().getX(), gc.getEmptyTag().getY());
                }
                if(gc.isCorrect()) endGame();
                event.consume();
            }
        });
        
        rootLayout.setCenter(gameScene);
    }
    
    public Stage getPrimaryStage() {
    	return primaryStage;
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Tag Game");
        initRootLayout();
        initGameScene();
	}
	
	public void endGame() {
		System.out.println(gc.turnCounter());
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}
