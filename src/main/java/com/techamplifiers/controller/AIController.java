package com.techamplifiers.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techamplifiers.service.ChatService;
import com.techamplifiers.service.ImageService;
import com.techamplifiers.service.SpeechService;

@RestController // Controller vs RestController
public class AIController {

	public ChatService chatService;
	public SpeechService speechService;
	public ImageService imageService;

	public AIController(ChatService chatService, ImageService imageService, SpeechService speechService) {
		this.chatService = chatService;
		this.imageService = imageService;
		this.speechService = speechService;
	}

	@GetMapping("/hello") // Get Method
	public String hello() {
		return "Hello from Spring AI Demo Application!";
	}

	@GetMapping("/ask-ai")
	public String getResponse(@RequestBody String prompt) {
		return chatService.getChatResponse(prompt);
	}

	@GetMapping("/generate-audio")
	public String generateAudio(@RequestBody String prompt) {
		return speechService.convertTextToSpeech(prompt);
	}

	@GetMapping("/generate-image")
	public String generateImage(@RequestBody String prompt) {
		return imageService.generateImage(prompt);
	}

}
