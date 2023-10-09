package org.example.Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * The TrainingAsset-class contains a word-image reference
 * It is used in the Worttrainer to show an image and compare the user-input to the correct spelling
 * of the corresponding word.
 * @author Tobias Janits
 * @version 08-10-2023 | dd-mm-yyyy
 */
public class TrainingAsset {
    private URL imageURL;
    private BufferedImage image;
    private String word;

    /**
     * constructor
     * initializes a new word-image asset
     */
    public TrainingAsset() {}

    /**
     * Method used to add values to the asset after its creation
     * @param imageURL URL to the image
     * @param word the word describing the image
     * @return true if the values were set correctly | false if one or both of the
     * values are not as they should be
     */
    public boolean setValues(String imageURL, String word) {
        try {
            if (this.checkURL(imageURL)) {
                this.imageURL = new URL(imageURL);
            } else {
                return false;
            }
        } catch (MalformedURLException e) {
            System.out.println("Asset could not be created");

        }
        if(this.checkWordNull(word)) {
            this.word = word;
        } else {
            return false;
        }

        //creating a tmp-image to then resize it and save the one in correct size
        BufferedImage tmp;
        try {
            tmp = ImageIO.read(this.imageURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //resizing the image to 200x200 px
        int generalize = 200;
        this.image = new BufferedImage(generalize, generalize, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = this.image.createGraphics();
        graphics2D.drawImage(this.image, 0, 0, generalize, generalize, null);
        graphics2D.dispose();

        return true;
    }

    /**
     * returns the URL to the image
     * @return URL of the image
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * returns the correctly spelled word
     * @return the word describing the image
     */
    public String getWord() {
        return this.word;
    }

    /**
     * checks the URL
     * checks the URL itself as well as the possibility of the String bein null
     * @param url URL to the image
     * @return true if valid | false if not
     */
    private boolean checkURL(String url) {
        try {
            new URL(url).toURI();
            System.out.println("URL-check passed");
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            System.out.println("URL-check failed");
            return false;
        }
    }

    /**
     * checks for null value in the word paramter
     * @param word correctly spelled word
     * @return true if not-null | false if null
     */
    private boolean checkWordNull(String word) {
        if(word == null || word == "") {
            return false;
        }
        return true;
    }
}
