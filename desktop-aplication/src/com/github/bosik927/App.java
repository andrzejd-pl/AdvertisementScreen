package com.github.bosik927;

import com.github.bosik927.model.advertisement.control.AdvertisementThread;
import com.github.bosik927.model.news.control.NewsThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
        primaryStage.setTitle("Advertisement application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Thread advertisementThread = new Thread(new AdvertisementThread(root));
        advertisementThread.start();

        Thread newsThread = new Thread(new NewsThread(root));
        newsThread.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}