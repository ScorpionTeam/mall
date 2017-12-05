package com.scoprion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
public class MallApplicationTests {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Test
	public void test() {

		redisTemplate.opsForValue().set("boot","hello boot");
	}

}
