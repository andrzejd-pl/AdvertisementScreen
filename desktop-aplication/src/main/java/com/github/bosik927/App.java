package com.github.bosik927;

import com.github.DamianWiatrzyk.Controller.SettingsController;
import com.github.bosik927.model.advertisement.control.AdvertisementThread;
import com.github.bosik927.model.news.control.NewsThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static SettingsController settingsController = new SettingsController();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
        primaryStage.setTitle("Advertisement application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        AdvertisementThread advertisementController = settingsController.getAdvertisementThread();
        advertisementController.setRoot(root);
        Thread advertisementThread = new Thread(advertisementController);
        advertisementThread.start();

        NewsThread newsController = settingsController.getNewsThread();
        newsController.setRoot(root);
        Thread newsThread = new Thread(newsController);
        newsThread.start();
    }


    public static void main(String[] args) {
        settingsController.loadSettings();
        launch(args);
    }
}