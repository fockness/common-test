package com.example.demo.cases.common;
/**
* @author shibin E-mail : shibin@uama.com.cn
* @Date creation time ：2018-11-15 19:10
* @Description
*	
*/

import com.google.common.collect.Lists;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

	/**
	 * 
	 * 将对象中的属性值值映射出来Student.name或者map.value
	 * 不影响原来集合
	 */
	@Test
	public void testStreamMap() {
		List<String> list = Arrays.asList("a", "b", "c");
		List<String> list2 = list.stream().map(String::toUpperCase).limit(1).collect(Collectors.toList());
		list = list.stream().skip(1).collect(Collectors.toList());

		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(1);
		list3.add(2);
		list3.add(3);
		Stream<Integer> stream = list3.stream();
		list3.add(4);
		list3.add(4);
		//延迟执行特性，在聚合操作之前都可以添加相应元素
		long n = stream.distinct().count();
		System.out.println(n);
	}
	
	/**
	 * 构建流generate，of，iterate
	 * generate
	 */
	@Test
	public void testStreamGenerate() {
		Stream<String> stream = Stream.generate(() -> "users").limit(10);
		//引用本地过滤器
		stream = stream.filter(this::filter);
		stream.forEach(System.out::println);
	}
	
	/**
	 * Of
	 */
	@Test
	public void testStreamOf() {
		Stream<String> stream = Stream.of("1", "2", "3");
		System.out.println(stream.collect(Collectors.toList()));
	}
	
	/**
	 * iterate
	 */
	@Test
	public void testStreamIterate() {
		Stream.iterate(0, x->x+1).limit(10).forEach(System.out::println);
	}
	
	/**
	 * 查找与匹配allMatch,anyMatch
	 */
	@Test
	public void testStreamMatch() {
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		boolean success = list.stream().anyMatch(x -> x>=3);//只要有一个大于3，则返回true
		System.out.println(success);
		
		success = list.stream().allMatch(x -> x>=3);//全部大于3才返回true
		System.out.println(success);
		
		success = list.stream().noneMatch(x -> x>=3);//没有元素大于等于3才返回true
		System.out.println(success);
	}
	
	@Test
	public void testStreamForeach() {
		List<Integer> list = Lists.newArrayList(1,2,3,4);//楼的id
		List<Integer> list2 = Lists.newArrayList(1,2,6,7);//单元的parentId
		Stream<Integer> stream = list.stream();
		for(Integer i : list2) {
			stream = stream.filter(j->j != i);
		}
		list = stream.collect(Collectors.toList());
		System.out.println(list);
	}
	
	@Test
	public void testStreamMap2() {
		List<String> list = Lists.newArrayList("a", "b");
		Map<String, String> communityIdMap = list.stream().distinct().collect(Collectors.toMap(k -> k, v -> v));
		System.out.println(communityIdMap.entrySet().toString());
	}
	
	@Test
	public void testStreamRemove() {
		List<Integer> list = Lists.newArrayList(1,2,3,4);
		list.removeIf(i->{
			return true;
		});
		System.out.println(list);
	}

	/**
	 * 将数据分组
	 */
	@Test
	public void testGroup() {
		List<String> list = Lists.newArrayList("aaa", "aaa", "bbb");
        System.out.println(list.stream().collect(Collectors.groupingBy(v -> v, Collectors.counting())));

        List<User> users =
				Lists.newArrayList(new User("lili", 12), new User("wangming", 12), new User("dagou", 19));

		System.out.println(users.stream().collect(Collectors.groupingBy(u -> u.getAge())));
	}

	@Data
	private static class User{
		private String name;
		private Integer age;

		public User(String name, Integer age) {
			this.name = name;
			this.age = age;
		}
	}

	private boolean filter(String string) {
		return true;
	}
}

