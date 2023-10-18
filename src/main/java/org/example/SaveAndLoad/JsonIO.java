package org.example.SaveAndLoad;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Controller.Rechtschreibtrainer;

import java.io.File;
import java.io.IOException;

public class JsonIO implements SaveAndLoad {
    private final ObjectMapper converter;
    private final String PATH = "./stat_data/progress.json";

    public JsonIO() {
        this.converter = new ObjectMapper();
    }

    @Override
    public boolean saveStatsAsFile(Rechtschreibtrainer trainer) {
        try {
            File file = new File(PATH);
            this.converter.writeValue(file, trainer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Rechtschreibtrainer loadStatsFromFile() {
        try {
            File file = new File(PATH);
            return this.converter.readValue(file, Rechtschreibtrainer.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
