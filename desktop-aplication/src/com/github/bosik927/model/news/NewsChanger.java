package com.github.bosik927.model.news;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class NewsChanger implements Runnable {

    private Parent root;
    private String message;

    NewsChanger(Parent root, String message) {
        this.root = root;
        this.message = message;
    }

    @Override
    public void run() {
        Label news = (Label) root.lookup("#news");
        news.setText(message);
        System.out.println("Changed news");
    }
}