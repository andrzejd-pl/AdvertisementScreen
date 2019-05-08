package com.dybowski.andrzej.panel.controllers;

import com.dybowski.andrzej.panel.repositories.AdvertisementRepository;
import com.dybowski.andrzej.panel.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class WebController {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;
}
