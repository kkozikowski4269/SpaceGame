package org.example.controller;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

public class Sound {
    public static AudioClip playSound(String file, double volume, boolean loop){
        if(loop){
            Media media = new Media(new File(file).toURI().toString());
            AudioClip audioClip = new AudioClip(media.getSource());
            audioClip.setVolume(volume);
            audioClip.setCycleCount(AudioClip.INDEFINITE);
            audioClip.play();
            return audioClip;
        }else{
            Media media = new Media(new File(file).toURI().toString());
            AudioClip audioClip = new AudioClip(media.getSource());
            audioClip.setVolume(volume);
            audioClip.play();
            return audioClip;
        }
    }
}
