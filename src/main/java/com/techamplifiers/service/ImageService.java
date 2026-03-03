package com.techamplifiers.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	OpenAiImageModel openaiImageModel;
	public ImageService(OpenAiImageModel openaiImageModel) {
		this.openaiImageModel = openaiImageModel;
	}
	
	public String generateImage(String userInput) {

		OpenAiImageOptions imageOptions = OpenAiImageOptions.builder()
                .quality("hd")
                .N(1)
                .height(1024)
                .width(1024)
                .build();

		ImageResponse response = openaiImageModel.call(new ImagePrompt(userInput, imageOptions));
		
		return response.getResult().getOutput().getUrl();
	}
}