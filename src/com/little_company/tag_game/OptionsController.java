package com.little_company.tag_game;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class OptionsController {

	@FXML
	private Slider sizeSlider;
	@FXML
	private CheckBox modeBox;
	protected boolean mode;
	@FXML
	protected int size;
	private boolean changed = false;
	private ViewController controller;
	private Stage optionsStage;
	
	@FXML
	private void onSliderChanged(){
		size = (int)sizeSlider.getValue();
		changed = true;
	}
	
	@FXML
	private void onCheckboxChanged() {
		mode = !mode;
		changed = true;
	}
	
	@FXML
	public void saveChanges() {
		if(changed) {
			controller.setTimedMode(mode);
			controller.setGameSize(size);
			controller.startNewGame();
		}
		optionsStage.close();
	}
	
	public void setController(ViewController link) {
		controller = link;
	}
	
	public void setStage(Stage stage) {
		optionsStage = stage;
		modeBox.setSelected(mode);
		sizeSlider.setValue((double)size);
	}
	
}
