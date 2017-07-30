package user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;

public class TestUser {
  public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	UserService us = (UserService) context.getBean("userService");
//	us.insertUser(new User(null,"huxz","123456","胡鑫喆","男",null));
	User user = us.showOne("huxz");
	System.out.println(user);
}
}
