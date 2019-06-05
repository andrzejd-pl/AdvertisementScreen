package com.github.bosik927.model.readers.control;

import com.github.bosik927.model.news.entity.News;
import com.github.bosik927.model.readers.entity.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewsReader {

    //    FIXME: Create relative path
    public static final String NEWS_FILE_PATH = "C:\\Users\\Bosik\\Desktop\\repositories\\Przemek4\\AdvertisementScreen\\desktop-aplication\\src\\com\\github\\bosik927\\model\\readers\\control\\news.txt";
    private static final FileReader FILE_READER = new FileReader();

    public List<News> read(){
        String content = FILE_READER.read(NEWS_FILE_PATH);
        Type listNews = new TypeToken<ArrayList<News>>(){}.getType();
        return new Gson().fromJson(content, listNews);
    }
}