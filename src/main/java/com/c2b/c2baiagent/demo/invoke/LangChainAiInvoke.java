package com.c2b.c2baiagent.demo.invoke;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.model.chat.ChatModel;

/**
 * ClassName: LangChainAiInvoke
 * Package: com.c2b.c2baiagent.demo.invoke
 * Description:LangChain调用示例
 *
 * @Author lzy
 */
public class LangChainAiInvoke {
    public static void main(String[] args) {
        ChatModel qwenModel = QwenChatModel.builder()
                .apiKey(TestApiKey.API_KEY)
                .modelName("qwen-max")
                .build();

        String answer = qwenModel.chat("Say 'Hello World'");
        System.out.println(answer); // Hello World
    }
}
