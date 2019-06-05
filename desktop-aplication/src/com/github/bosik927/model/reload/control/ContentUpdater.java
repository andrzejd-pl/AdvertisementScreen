package com.github.bosik927.model.reload.control;

import com.github.bosik927.model.advertisement.control.AdvertisementThread;
import com.github.bosik927.model.news.control.NewsThread;
import com.github.bosik927.model.queue.control.AdvertisementQueueCreator;
import com.github.bosik927.model.queue.control.NewsQueueCreator;
import com.github.bosik927.model.readers.control.AdvertisementReader;
import com.github.bosik927.model.readers.control.NewsReader;
import javafx.application.Platform;
import javafx.scene.Parent;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

//TODO: Create message dictionary
public class ContentUpdater implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ContentUpdater.class.getName());
    private static final int RELOADING_TIME = 1;
    private Parent root;
    private Long lastModifiedNewsFile;
    private Long lastModifiedAdvertisementFile;


    public ContentUpdater(Parent root) {
        this.root = root;
    }

    @Override
    public void run() {
        try {
            initLastModified();
            while (true) {
                TimeUnit.SECONDS.sleep(RELOADING_TIME);
                updateNews();
                updateAdvertisement();
                updateScreen(root);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initLastModified() {
        lastModifiedNewsFile = new File(NewsReader.NEWS_FILE_PATH).lastModified();
        lastModifiedAdvertisementFile = new File(AdvertisementReader.ADVERTISEMENT_FILE_PATH).lastModified();
    }


    private void updateNews() {
        Long currentLastModifiedNewsFile = new File(NewsReader.NEWS_FILE_PATH).lastModified();
        if (!lastModifiedNewsFile.equals(currentLastModifiedNewsFile)) {
            lastModifiedNewsFile = currentLastModifiedNewsFile;
            NewsThread.newsQueue = NewsQueueCreator.get();
            LOGGER.log(Level.INFO, "Updated News!", new java.util.Date());
        }
    }

    private void updateAdvertisement() {
        Long currentLastModifiedAdvertisementFile = new File(AdvertisementReader.ADVERTISEMENT_FILE_PATH).lastModified();
        if (!lastModifiedAdvertisementFile.equals(currentLastModifiedAdvertisementFile)) {
            lastModifiedAdvertisementFile = currentLastModifiedAdvertisementFile;
            AdvertisementThread.advertisements = AdvertisementQueueCreator.get();
            LOGGER.log(Level.INFO, "Updated Advertisement!", new java.util.Date());
        }
    }

    private void updateScreen(Parent root){
        Platform.runLater(new SnapUpdaterThread(root));
    }
}