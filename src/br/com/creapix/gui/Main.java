package br.com.creapix.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static final String MAIN_SCREEN = "Main";
	public static final String MAIN_SCREEN_FXML = "main.fxml";
	public static final String CADASTRO_SCREEN = "cadastro";
	public static final String CADASTRO_SCREEN_FXML = "cadastro.fxml";
	public static final String ROULETTE_SCREEN = "roulette";
	public static final String ROULETTE_SCREEN_FXML = "roulette.fxml";

	@Override
	public void start(Stage primaryStage) {

		ScreensController mainContainer = new ScreensController();
		mainContainer.loadScreen(Main.MAIN_SCREEN, Main.MAIN_SCREEN_FXML);
		mainContainer.loadScreen(Main.CADASTRO_SCREEN, Main.CADASTRO_SCREEN_FXML);
		// mainContainer.loadScreen(ScreensFramework.ROULETTE_SCREEN,
		// ScreensFramework.ROULETTE_SCREEN_FXML);

		mainContainer.setScreen(Main.MAIN_SCREEN);

		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
