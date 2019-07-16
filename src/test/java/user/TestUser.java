package user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.TestConfig;
import dao.UserDao;
import entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TestUser {
	@Autowired
	private UserDao userDao;
	@Test
	public void save(){	
		User user = new User();
		user.setUserName("ww");
		user.setPassword("123456");
		user.setAge(22);
		userDao.save(user);	
	}
}
