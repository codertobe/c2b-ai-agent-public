package com.c2b.c2baiagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * ClassName: ParentChildAppTest
 * Package: com.c2b.c2baiagent.app
 * Description:
 *
 * @Author lzy
 */
@SpringBootTest
class ParentChildAppTest {

    @Resource
    private ParentChildApp parentChildApp;

    @Test
    void doChat() {
        String chatId = UUID.randomUUID().toString();
        // 第一次对话
        String message = "你好，我是lzy";
        String answer = parentChildApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);
        // 第二次对话
        message = "母亲刚退休";
        answer = parentChildApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);
        // 第三次对话
        message = "我的名字是什么？你还记得不";
        answer = parentChildApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithReport() {
        String chatId = UUID.randomUUID().toString();
        String message = "你好，我是lzy，我想让母亲更爱她自己，但我不知道该怎么做";
        ParentChildApp.ChatReport chatReport = parentChildApp.doChatWithReport(message, chatId);
        Assertions.assertNotNull(chatReport);
    }
}