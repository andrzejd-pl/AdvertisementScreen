package com.github.bosik927.controler;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Controller {

    @FXML
    public ImageView imageView;
    @FXML
    public MediaView mediaView;
    @FXML
    public WebView webView;
    @FXML
    public AnchorPane anchorPane;

    public Controller() {
    }

    @FXML
    private void initialize() {
//        imageView.setImage(readImage());
//        mediaView.setMediaPlayer(readVideo());
//        readWebsite("http://google.com");
    }

    private Image readImage() {
        BufferedImage bufferedImage = null;
        File f = null;

        //read image
        try {
            f = new File("C:\\Users\\Bosik\\IdeaProjects\\JavaFxSampleProjec\\src\\sample\\krawczyk.jpg"); //image file path
            bufferedImage = new BufferedImage(815, 568, BufferedImage.TYPE_INT_ARGB);
            bufferedImage = ImageIO.read(f);
            System.out.println("Reading complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    private MediaPlayer readVideo() {
        Media media = null;
        try{
            media = new Media(Paths.get("C:\\Users\\Bosik\\IdeaProjects\\PT\\src\\sample\\sampleVideo2.mp4").toUri().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);

            return mediaPlayer;
        }catch (Exception e){
            System.out.println("Problem with reading video");
        }
        return null;
    }

    void readWebsite(String path){
        WebEngine webEngine = webView.getEngine();
        webEngine.load(path);
    }

}
