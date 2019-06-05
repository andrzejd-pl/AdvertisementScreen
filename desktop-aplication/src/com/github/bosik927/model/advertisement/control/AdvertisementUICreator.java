package com.github.bosik927.model.advertisement.control;

import com.github.bosik927.model.advertisement.entity.Advertisement;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
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
import java.util.logging.Logger;

//TODO: Change logs, and exception handler, add ENUM vie with resourcePath, value, id
public class AdvertisementUICreator implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(AdvertisementUICreator.class.getName());
    private Advertisement advertisement;
    private Parent root;

    public AdvertisementUICreator(Advertisement advertisement, Parent root) {
        this.advertisement = advertisement;
        this.root = root;

    }

    @Override
    public void run() {
        VBox vBox = (VBox) root.lookup("#advertisement");
        vBox.getChildren().clear();

        if (advertisement.getData_type().equals("image")) {
            ImageView imageView = new ImageView();
            imageView.setImage(readImage(advertisement.getSource()));
            imageView.setSmooth(true);
            imageView.setFitHeight(vBox.getHeight());
            imageView.setFitWidth(vBox.getWidth());
            vBox.getChildren().add(imageView);
        }

        if (advertisement.getData_type().equals("video")) {
            MediaView mediaView = new MediaView();
            mediaView.setMediaPlayer(readVideo(advertisement.getSource()));
            mediaView.setSmooth(true);
            mediaView.setFitHeight(vBox.getHeight());
            mediaView.setFitWidth(vBox.getWidth());
            vBox.getChildren().add(mediaView);
        }

        if (advertisement.getData_type().equals("web")) {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            webEngine.load(advertisement.getSource());
            vBox.getChildren().add(webView);
        }

        Stage stage = new Stage();
        stage.setTitle("Advertisement");
        stage.setScene(new Scene(root));
        stage.show();

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
        Media media;
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