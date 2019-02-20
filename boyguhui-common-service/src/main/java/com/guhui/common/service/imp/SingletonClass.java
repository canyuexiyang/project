package com.guhui.common.service.imp;


import java.io.Serializable;

/**
 * Created by guhui ^-^ on 2019/2/19.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/2/19$ 11:00$
 * @Version: 1.0
 */
public class SingletonClass implements MessageFactory{

	private static SingletonClass singleton  = new SingletonClass();
	private SingletonClass() {}
	public static SingletonClass SingletonClass(){
		return singleton;
	}

	@Override
	public Message newMessage(String code) {
		Message message = new Message() {
			@Override
			public void pringMessage() {
				System.out.println("111");
			}
		};
		return message;
	}
}

interface MessageFactory{
	Message newMessage(String code);
}

interface Message extends Serializable{
	void pringMessage();
}
