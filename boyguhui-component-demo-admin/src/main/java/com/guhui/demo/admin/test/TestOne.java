package com.guhui.demo.admin.test;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by guhui ^-^ on.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/5/8$ 16:12$
 */
public class TestOne {

	private static final Logger LOG = LoggerFactory.getLogger(TestOne.class);

	public static void main(String[] args){

//		List<String> proNames = Arrays.asList(new String[]{"Ni","Hao","Lambda"});
//			List<String> lowercaseNames3 = proNames.stream().map(String::toLowerCase).collect(Collectors.toList());
//			List<String> low = proNames.stream().map(String :: toLowerCase).collect(Collectors.toList());
//			List<String> low2 = proNames.stream().map(name -> name.toLowerCase()).collect(Collectors.toList());
//		List<Integer> nums = Lists.newArrayList(1,null,3,4,null,6);
//		nums = nums.stream().filter(num -> num != null).collect(Collectors.toList());

//		long cou = nums.stream().filter(num -> num != null).count();
//		System.out.println(cou);
//		Stream<List<Integer>> intList = Stream.of(nums);
//		System.out.println("-----------------------");
		Stream.iterate(1,item -> item + 2).limit(10).forEach(System.out::println);
//		nums.stream().peek(System.out::println);
//		nums.forEach(System.out::println);

		List<Integer> ints = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
//		ints = Lists.newArrayList(null,null,null);


		// ints sum is:55
//		System.out.println("ints sum is:" + ints.stream().reduce((sum, item) -> sum + item).get());

		//false
//		System.out.println(ints.stream().anyMatch(item -> item < 100));
		ints.stream().filter(o1 -> o1 != null).min((o1, o2) -> o1.compareTo(o2)).ifPresent(System.out::println);
		System.out.println(ints.stream().filter(o1 -> o1 != null).max((c1,c2) -> c1.compareTo(c2)).isPresent());
	}

}
