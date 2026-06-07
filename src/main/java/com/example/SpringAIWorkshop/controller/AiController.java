package com.example.SpringAIWorkshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringAIWorkshop.service.AudioService;
import com.example.SpringAIWorkshop.service.ChatService;
import com.example.SpringAIWorkshop.service.ImageService;

@RestController
public class AiController {
	
	@Autowired
	private ChatService chatservice;
	
	@Autowired
	private AudioService audioService;
	
	@Autowired
	private ImageService imageService;
	
	@GetMapping("/ask-ai")
	public String askAi(@RequestParam String prompt) {
		return chatservice.askAI(prompt);
	}
	
	@GetMapping("/generate-audio")
	public String generateAudio(@RequestParam String text) {
		return audioService.convertTextToSpeech(text);
	}
	
	@GetMapping("/generate-image")
	public String generateImage(@RequestParam String prompt) {
		return imageService.generateImage(prompt);
	}
	
}

