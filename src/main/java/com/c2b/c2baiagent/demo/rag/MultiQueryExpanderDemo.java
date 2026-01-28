package com.c2b.c2baiagent.demo.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.preretrieval.query.expansion.MultiQueryExpander;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName: MultiQueryExpanderDemo
 * Package: com.c2b.c2baiagent.demo.rag
 * Description:
 *
 * @Author lzy
 */
@Component
public class MultiQueryExpanderDemo {

    @Resource
    private ChatClient.Builder chatClientBuilder;

    public List<Query> expand(String query) {//"How to run a Spring Boot app?"
        MultiQueryExpander queryExpander = MultiQueryExpander.builder()
                .chatClientBuilder(chatClientBuilder)
                .numberOfQueries(3)
                .build();
        List<Query> queries = queryExpander.expand(new Query(query));
        return queries;
    }

}
