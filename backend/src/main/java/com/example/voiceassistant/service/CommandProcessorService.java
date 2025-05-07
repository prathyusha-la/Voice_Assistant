package com.example.voiceassistant.service;

import org.springframework.stereotype.Service;

@Service
public class CommandProcessorService {

    public String processCommand(String command) {
        command = command.toLowerCase();

        if (command.contains("weather")) {
            return "The weather today is sunny with a high of 25°C.";
        } else if (command.contains("music")) {
            return "Sure! Playing your favorite music.";
        } else {
            return "Sorry, I didn't understand that command.";
        }
    }
}
