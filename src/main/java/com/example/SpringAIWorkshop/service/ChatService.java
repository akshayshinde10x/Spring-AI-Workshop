package com.example.SpringAIWorkshop.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	//private final ChatClient chatclient;
	private final ChatModel chatModel;
    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String askAI(String prompt) {
    	
    	OpenAiChatOptions options = OpenAiChatOptions.builder()
                .model("gpt-5.5")
                .maxCompletionTokens(1000)
                .temperature(1.0)
                .build();
    	
        ChatResponse response = chatModel.call(new Prompt(prompt, options));
      
        System.out.println(response);
        return response.getResult().getOutput().getText();
    }
}
