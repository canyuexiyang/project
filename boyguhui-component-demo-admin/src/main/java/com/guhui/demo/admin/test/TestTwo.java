package com.guhui.demo.admin.test;


public class TestTwo {

	public static void main(String[] args){
		System.out.println("TestTwo A:"+MyParent.a);
		System.out.println("TestTwo B:"+MyParent.b);
		/*
		MyParent A:0
		MyParent B:0
		MyParent A:1
		MyParent B:1
		MyParent A:1
		MyParent B:10
		MyParent A:2
		MyParent B:11
		TestTwo A:2
		TestTwo B:11
		* */
	}

}

class MyParent{
	public static int a;
	public static MyParent myParent = new MyParent();
	public static int b = 10;
	public static MyParent myParent1 = new MyParent();
	private MyParent(){
		System.out.println("MyParent A:"+a);
		System.out.println("MyParent B:"+b);
		a++;
		b++;
		System.out.println("MyParent A:"+a);
		System.out.println("MyParent B:"+b);
	}
}
