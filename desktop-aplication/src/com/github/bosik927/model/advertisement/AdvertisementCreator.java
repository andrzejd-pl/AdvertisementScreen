package com.github.bosik927.model.advertisement;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class AdvertisementCreator implements Runnable {

    private Advertisement advertisement;

    AdvertisementCreator(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    @Override
    public void run() {
        Parent root = null;
        try {
            if (advertisement.getDataType().equals("Picture")) {
                root = FXMLLoader.load(getClass().getResource("../../view/PictureAdvertisement.fxml"));
                ImageView imageView = (ImageView) root.lookup("#imageView");
                imageView.setImage(readImage(advertisement.getSource()));
            }

            if (advertisement.getDataType().equals("Video")) {
                root = FXMLLoader.load(getClass().getResource("../../view/MediaAdvertisement.fxml"));
                MediaView mediaView = (MediaView) root.lookup("#mediaView");
                mediaView.setMediaPlayer(readVideo(advertisement.getSource()));
            }

            if (advertisement.getDataType().equals("Web")) {
                root = FXMLLoader.load(getClass().getResource("../../view/WebAdvertisement.fxml"));
                WebView webView = (WebView) root.lookup("#webView");
                WebEngine webEngine = webView.getEngine();
                webEngine.load(advertisement.getSource());
            }

            Stage stage = new Stage();
            stage.setTitle("Advertisement");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with reading PictureAdvertisement.fxml file!");
        }

    }

    private Image readImage(String path) {
        BufferedImage bufferedImage = null;
        File f = null;

        //read image
        try {
            f = new File(path); //image file path
            bufferedImage = new BufferedImage(815, 568, BufferedImage.TYPE_INT_ARGB);
            bufferedImage = ImageIO.read(f);
            System.out.println("Reading complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    private MediaPlayer readVideo(String path) {
        Media media = null;
        try {
            media = new Media(Paths.get(path).toUri().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);

            return mediaPlayer;
        } catch (Exception e) {
            System.out.println("Problem with reading video");
        }
        return null;
    }
}