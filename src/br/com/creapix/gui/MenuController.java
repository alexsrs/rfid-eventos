package br.com.creapix.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

	static ScreensController myController;

	public MenuController() {
		super();
	}

	@FXML
	private void goToMain(ActionEvent event) {
		myController.setScreen(Main.MAIN_SCREEN);

	}

	@FXML
	private void goToCadastro(ActionEvent event) {
		myController.setScreen(Main.CADASTRO_SCREEN);
		myController.getChildren().add(CadastroController.imgFoto);
	}

	@FXML
	private void goToWebCam(ActionEvent event) {
		myController.setScreen(Main.WEBCAM_SCREEN);
		// myController.getChildren().remove(CadastroController.imgFoto);
	}

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

}