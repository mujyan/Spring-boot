package me.mujyan.springbootdeveloper.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatgptService chatgptService;

    public String getChatResponse(String prompt) {
        // ChatGPT 에게 질문 전달
        return chatgptService.sendMessage(prompt);
    }
}