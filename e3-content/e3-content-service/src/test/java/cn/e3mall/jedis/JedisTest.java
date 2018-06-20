package cn.e3mall.jedis;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jedis.JedisClient;
import jedis.JedisClientPool;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;
	@Autowired
	private JedisClient jedisClient;
	@Test
	public void testJedis()throws Exception
{//单机版链接
	Jedis jedis = new Jedis("192.168.25.129",6379);
	jedis.set("xxx", "123");
	String string = jedis.get("xxx");
	System.out.println(string);
	jedis.close();
	
}
	//使用连接 池连接JedisPool
	//连接集群，自带连接池.
	@Test
	public void testJedisCluster()throws Exception{
		Set<HostAndPort> nodes = new HashSet<>();
	    nodes.add(new HostAndPort("192.168.25.129", 7001));
	    nodes.add(new HostAndPort("192.168.25.129", 7002));
	    nodes.add(new HostAndPort("192.168.25.129", 7003));
	    nodes.add(new HostAndPort("192.168.25.129", 7004));
	    nodes.add(new HostAndPort("192.168.25.129", 7005));
	    nodes.add(new HostAndPort("192.168.25.129", 7006));
		JedisCluster jediscluster = new JedisCluster(nodes);
		jediscluster.set("Test", "123");
		String string = jediscluster.get("Test");
		System.out.println(string);
		Map<String, JedisPool> clusterNodes = jediscluster.getClusterNodes();
          jediscluster.close();
	}
/*	@Test
  public void tt()throws Exception
  {
		//初始化spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:Spring/applicationContext-redis.xml");
			jedisClient	= applicationContext.getBean(JedisClient.class);
			jedisClient.set("xxx", "123");
		String string = jedisClient.get("xxx");
		System.out.println(string);
  }*/
	
}
