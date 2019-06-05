package com.github.bosik927.model.queue.control;

import com.github.bosik927.model.news.entity.News;
import com.github.bosik927.model.readers.control.NewsReader;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NewsQueueCreator {

    public static Queue<News> get(){
        List<News> news = new NewsReader().read();
        news.sort(Comparator.comparing(News::getOrder));
        return new LinkedList<>(news);
    }
}