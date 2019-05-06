package com.example.demo.cases.jackson;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class JacksonTest {
	
	// 声明两个转换器
    private ObjectMapper objectMapper = new ObjectMapper(); // 一般使用ObjectMapper就已足够
    private JsonGenerator jsonGenerator = null; // 需要关闭

    /**
     * 对象 -> json ①
     */
	@Test
	public void test1() throws JsonGenerationException, JsonMappingException, IOException {
		User user = new User("ZhangSan", 25, "abc@163.com");
		objectMapper.writeValue(System.out, user);
	}

    /**
     * 对象 -> json ②
     */
	@Test
	public void test2() throws JsonProcessingException {
		User user = new User("ZhangSan", 25, "abc@163.com");
        String json = objectMapper.writeValueAsString(user); 
        System.out.println(json);
	}
	/**
	 * json -> 对象
	 */
	@Test
	public void test3() throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"name\":\"ZhangSan\",\"age\":25,\"emailAddress\":\"abc@163.com\"}";
        User user = objectMapper.readValue(json, User.class);
        System.out.println(user.getName() + " | " + user.getAge() + " | " + user.getEmailAddress());
	}
	
	@Test
	public void test4() throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"name\":\"ZhangSan\",\"age\":25,\"emailAddress\":\"abc@163.com\"}";
//		JavaType type = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, User.class);
//		List<User> list = objectMapper.readValue(json, type);
//		for(User user : list) {
//			 System.out.println(user.getName() + " | " + user.getAge() + " | " + user.getEmailAddress());
//		}
		List<User> beanList = objectMapper.readValue(json, new TypeReference<List<User>>() {}); 
		for(User user : beanList) {
			 System.out.println(user.getName() + " | " + user.getAge() + " | " + user.getEmailAddress());
		}
	}
}





class User {
    public String name;
    public int age;
    public String emailAddress;
    
    public User() {}

    public User(String name, int age, String emailAddress) {
        super();
        this.name = name;
        this.age = age;
        this.emailAddress = emailAddress;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
    
    
}
