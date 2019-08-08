package com.guhui.common.custom;

import com.sun.el.stream.Stream;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/7/29$ 15:25$
 */
public class CustomArrayList<T> {

	/**
	 * 集合数据量
	 */
	private int size;

	/**
	 * 集合创建默认大小
	 */
	private static final int DEFAULT_CAPACITY = 10;

	/**
	 * 空集合
	 */
	private static final Object[] EMPTY_ELEMENTDATA = {};

	private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

	protected transient int modCount = 0;

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	/**
	 * 集合本身
	 */
	transient Object[] elementData;

	/**
	 * 创建一个默认空集合
	 */
	public CustomArrayList(){
		elementData = EMPTY_ELEMENTDATA;
	}

	/**
	 * 根据initialCapacity大小创建集合
	 * @param initialCapacity
	 */
	public CustomArrayList(int initialCapacity){
		if (initialCapacity > 0){
			elementData = new Object[initialCapacity];
		}else if(initialCapacity == 0){
			elementData = EMPTY_ELEMENTDATA;
		}else{
			throw new IllegalArgumentException("创建集合的大小数量不能为负数");
		}
	}

	public boolean add(T t){
		ensureExplicitCapacity(size + 1);
		elementData[size++] = t;
		return true;
	}


	private static int calculateCapacity(Object[] elementData, int minCapacity) {
		if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
			return Math.max(DEFAULT_CAPACITY, minCapacity);
		}
		return minCapacity;
	}

	private void ensureCapacityInternal(int minCapacity) {
		ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
	}

	private void ensureExplicitCapacity(int minCapacity) {
		modCount++;

		if (minCapacity - elementData.length > 0) {
			grow(minCapacity);
		}
	}

	private void grow(int minCapacity) {
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if (newCapacity - minCapacity < 0) {
			newCapacity = minCapacity;
		}
		if (newCapacity - MAX_ARRAY_SIZE > 0) {
			newCapacity = hugeCapacity(minCapacity);
		}
		elementData = Arrays.copyOf(elementData, newCapacity);
	}

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) {
			throw new OutOfMemoryError();
		}
		return (minCapacity > MAX_ARRAY_SIZE) ?
				Integer.MAX_VALUE :
				MAX_ARRAY_SIZE;
	}

	public static void main(String[] args) {
		String[] arr = new String[]{"a","b","c","d","e"};
		forArr(arr);

		System.arraycopy(arr, 1+1, arr, 1,
				arr.length - 1 - 1);
		arr[arr.length-1] = null;
		forArr(arr);
 	}

 	public static void forArr(String[] arr){
		StringBuilder sb = new StringBuilder("[");
		for (String s : arr) {
			sb.append(s).append(",");
		}
		sb.setCharAt(sb.lastIndexOf(","),']');
		System.out.println(sb.toString());
	}

}
