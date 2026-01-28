package com.c2b.c2baiagent;

import org.springframework.ai.vectorstore.pgvector.autoconfigure.PgVectorStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = PgVectorStoreAutoConfiguration.class)
public class C2bAiAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(C2bAiAgentApplication.class, args);
    }

}
