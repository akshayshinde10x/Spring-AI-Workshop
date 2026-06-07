package com.example.SpringAIWorkshop.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	private final OpenAiImageModel imageModel;

	public ImageService(OpenAiImageModel imageModel) {
		this.imageModel = imageModel;
	}

	public String generateImage(String prompt) {
		OpenAiImageOptions imageOptions = OpenAiImageOptions.builder()
				.quality("auto")
				.N(1)
				.height(1024)
				.width(1024)
				.build();

		ImageResponse response = imageModel.call(new ImagePrompt(prompt, imageOptions));
		// return response.getResult().getOutput().getUrl();
		String imageString = response.getResult().getOutput().getB64Json();
		byte[] imageBytes = Base64.getDecoder().decode(imageString);

		try (FileOutputStream f = new FileOutputStream("June06Image.png")) {
			f.write(imageBytes);
			System.out.println("Image file saved as output.mp3");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Image generation successful. Check png file.";
	}

}
