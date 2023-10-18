package org.example.SaveAndLoad;

import org.example.Controller.Rechtschreibtrainer;
import org.example.Objects.Statistic;

public interface SaveAndLoad {

    boolean saveStatsAsFile(Rechtschreibtrainer trainer);

    public Rechtschreibtrainer loadStatsFromFile();
}
