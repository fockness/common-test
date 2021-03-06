package com.example.demo.cases.algorithm;

import com.google.common.collect.Lists;
import lombok.Data;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
* @author shibin
* @version 创建时间：2019年3月5日 下午4:13:34
 * * 	求某数是否是对称数
*/
public class PredicateTest {

	@Test
	public void testPredicate() {
		List<User> users = Lists.newArrayList(new User(10), new User(20), new User(5));
		users = UserPredicate.filterUser(users, UserPredicate.moreThan18());
		System.out.println(users);
	}
}

@Data
class User{
	private Integer age;

	public User(Integer age) {
		super();
		this.age = age;
	}
}

/**
 * 断言单独写一个类中，使用的时候静态引入
 * @author shibin
 *
 */
class UserPredicate{

	public static Predicate<User> moreThan18(){
		return p->p.getAge() >= 10;
	}

	/**
	 * 相当于自定义过滤器
	 * @param users
	 * @param predicate
	 * @return
	 */
	public static List<User> filterUser(List<User> users, Predicate<User> predicate){
		return users.stream().filter(predicate).collect(Collectors.<User>toList());
	}
}
