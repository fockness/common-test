package com.example.demo.cases.algorithm;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.Data;

/**
* @author shibin
* @version ����ʱ�䣺2019��3��5�� ����4:13:34
* 	��ĳ���Ƿ��ǶԳ���
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
 * ���Ե���дһ�����У�ʹ�õ�ʱ��̬����
 * @author shibin
 *
 */
class UserPredicate{
	
	public static Predicate<User> moreThan18(){
		return p->p.getAge() >= 10;
	}
	
	/**
	 * �൱���Զ��������
	 * @param users
	 * @param predicate
	 * @return
	 */
	public static List<User> filterUser(List<User> users, Predicate<User> predicate){
		return users.stream().filter(predicate).collect(Collectors.<User>toList());
	}
}
