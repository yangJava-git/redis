package com.yangbingqi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangbingqi.bean.User;
import com.yangbingqi.utils.DateUtils;
import com.yangbingqi.utils.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:redis.xml")
public class RedisTest {
	
	@Autowired
	RedisTemplate redisTemplate;
	
	/**
	 * 
	 * @Title: JDKTest 
	 * @Description: 使用JDk系列化方式保存5万个user随机对象到Redis
	 * @return: void
	 */
	@Test
	public void JDKTest() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			User u = new User();
			u.setId(i);
			u.setName(StringUtils.getRandomCn(3));
			u.setGender(StringUtils.getSex());
			u.setEailm(StringUtils.getMail());
			u.setDate(DateUtils.getBirthday());
			u.setPhone(StringUtils.getPhone());
			redisTemplate.opsForValue().set("u"+i, u);
		}
		long end = System.currentTimeMillis();
		System.out.println("序列化方式为JdkSerializationRedisSerializer");
		System.out.println("保存数量为5W");
		System.out.println("所耗时间:"+(end-start));
	}
	
	
	/**
	 * 
	 * @Title: JsonTest 
	 * @Description:使用JSON系列化方式保存5万个user随机对象到Redis
	 * @return: void
	 */
	@Test
	public void JsonTest() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			User u = new User();
			u.setId(i);
			u.setName(StringUtils.getRandomCn(3));
			u.setGender(StringUtils.getSex());
			u.setEailm(StringUtils.getMail());
			u.setDate(DateUtils.getBirthday());
			u.setPhone(StringUtils.getPhone());
			redisTemplate.opsForValue().set("u"+i, u);
		}
		long end = System.currentTimeMillis();
		System.out.println("序列化方式为Jackson2JsonRedisSerializer");
		System.out.println("保存数量为5W");
		System.out.println("所耗时间:"+(end-start));
	}
	
	/**
	 * 
	 * @Title: HashTest 
	 * @Description: 使用Redis的Hash类型保存5万个user随机对象到Redis，并计算耗时
	 * @return: void
	 */
	@Test
	public void HashTest() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			User u = new User();
			u.setId(i);
			u.setName(StringUtils.getRandomCn(3));
			u.setGender(StringUtils.getSex());
			u.setEailm(StringUtils.getMail());
			u.setDate(DateUtils.getBirthday());
			u.setPhone(StringUtils.getPhone());
			redisTemplate.opsForHash().put("user", "user"+i, u.toString());
		}
		long end = System.currentTimeMillis();
		System.out.println("序列化方式为StringRedisSerializer");
		System.out.println("保存数量为5W");
		System.out.println("所耗时间:"+(end-start));
	}
	
	
	
	
	

}
