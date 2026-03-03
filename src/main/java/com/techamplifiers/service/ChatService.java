package com.techamplifiers.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private final ChatModel chatModel;

	public ChatService(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	public String getChatResponse(String prompt) {

		OpenAiChatOptions options = OpenAiChatOptions.builder()
				.model("gpt-4o")
				.maxTokens(500)
				.temperature(0.7)
				.build();

		ChatResponse response = chatModel.call(new Prompt(prompt, options));

		return response.getResult().getOutput().getText();
	}

}
