package com.github.bosik927.model.advertisement.control;

import com.github.bosik927.model.advertisement.entity.Advertisement;
import javafx.application.Platform;
import javafx.scene.Parent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

//TODO: Add logger
public class AdvertisementThread implements Runnable {

    public static Queue<Advertisement> advertisements = new LinkedList<>();
    private Parent root;

    public AdvertisementThread(Parent root) {
        this.root = root;
    }

    public void run() {
        while (true) {
            try {
                Advertisement advertisement = advertisements.remove();
                advertisements.add(advertisement);
                Platform.runLater(new AdvertisementUICreator(advertisement, root));
                TimeUnit.SECONDS.sleep(advertisement.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}