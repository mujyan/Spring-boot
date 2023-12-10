package me.mujyan.springbootdeveloper.controller;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mujyan.springbootdeveloper.service.ChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/chat-gpt")
public class ChatGPTController {
    private final ChatService chatService;
    private final ChatgptService chatgptService;

    // 요청 시 Body에 question: {query} 형태의 데이터가 존재하여야한다. ex) { "question": "안녕 1+5 의 정답은 뭐야?" }
    @PostMapping("")
    public String test(@RequestBody String question) {
        return chatService.getChatResponse(question);
    }
}