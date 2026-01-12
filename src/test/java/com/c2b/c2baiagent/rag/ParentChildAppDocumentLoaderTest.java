package com.c2b.c2baiagent.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClassName: ParentChildAppDocumentLoaderTest
 * Package: com.c2b.c2baiagent.rag
 * Description:
 *
 * @Author lzy
 */
@SpringBootTest
class ParentChildAppDocumentLoaderTest {

    @Resource
    private ParentChildAppDocumentLoader parentChildAppDocumentLoader;

    @Test
    void loadMarkdowns() {
        parentChildAppDocumentLoader.loadMarkdowns();
    }
}