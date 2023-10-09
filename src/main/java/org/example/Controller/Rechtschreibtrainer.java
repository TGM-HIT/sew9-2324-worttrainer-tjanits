package org.example.Controller;

import org.example.Objects.Statistic;
import org.example.Objects.TrainingAsset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Rechtschreibtrainer {
    private ArrayList<TrainingAsset> assets;
    private Statistic stats;

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
        boolean check = false;
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
        } while (check == false);
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
                if(this.stats.saveAsFile()) {
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

            if()
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

}
