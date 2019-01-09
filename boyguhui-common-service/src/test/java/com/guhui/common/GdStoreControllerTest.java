package com.guhui.common;

import com.guhui.common.controller.GdStoreController;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by guhui ^-^ on 2018/12/24.
 */
@EnableEncryptableProperties
@RunWith(SpringRunner.class)
@SpringBootTest
public class GdStoreControllerTest {

	private static final Logger log = LoggerFactory.getLogger(GdStoreControllerTest.class);

	private MockMvc mvc;

	@Autowired
	private GdStoreController gdStoreController;

	@Autowired
	StringEncryptor stringEncryptor;//密码解码器注入

	@Before
	public void setMockMvc(){
		mvc = MockMvcBuilders.standaloneSetup(gdStoreController).build();
	}

	@After
	public void after(){
		log.info("--柴犬小七--小六--");
	}

	@Test
	public void testOne(){
		System.out.println("-----单元测试案例");
		log.info("--柴犬小七--");
	}

	@Test
	public void testTwo(){
		String url = "/gdStore/getGdStoreAll";
		try {
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
			int status = mvcResult.getResponse().getStatus();
			String content = mvcResult.getResponse().getContentAsString();
			log.info("status -- "+status);
			log.info("content -- "+content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testThree(){
		//加密密码
//		StringEncryptor ss = new StandardPBEStringEncryptor();
//		((StandardPBEStringEncryptor) ss).setPassword("123");
//		//String pwd = stringEncryptor.encrypt("123456");
//		String pwd = ss.encrypt("123456");
		System.out.println(stringEncryptor.encrypt("guhui960308"));
	}



























}
