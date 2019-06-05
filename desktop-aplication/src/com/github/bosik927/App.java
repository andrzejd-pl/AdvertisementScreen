package com.github.bosik927;

import com.github.bosik927.model.advertisement.control.AdvertisementThread;
import com.github.bosik927.model.news.control.NewsThread;
import com.github.bosik927.model.queue.control.AdvertisementQueueCreator;
import com.github.bosik927.model.queue.control.NewsQueueCreator;
import com.github.bosik927.model.reload.control.ContentUpdater;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private List<Thread> threads = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
        primaryStage.setTitle("Advertisement application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        initialization();
        Thread advertisementThread = new Thread(new AdvertisementThread());
        advertisementThread.start();
        threads.add(advertisementThread);

        Thread newsThread = new Thread(new NewsThread(root));
        newsThread.start();
        threads.add(newsThread);

        Thread contentUpdater = new Thread(new ContentUpdater(root));
        contentUpdater.start();
        threads.add(contentUpdater);
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void initialization() {
        NewsThread.newsQueue = NewsQueueCreator.get();
        AdvertisementThread.advertisements = AdvertisementQueueCreator.get();
    }

    @Override
    public void stop() {
        threads.forEach(Thread::stop);
    }
}