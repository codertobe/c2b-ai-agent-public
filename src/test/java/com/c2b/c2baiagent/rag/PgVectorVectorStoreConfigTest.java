package com.c2b.c2baiagent.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClassName: PgVectorVectorStoreConfigTest
 * Package: com.c2b.c2baiagent.rag
 * Description:
 *
 * @Author lzy
 */
@SpringBootTest
class PgVectorVectorStoreConfigTest {

    @Resource
    private VectorStore pgVectorVectorStore;

    @Test
    void pgVectorVectorStore() {
        List<Document> documents = List.of(
                new Document("小时候，小朋友", Map.of("meta1", "meta1")),
                new Document("长大后，大小孩"),
                new Document("年老时，老顽童", Map.of("meta2", "meta2")));

        // Add the documents to PGVector
        pgVectorVectorStore.add(documents);

        // Retrieve documents similar to a query
        List<Document> results = this.pgVectorVectorStore.similaritySearch(SearchRequest.builder().query("一年级").topK(2).build());
        Assertions.assertNotNull(results);
    }
}