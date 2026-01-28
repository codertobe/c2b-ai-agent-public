package com.c2b.c2baiagent.rag;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;

/**
 * ClassName: ParentChildAppContextualQueryAugmenterFactory
 * Package: com.c2b.c2baiagent.rag
 * Description:
 *
 * @Author lzy
 */
public class ParentChildAppContextualQueryAugmenterFactory {
    public static ContextualQueryAugmenter createInstance() {
        PromptTemplate emptyContextPromptTemplate = new PromptTemplate("""
                你应该输出下面的内容：
                抱歉，我只能回答亲子关系相关的问题，别的没办法帮到您哦，
                有问题可以联系客服 https://github.com/codertobe
                """);
        return ContextualQueryAugmenter.builder()
                .allowEmptyContext(false)
                .emptyContextPromptTemplate(emptyContextPromptTemplate)
                .build();
    }
}
