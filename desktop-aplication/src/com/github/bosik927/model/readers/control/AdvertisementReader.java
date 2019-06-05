package com.github.bosik927.model.readers.control;

import com.github.bosik927.model.advertisement.entity.Advertisement;
import com.github.bosik927.model.readers.entity.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementReader {

//    FIXME: Create relative path
    public static final String ADVERTISEMENT_FILE_PATH = "C:\\Users\\Bosik\\Desktop\\repositories\\Przemek4\\AdvertisementScreen\\desktop-aplication\\src\\com\\github\\bosik927\\model\\readers\\control\\advertisement.txt";
    private static final FileReader FILE_READER = new FileReader();

    public List<Advertisement> read(){
        String content = FILE_READER.read(ADVERTISEMENT_FILE_PATH);
        Type advertisementList = new TypeToken<ArrayList<Advertisement>>(){}.getType();
        return new Gson().fromJson(content, advertisementList);
    }
}