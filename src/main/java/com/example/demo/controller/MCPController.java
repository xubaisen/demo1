package com.example.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/mcp")
@RestController
public class MCPController {
    private final  ChatClient chatClient;

    private final OpenAiChatModel chatModel;

    public MCPController(ToolCallbackProvider mcpTools,OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
        this.chatClient = ChatClient
                .builder(chatModel)
                .defaultTools(mcpTools.getToolCallbacks())
                .build();
    }


    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        System.out.printf("===============");
        return chatClient.prompt("北京今天天气怎么样？").call().content();
    }

}
