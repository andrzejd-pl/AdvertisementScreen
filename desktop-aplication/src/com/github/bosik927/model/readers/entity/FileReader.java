package com.github.bosik927.model.readers.entity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FileReader {

    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public String read(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.toString(), e);
        }
        return contentBuilder.toString();
    }
}