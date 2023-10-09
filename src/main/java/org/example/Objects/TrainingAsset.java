package org.example.Objects;

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
    private String imageURL;
    private String word;

    /**
     * constructor
     * initializes a new word and the matching image (per URL)
     * @param imageURL URL to the image
     * @param word describes the image
     */
    public TrainingAsset(String imageURL, String word) {
        try {
            if (this.checkURL(imageURL)) {
                this.imageURL = imageURL;
            }
        } catch (MalformedURLException e) {
            System.out.println("Asset could not be created");
        }
        if(this.checkWordNull(word)) {
            this.word = word;
        }
    }

    /**
     * returns the URL to the image
     * @return URL of the image
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * returns the correctly spelled word
     * @return the word describing the image
     */
    public String getWord() {
        return word;
    }

    /**
     * checks the URL
     * checks the URL itself as well as the possibility of the String bein null
     * @param url URL to the image
     * @return true if valid | false if not
     * @throws MalformedURLException
     */
    private boolean checkURL(String url) throws MalformedURLException {
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
