package com.guhui.demo.admin.test;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
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
		MathOperation  addition = (int a,int b) -> a + b;

		GreetingService service = message -> System.out.println(message);

		// ints sum is:55
//		System.out.println("ints sum is:" + ints.stream().reduce((sum, item) -> sum + item).get());

		//false
//		System.out.println(ints.stream().anyMatch(item -> item < 100));
		ints.stream().filter(o1 -> o1 != null).min((o1, o2) -> o1.compareTo(o2)).ifPresent(System.out::println);
		System.out.println(ints.stream().filter(o1 -> o1 != null).max((c1,c2) -> c1.compareTo(c2)).isPresent());

		final int num = 1;
		Converter<Integer, String> s = paramOne -> System.out.println(String.valueOf(paramOne + num));
		s.convert(2);//打印 3

		IMessage message = str -> System.out.println(str);

		IMath math = (a,b) -> a + b;
		int count = math.add(12,13);

//		Function<String> function.
		IntFunction<String> fun = String::valueOf;
		String str = fun.apply(69);

		Consumer<String> con = System.out::println;

		Predicate<Integer> predicate = itm -> itm < 5;
		System.out.println(predicate.test(9));

	}

	public interface Converter<T1, T2> {
		void convert(int i);
	}

	public void testTwo(){
		new Thread( () -> System.out.println("123")).start();
	}


	public void mapTest() {
		List<Double> cost = Arrays.asList(10.0, 20.0,30.0);
		cost.stream().map(x -> x + x*0.05).forEach(System.out::println);

		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		List<Integer> distinct = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
		numbers.stream().max((i,j) -> i.compareTo(j)).get();

	}

	public void filetTest(){
		List<Double> cost = Arrays.asList(10.0, 20.0,30.0,40.0);
		List<Double> filteredCost = cost.stream().filter(x -> x > 25.0 && x != null).collect(Collectors.toList());
		filteredCost.forEach(x -> System.out.println(x));

		List<Double> costBeforeTax = Arrays.asList(100.00, 200.00, 300.00, 400.00, 500.00);
		double bill = costBeforeTax.stream().map((co) -> co + 0.12 * co).reduce((sum, co) -> sum + co).get();
	}


}
interface MathOperation {
	int operation(int a, int b);
}

interface GreetingService {
	void sayMessage(String message);
}
interface IMessage {
	void send(String str);
}

interface IMath {
	int add(int x,int y);
}