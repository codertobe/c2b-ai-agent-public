package com.c2b.c2baiagent.demo.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.rag.Query;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClassName: MultiQueryExpanderDemoTest
 * Package: com.c2b.c2baiagent.demo.rag
 * Description:
 *
 * @Author lzy
 */
@SpringBootTest
class MultiQueryExpanderDemoTest {

    @Resource
    private MultiQueryExpanderDemo multiQueryExpanderDemo;

    @Test
    void expand() {
        List<Query> queries = multiQueryExpanderDemo.expand("价值是什么?");
        assertNotNull(queries);
    }
}