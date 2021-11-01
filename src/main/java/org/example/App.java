package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.controller.GameController;
import org.example.model.Player;
import org.example.view.GameView;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        GameView gameView = GameView.getInstance();
        GameController gameController = new GameController();
        scene = new Scene(gameView, 1280, 720);
        stage.centerOnScreen();
        stage.setScene(scene);
        scene.setRoot(gameView);
        stage.show();
        gameController.setUp();
        gameController.run();
    }

    public static void setRoot(String fxml) throws IOException {

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}