package com.github.bosik927.model.advertisement.control;

import com.github.bosik927.model.advertisement.entity.Advertisement;
import javafx.application.Platform;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

//TODO: Add logger
public class AdvertisementThread implements Runnable {

    public static Queue<Advertisement> advertisements = new LinkedList<>();

    public void run() {
        while (true) {
            try {
                Advertisement advertisement = advertisements.remove();
                advertisements.add(advertisement);
                TimeUnit.SECONDS.sleep(advertisement.getTime());
                Platform.runLater(new AdvertisementUICreator(advertisement));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}