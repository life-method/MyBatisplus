package com.example.demo;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		String type="$2a$10$QBmbw9kLmr7ZC6qmkhxj6uPC2fGj7UoTPdn50pBlELIFbYzvs7v6q";
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		String encode1 = encode.encode("123");
		System.out.println(encode1);
//        Integer
		boolean matches = encode.matches("123", type);
		System.out.println(matches);

	}
	@Autowired
	private UserMapper userMapper;
    @Test
	public void test10(){
		User user = userMapper.selectOne(null);
		System.out.println(user);
	}
}
