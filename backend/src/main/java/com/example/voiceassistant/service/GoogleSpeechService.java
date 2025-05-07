package com.example.voiceassistant.service;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleSpeechService {

    public String recognizeFromAudio(byte[] audioBytes) throws IOException {
        try (SpeechClient speechClient = SpeechClient.create()) {
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                    .setLanguageCode("en-US")
                    .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(ByteString.copyFrom(audioBytes))
                    .build();

            RecognizeResponse response = speechClient.recognize(config, audio);

            return response.getResultsList().isEmpty()
                    ? "Sorry, no speech detected."
                    : response.getResults(0).getAlternatives(0).getTranscript();
        }
    }
}
