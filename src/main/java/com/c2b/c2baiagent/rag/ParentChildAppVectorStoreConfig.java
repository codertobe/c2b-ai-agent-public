package com.c2b.c2baiagent.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * ClassName: ParentChildAppVectorStoreConfig
 * Package: com.c2b.c2baiagent.rag
 * Description: 初始化基于内存的向量数据库 Bean
 *
 * @Author lzy
 */
@Configuration
public class ParentChildAppVectorStoreConfig {

    @Resource
    private ParentChildAppDocumentLoader parentChildAppDocumentLoader;

    @Bean
    VectorStore ParentChildAppVectorStore(EmbeddingModel dashscopeEmbeddingModel) {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(dashscopeEmbeddingModel)
                .build();
        // 加载文档到内存中
        List<Document> documents = parentChildAppDocumentLoader.loadMarkdowns();
        // 将文档添加到向量数据库中
        simpleVectorStore.add(documents);
        return simpleVectorStore;
    }
}
