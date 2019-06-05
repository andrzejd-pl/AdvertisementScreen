package com.github.bosik927.model.news.control;

import com.github.bosik927.model.news.entity.News;
import javafx.application.Platform;
import javafx.scene.Parent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewsThread implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(NewsUICreator.class.getName());
    public static Queue<News> newsQueue = new LinkedList<>();
    private Parent root;

    public NewsThread(Parent root) {
        this.root = root;
    }

    @Override
    public void run() {
        while (true) {
            try {
                News message = newsQueue.remove();
                Platform.runLater(new NewsUICreator(root, message.getName()));
                newsQueue.add(message);
                System.out.println(message.getName());
                TimeUnit.SECONDS.sleep(message.getTime());
            } catch (Exception e) {
                LOGGER.log(Level.WARNING,e.getMessage(),e);
            }
        }
    }
}