package com.nowcoder.community;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.service.DiscussPostService;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SpringbootTests {

    
    @Autowired
    private DiscussPostService discussPostService;
    
    private DiscussPost data;
    
    // 在测试类初始化之前执行一次
    @BeforeAll
    public static void beforeClass() {
        System.out.println("BeforeClass");
    }

    // 在测试类初始化之后执行一次
    @AfterAll
    public static void afterClass() {
        System.out.println("AfterClass");
    }

    // 在测试方法执行之前执行
    @BeforeEach
    public  void beforeEach() {
        
        data = new DiscussPost();
        data.setUserId(111);
        data.setTitle("TestTitle");
        data.setContent("TestContent");
        data.setCreateTime(new Date());
        discussPostService.addDiscussPost(data);
        
        System.out.println("BeforeEach");
    }

    // 在测试方法执行之前执行
   @AfterEach
    public void afterEach() {
        
        // 删除测试数据
       discussPostService.updateStatus(data.getId(), 2);
        System.out.println("AfterEach");
    }
    
    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }
    
    @Test void testFindById() {
        DiscussPost post = discussPostService.findDiscussPostById(data.getId());
        // 一般都是执行一个测试类，不用system.out.println打印控制台，看有没有绿色的钩子代表没报错
        // 使用断言
        Assertions.assertNotNull(post);
        Assertions.assertEquals(data.getTitle(), post.getTitle());
        Assertions.assertEquals(data.getContent(), post.getContent());
    }

    @Test void testUpdateScore() {
        int rows = discussPostService.updateStatus(data.getId(), 2000);
       
        Assertions.assertEquals(1, rows);
        DiscussPost post = discussPostService.findDiscussPostById(data.getId());
        Assertions.assertEquals(2000.00, post.getStatus(), 2);
    }
}
