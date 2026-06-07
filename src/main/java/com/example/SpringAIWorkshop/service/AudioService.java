package com.example.SpringAIWorkshop.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.audio.tts.TextToSpeechResponse;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.stereotype.Service;

@Service
public class AudioService {
	
	private final OpenAiAudioSpeechModel audioModel;
	public AudioService(OpenAiAudioSpeechModel audioModel) {
		this.audioModel = audioModel;
	}
	
	public String convertTextToSpeech(String text) {
		
		OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
			    .model("gpt-4o-mini-tts")
			    .voice(OpenAiAudioSpeechOptions.Voice.BALLAD)
			    .responseFormat(OpenAiAudioSpeechOptions.AudioResponseFormat.MP3)
			    .speed(1.0)
			    .build();
		
		TextToSpeechPrompt speechPrompt = new TextToSpeechPrompt(text, speechOptions);
		TextToSpeechResponse response = audioModel.call(speechPrompt);
		
		byte[] audio = response.getResult().getOutput();
		
		try (FileOutputStream f = new FileOutputStream("June06Audio.mp3")) {
			f.write(audio);
			System.out.println("Audio file saved as output.mp3");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Audio generation successful. Check mp3 file.";
	}
}
