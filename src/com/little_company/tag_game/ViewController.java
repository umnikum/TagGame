package com.little_company.tag_game;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewController{
	
	@FXML
	private Label scoreLabel;
	private View ViewLink;
	private ConfigMemory memory;
	private ScheduledThreadPoolExecutor executor;
	private TimerTask task;
	private GameController controller;
	
	@FXML
	public void startNewGame() {
		executor.purge();
		ViewLink.setGameSize(memory.getSize());
		if(memory.getMode()) {
			task.unpause();
		}else {
			task.pause();
		}
		ViewLink.newGame();
		controller = ViewLink.getGameController();
		scoreLabel.textProperty().bind(controller.getScoreProperty().asString());
	}
	
	public void setGameSize(int size){
		memory.setSize(size);
	}
	
	@FXML
	public void openOptionsMenu(){
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(View.class.getResource("OptionsStage.fxml"));
            AnchorPane optionsStage = (AnchorPane) loader.load();
            
            OptionsController optionsController = loader.getController();
            optionsController.setController(this);
            optionsController.size = memory.getSize();
            optionsController.mode = memory.getMode();
            
            Stage stage = new Stage();
            optionsController.setStage(stage);
            Scene scene = new Scene(optionsStage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void setTimedMode(boolean mode) {
		memory.setMode(mode);
	}

	public void endGame() {
		controller.updateScore(GameController.WIN);
		task.pause();
	}
	
	public void setView(View link) {
		ViewLink = link;
		controller = link.getGameController();
		memory = new ConfigMemory();
	}
	
	public void initializeExecutor() {
		executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);
		task = new TimerTask(scoreLabel);
		scoreLabel.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		controller.updateScore(GameController.TIME);
        		event.consume();
        	}
        });
		executor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				Platform.runLater(task);
			}
		}, 1, 1, TimeUnit.SECONDS);
	}
	
	public void shutdownExecutor() {
		executor.shutdownNow();
	}
}
