package com.c2b.c2baiagent.rag;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetriever;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetrieverOptions;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: ParentChildAppRagCloudAdvisorConfig
 * Package: com.c2b.c2baiagent.rag
 * Description: 初始化基于云知识库的检索增强顾问 Bean
 *
 * @Author lzy
 */
@Configuration
public class ParentChildAppRagCloudAdvisorConfig {
    @Value("${spring.ai.dashscope.api-key}")
    private String dashScopeApiKey;

    @Bean
    public Advisor parentChildAppRagCloudAdvisor() {
        DashScopeApi dashScopeApi = DashScopeApi.builder().apiKey(dashScopeApiKey).build();
        final String KNOWLEDGE_INDEX = "亲子小助手"; //云知识库名称
        DocumentRetriever dashScopeDocumentRetriever = new DashScopeDocumentRetriever(dashScopeApi,
                DashScopeDocumentRetrieverOptions.builder()
                        .withIndexName(KNOWLEDGE_INDEX)
                        .build());
        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(dashScopeDocumentRetriever)
                .build();
    }
}
