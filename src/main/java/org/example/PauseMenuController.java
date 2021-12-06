package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.controller.GameController;
import org.example.model.Game;

import java.io.IOException;
import java.net.URL;

public class PauseMenuController {
    private static Stage stage;
    private static Scene scene;
    private static boolean open;
    private static Game game = Game.getInstance();

    public static void set()
    {
        URL fxmlLocation = PrimaryController.class.getResource("pause_menu.fxml");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fxmlLocation);
        try {
            scene = new Scene(loader.load());
            stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            setOpen(true);
        }catch(IOException e){
            System.out.println(e.toString());
        }
    }

    @FXML
    public void resumeGame(){
        getStage().close();
        open = false;
        setOpen(false);
        game.pause(false);
        game.getBgMusic().play();
    }

    @FXML
    public void quitGame(){
        stage.close();
        PrimaryController.getStage().close();
    }

    public static Scene getScene() {
        return scene;
    }

    public static Stage getStage() {
        return stage;
    }

    public static boolean isOpen(){
        return open;
    }
    public static void setOpen(boolean b){
        open = b;
    }
}
