package com.c2b.c2baiagent.app;

import com.c2b.c2baiagent.advisor.MyLoggerAdvisor;
import com.c2b.c2baiagent.chatmemory.FileBasedChatMemory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * ClassName: ParentChildApp
 * Package: com.c2b.c2baiagent.app
 * Description: 亲子关系小助手
 *
 * @Author lzy
 */

@Component
@Slf4j
public class ParentChildApp {

    private final ChatClient chatClient;

    private static final String SYSTEM_PROMPT = "你是资深亲子专家，精通各年龄段亲子问题。" +
            "沟通先共情，不评判，再通过逐步引导式提问深挖细节（身份、孩子年龄、具体场景、双方反应、过往应对）。" +
            "语言适配父母/孩子，建议具体可落地。" +
            "避免信息过载，一次一问，遇严重问题引导专业机构帮助。";

    /**
     * 初始化 ChatClient
     *
     * @param dashscopeChatModel
     */
    public ParentChildApp(ChatModel dashscopeChatModel) {
        // 初始化基于文件的对话记忆
        String fileDir = System.getProperty("user.dir") + "/tmp/chat-memory";
        ChatMemory chatMemory = new FileBasedChatMemory(fileDir);
        // 初始化基于内存的对话记忆
        /*MessageWindowChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(new InMemoryChatMemoryRepository())
                .maxMessages(20)
                .build();*/
        chatClient = ChatClient.builder(dashscopeChatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build(),
                        // 自定义日志 Advisor，可按需开启
                        new MyLoggerAdvisor()
                        // 自定义推理增强 Advisor，可按需开启
//                       ,new ReReadingAdvisor()
                       )
                .build();
    }

    /**
     * AI 基础对话（支持多轮对话记忆）
     *
     * @param message
     * @param chatId
     * @return
     */
    public String doChat(String message, String chatId) {
        ChatResponse response = chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .chatResponse();
        String content = response.getResult().getOutput().getText();
        log.info("content: {}", content);
        return content;
    }

    record ChatReport(String title, List<String> suggestions) {

    }
    /**
     * AI 基础对话（结构化输出报告）
     *
     * @param message
     * @param chatId
     * @return
     */
    public ChatReport doChatWithReport(String message, String chatId) {
        ChatReport chatReport = chatClient
                .prompt()
                .user(message)
                .system(SYSTEM_PROMPT + "每次对话后都要生成咨询结果，标题为{用户名}的咨询报告，内容为建议列表")
                .advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .entity(ChatReport.class);
        log.info("chatReport: {}", chatReport);
        return chatReport;
    }
    @Resource
    private VectorStore parentChildAppVectorStore;
    @Resource
    private Advisor parentChildAppRagCloudAdvisor;
    public String doChatWithRag(String message, String chatId) {
        ChatResponse chatResponse = chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, chatId))
                // 开启日志，便于观察效果
//                .advisors(new MyLoggerAdvisor())
                // 应用知识库问答
                .advisors(QuestionAnswerAdvisor.builder(parentChildAppVectorStore).build())
                // 应用增强检索服务（云知识库服务）
//                .advisors(parentChildAppRagCloudAdvisor)
                .call()
                .chatResponse();
        String content = chatResponse.getResult().getOutput().getText();
        log.info("content: {}", content);
        return content;
    }
}
