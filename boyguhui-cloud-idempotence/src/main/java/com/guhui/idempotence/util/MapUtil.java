package com.guhui.idempotence.util;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @Project boyguhui-cloud-idempotence
 * @Description: 利用Map代码redis来保存Token
 * @Author: guhui
 * @Date: 2019/8/15
 * DCL单例模式: 懒汉式套路基础上加入并发控制，保证在多线程环境下，对外存在一个对象
 * 1、构造器私有化 -->避免外部new构造器
 * 2、提供私有的静态属性 -->存储对象的地址
 * 3、提供公共的静态方法 --> 获取属性
 */
public class MapUtil {

	/**
	 * 2、提供私有Hashtable的静态属性,加上volatile 禁止指令重排，那到一个未赋值的map
	 * 使用HashTable是线程安全容器，牺牲一点效率换取容器同步存储。避免并发存储
	 * 一开始是HashMap,我使用Jmeter一秒钟500个访问，只存储了499个token，丢了一个token。换成HashTable就没问题了
	 */
	private static volatile Hashtable<String,String> map;

	/**
	 * 1、构造器私有化 -->避免外部new构造器
	 */
	private MapUtil(){}

	/**
	 * 、提供公共的静态方法 --> 获取属性
	 * @return
	 */
	public static Hashtable<String,String> getMap(){
		//第一次检查，避免不必要的同步
		if (null != map){
			return map;
		}
		//加锁，获取实例
		synchronized (MapUtil.class){
			//第二次检查
			if (null == map){
				//1、开辟空间 //2、初始化对象信息 //3、返回对象的地址给引用
				map = new Hashtable<>();
			}

		}
		return map;
	}

}
