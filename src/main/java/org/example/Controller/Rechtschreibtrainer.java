package org.example.Controller;

import org.example.Objects.Statistic;
import org.example.Objects.TrainingAsset;
import org.example.SaveAndLoad.JsonIO;
import org.example.SaveAndLoad.SaveAndLoad;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Rechtschreibtrainer {
    private final ArrayList<TrainingAsset> assets;
    private final Statistic stats;

    /**
     * constructor method
     * Initializes a new trainer-game
     */
    public Rechtschreibtrainer() {
        this.assets = new ArrayList<>();
        this.stats = new Statistic();
    }

    /**
     * Allows the Player to add a new asset to the array
     */
    public void addAsset() {
        boolean check;
        do {
            String imageURL = JOptionPane.showInputDialog("Provide the link to the image of your new word:");
            String word;
            String test;
            do {
                word = JOptionPane.showInputDialog("Write the new word in the box below (make sure it is correctly spelled!)");
                test = JOptionPane.showInputDialog("Write the word again to make sure its spelled correctly:");
            } while (!word.equals(test));

            TrainingAsset nAsset = new TrainingAsset();
            if (nAsset.setValues(imageURL, word)) {
                this.assets.add(nAsset);
                check = true;
            } else {
                check = false;
            }
        } while (!check);
    }

    public boolean play() {
        while(true) {
            StringBuilder stB = new StringBuilder();
            stB.append("Gib den Nummer des Wortes ein mit dem du starten möchtest oder schreibe 'z' um ein zufälliges Wort zu wählen.");
            stB.append("\nMit 'reset' kannst du deinen aktuellen Spielstand zurücksetzen.");
            stB.append("\nUm das Spiel zu beenden schreibe 'end'.");
            String userInput = JOptionPane.showInputDialog(stB.toString());

            if(userInput == null) {
                JOptionPane.showMessageDialog(null,"Du hast nichts eingegeben, das Spiel wird abgebrochen!");
                return false;
            }

            if(userInput.equals("end")) {
                if(this.saveProgressAsFile()) {
                    JOptionPane.showMessageDialog(null, "Spiel wurde beendet, Spielstand gespeichert!");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Spiel wurde beendet, Spielstand konnte nicht gespeichert werden!");
                    return false;
                }
            }

            if(userInput.equals("z")) {
                Random ran = new Random();
                int index = ran.nextInt(this.assets.size());
                TrainingAsset asset = this.assets.get(index);

                this.displayQuiz(asset);
            }

            if(this.isStringInt(userInput)) {
                int index = Integer.parseInt(userInput);
                TrainingAsset asset = this.assets.get(index);

                this.displayQuiz(asset);
            }
        }
    }

    private void displayQuiz(TrainingAsset asset) {
        String userInput = JOptionPane.showInputDialog(null, new ImageIcon(asset.getImage()));

        if(userInput.equals(asset.getWord())) {
            this.stats.addCorrectAnswer();
            JOptionPane.showMessageDialog(null,"Toll gemacht!\n" + this.stats.retreiveStatsString());
        } else {
            this.stats.addWrongAnswer();
            JOptionPane.showMessageDialog(null,"Leider falsch. Probiers nochmal...");
            displayQuiz(asset);
        }
    }

    private boolean saveProgressAsFile() {
        SaveAndLoad serializer = new JsonIO();
        boolean test = serializer.saveStatsAsFile(this);
        if(test) {
            return true;
        }
        return false;
    }

    public boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
