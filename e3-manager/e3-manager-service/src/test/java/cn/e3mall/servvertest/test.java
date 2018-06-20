package cn.e3mall.servvertest;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
@Test
public void junitTest() throws IOException
{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:Spring/applicationContext-*.xml");
		System.out.println("服务已经启动。。。。");
		System.in.read();
		System.out.println("服务已经关闭");
		
	}
}
