package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {
    
    @Autowired
    private MailClient mailClient;
    
    @Autowired
    private TemplateEngine templateEngine;
    
    @Test
    public void testTestMail() {
        mailClient.sendMail("1506891518@qq.com","牛客网","项目测试");
    }
    
    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("username","sunday");
        
        String content = templateEngine.process("/mail/demo",context);
        mailClient.sendMail("1506891518@qq.com","牛客网HTML",content);
        
    }
}
