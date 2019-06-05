package com.github.bosik927.model.reload.control;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SnapUpdaterThread implements Runnable {

    private static final String SNAP_PATH="screen.png";
    private Parent root;

    public SnapUpdaterThread(Parent root) {
        this.root = root;
    }

    @Override
    public void run() {
        VBox window = (VBox) root.lookup("#window");
        WritableImage image = window.snapshot(new SnapshotParameters(), null);
        File file = new File(SNAP_PATH);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
//            TODO: Add logger
        }
    }
}