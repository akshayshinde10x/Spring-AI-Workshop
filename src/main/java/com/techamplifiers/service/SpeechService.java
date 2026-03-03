package com.techamplifiers.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.stereotype.Service;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;

@Service
public class SpeechService {

	OpenAiAudioSpeechModel openAiAudioSpeechModel;
	
	public SpeechService(OpenAiAudioSpeechModel openAiAudioSpeechModel) {
		this.openAiAudioSpeechModel = openAiAudioSpeechModel;
	}
	
	public String convertTextToSpeech(String text) {
		
		OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
			    .model("gpt-4o-mini-tts")
			    .voice(OpenAiAudioApi.SpeechRequest.Voice.SHIMMER)
			    .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
			    .speed(1.0f) //double in latest code
			    .build();
		
		SpeechPrompt speechPrompt = new SpeechPrompt(text, speechOptions);
		SpeechResponse response = openAiAudioSpeechModel.call(speechPrompt);
		
//		TextToSpeechPrompt speechPrompt = new TextToSpeechPrompt(text, speechOptions);
//		TextToSpeechResponse response = openAiAudioSpeechModel.call(speechPrompt);
		
		byte[] audio = response.getResult().getOutput();
		
		//convert byte array to mp3 file
		try (FileOutputStream f = new FileOutputStream("voice.mp3")) {
			f.write(audio);
			System.out.println("Audio file saved as output.mp3");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Audio generation successful. Check mp3 file.";
	}
}
