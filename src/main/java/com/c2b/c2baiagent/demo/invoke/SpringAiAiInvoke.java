package com.c2b.c2baiagent.demo.invoke;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName: SpringAiAiInvoke
 * Package: com.c2b.c2baiagent.demo.invoke
 * Description: Spring AI调用示例
 *
 * @Author lzy
 */

//@Component // 项目启动时会执行（实现了CommandLineRunner接口）
public class SpringAiAiInvoke implements CommandLineRunner {
    @Resource
    private ChatModel dashscopeChatModel;

    @Override
    public void run(String... args) throws Exception {

        SystemMessage systemMsg = new SystemMessage("你是武志红。");
        UserMessage userMsg = new UserMessage("三十而立");

        // 与聊天模型一起使用
        List<org.springframework.ai.chat.messages.Message> messages = List.of(systemMsg, userMsg);
        Prompt prompt = new Prompt(messages);
        ChatResponse response = dashscopeChatModel.call(prompt);  // 返回 ChatResponse，包含 AssistantMessage
        AssistantMessage assistantMessage = response.getResult().getOutput();
        System.out.println(assistantMessage);
    }
}
