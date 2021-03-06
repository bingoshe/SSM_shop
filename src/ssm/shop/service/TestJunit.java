package ssm.shop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ssm.shop.dao.UserDao;
import ssm.shop.entity.User;
/*
 * 一个Junit的测试例子，直接测试Dao层
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestJunit {
	@Autowired
	UserDao userdao;

	@Test
	public void test1() {
		User user  = userdao.getUserByName("1");
		System.out.println(user);
	}
}
