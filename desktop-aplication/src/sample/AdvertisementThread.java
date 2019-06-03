package sample;

import javafx.application.Platform;
import javafx.scene.Parent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

class AdvertisementThread implements Runnable {
    private Thread t;
    private String threadName;
    private Parent root;

    AdvertisementThread(String name, Parent parent) {
        threadName = name;
        root = parent;

        System.out.println("Creating " + threadName);
    }

    public void run() {
        System.out.println("Running " + threadName);
        Queue<Advertisement> advertisements = getAdvertisement();

        while (true) {
            try {
                TimeUnit.SECONDS.sleep(9);
                Advertisement advertisement = advertisements.remove();
                Platform.runLater(new AdvertisementCreator(advertisement));
                System.out.println("Send advertisement " + advertisement.getAdvertisementId());
                advertisements.add(advertisement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Queue<Advertisement> getAdvertisement() {
        Queue<Advertisement> advertisements = new LinkedList<>();

        Advertisement add1 = new Advertisement();
        add1.setAdvertisementId(1);
        add1.setDataType("Picture");
        add1.setName("Ads1");
        add1.setSource("C:\\Users\\Bosik\\IdeaProjects\\JavaFxSampleProjec\\src\\sample\\krawczyk.jpg");
        add1.setTime(5);
        advertisements.add(add1);

        Advertisement add2 = new Advertisement();
        add2.setAdvertisementId(2);
        add2.setDataType("Web");
        add2.setName("Ads3");
        add2.setSource("http://www.pornhub.com");
        add2.setTime(5);
        advertisements.add(add2);

        Advertisement add3 = new Advertisement();
        add3.setAdvertisementId(3);
        add3.setDataType("Video");
        add3.setName("Ads3");
        add3.setSource("C:\\Users\\Bosik\\IdeaProjects\\PT\\src\\sample\\sampleVideo2.mp4");
        add3.setTime(5);
        advertisements.add(add3);

        return advertisements;
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}