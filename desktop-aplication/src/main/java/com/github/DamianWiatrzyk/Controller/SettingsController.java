package com.github.DamianWiatrzyk.Controller;

import com.github.DamianWiatrzyk.Repository.AdvertisementRepository;
import com.github.DamianWiatrzyk.Repository.NewsRepository;
import com.github.bosik927.model.advertisement.control.AdvertisementThread;
import com.github.bosik927.model.advertisement.entity.Advertisement;
import com.github.bosik927.model.news.control.NewsThread;
import com.github.bosik927.model.news.entity.News;

import java.util.List;

public class SettingsController {
    private AdvertisementThread advertisementThread;
    private NewsThread newsThread;
    private AdvertisementRepository advertisementRepository;
    private NewsRepository newsRepository;

    public AdvertisementThread getAdvertisementThread() {
        return advertisementThread;
    }

    public NewsThread getNewsThread() {
        return newsThread;
    }

    public void loadSettings() {
        List<Advertisement> advertisements = advertisementRepository.getAllAdvertisements();
        List<News> news = newsRepository.getAllNews();
    }
}
