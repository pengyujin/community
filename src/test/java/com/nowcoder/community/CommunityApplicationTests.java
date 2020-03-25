package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class CommunityApplicationTests implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		//spring容器
		this.applicationContext = applicationContext;
	}
	
	@Test
	public void testApplicationContext() {
		System.out.println(applicationContext);
		
		//怎么用,DAO有两个以上实现类可用@Primary 优先获取bean
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());
		
		//也可通过名字获取@Service("alphaDaoHibernate")
		alphaDao = applicationContext.getBean("alphaDaoHibernate",AlphaDao.class);
		System.out.println(alphaDao.select());
		
	}
	
	/**
	 * 测试bean的管理
	 */
	@Test
	public void testBeanManagement() {
		//1.通过注解方式@PostConstruct、@PreDestroy
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);

		//第二次实例化bean，得到的是同一个bean
		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
		
		//spring的bean默认是单例的，用@Scope("prototype")每次实例化bean
	}
	
	@Test
	public void testBeanConfig() {
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}
	
	@Autowired
	@Qualifier("alphaDaoHibernate")
	private AlphaDao alphaDao;

	@Autowired
	private AlphaService alphaService;
	
	@Autowired
	private SimpleDateFormat simpleDateFormat;
	/**
	 * 测试依赖注入方式
	 */
	@Test
	public void testDI() {
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}
}
