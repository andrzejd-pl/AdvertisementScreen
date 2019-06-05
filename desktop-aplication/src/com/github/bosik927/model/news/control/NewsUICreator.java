package com.github.bosik927.model.news.control;

import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NewsUICreator implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(NewsUICreator.class.getName());
    private Parent root;
    private String message;

    NewsUICreator(Parent root, String message) {
        this.root = root;
        this.message = message;
    }

    @Override
    public void run() {
        Label news = (Label) root.lookup("#news");
        news.setText(message);
        LOGGER.log(Level.INFO, "Changed news to new to: " + message);
    }
}