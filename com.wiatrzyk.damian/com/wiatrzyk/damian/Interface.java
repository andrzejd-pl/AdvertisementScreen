package com.wiatrzyk.damian;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

class Interface {
    public byte[] getVideo(String path) throws IOException {
        File file = new File(path);


        FileInputStream fileInputStream = null;
        byte[] bFile = new byte[(int) file.length()];

        fileInputStream = new FileInputStream(file);
        fileInputStream.read(bFile);
        fileInputStream.close();

        return bFile;
    }

    public byte[] getImage(String path) throws IOException {
        BufferedImage image = ImageIO.read(new File("image.png"));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);

        byte[] source = outputStream.toByteArray();

        return source;
    }

    public byte[] getWWW(String url) throws IOException {

        String site = "";
        byte[] siteBytes;
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            site = site + inputLine;
        }
        in.close();
        siteBytes = site.getBytes();

        return siteBytes;
    }
}