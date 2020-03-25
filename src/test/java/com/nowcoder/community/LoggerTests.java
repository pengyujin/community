package com.nowcoder.community;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class LoggerTests {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggerTests.class);
    
    @Test
    public void testLogger() {
        //12345
        System.out.println(logger.getName());
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}
