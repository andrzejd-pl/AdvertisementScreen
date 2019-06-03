package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        primaryStage.setTitle("My Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Thread advertisementThread = new Thread(new AdvertisementThread("Dupa", root));
        advertisementThread.start();

        Thread newsThread = new Thread(new NewsThread(root));
        newsThread.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}