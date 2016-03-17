package br.com.creapix.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static final String MAIN_SCREEN = "main";
	public static final String MAIN_SCREEN_FXML = "main.fxml";
	public static final String CADASTRO_SCREEN = "cadastro";
	public static final String CADASTRO_SCREEN_FXML = "cadastro.fxml";
	public static final String WEBCAM_SCREEN = "WebCamPreviewController";
	public static final String WEBCAM_SCREEN_FXML = "webcam.fxml";

	@Override
	public void start(Stage primaryStage) {

		ScreensController mainContainer = new ScreensController();
		mainContainer.loadScreen(Main.MAIN_SCREEN, Main.MAIN_SCREEN_FXML);
		mainContainer.loadScreen(Main.CADASTRO_SCREEN, Main.CADASTRO_SCREEN_FXML);
		mainContainer.loadScreen(Main.WEBCAM_SCREEN, Main.WEBCAM_SCREEN_FXML);

		mainContainer.setScreen(Main.MAIN_SCREEN);

		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Cadastro Rfid - www.creapix.com.br");
		primaryStage.centerOnScreen();
		primaryStage.show();
	}
}
