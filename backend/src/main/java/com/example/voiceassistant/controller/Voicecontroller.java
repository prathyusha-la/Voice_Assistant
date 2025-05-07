package com.example.voiceassistant.controller;

import com.example.voiceassistant.service.CommandProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voice")
@CrossOrigin(origins = "*") // Allow frontend access for now
public class Voicecontroller {

    @Autowired
    private CommandProcessorService commandProcessorService;

    @PostMapping("/process")
    public ResponseEntity<String> processVoice(@RequestBody String command) {
        String response = commandProcessorService.processCommand(command);
        return ResponseEntity.ok(response);
    }
}
