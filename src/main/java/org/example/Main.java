package org.example;

import org.example.Controller.Rechtschreibtrainer;

public class Main {
    public static void main(String[] args) {
        Rechtschreibtrainer rechtschreibtrainer = new Rechtschreibtrainer();
        rechtschreibtrainer.addAsset();
        rechtschreibtrainer.addAsset();
        boolean test = rechtschreibtrainer.play();
        System.out.println("erg: "+test);
    }
}