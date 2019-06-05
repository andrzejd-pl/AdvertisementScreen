package com.github.bosik927.model.queue.control;

import com.github.bosik927.model.advertisement.entity.Advertisement;
import com.github.bosik927.model.readers.control.AdvertisementReader;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdvertisementQueueCreator {

    public static Queue<Advertisement> get() {
        List<Advertisement> advertisements = new AdvertisementReader().read();
        advertisements.sort(Comparator.comparing(Advertisement::getTime));
        return new LinkedList<>(advertisements);
    }
}