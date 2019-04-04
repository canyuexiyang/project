package com.guhui.es.bboss;

import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: es-bboss测试类
 * @Author: guhui
 * @CreateDate: 2019/4/4$ 17:01$
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsBbossApp.class)
public class EsBbossTest {



	@Test
	public void test1(){
		//创建es客户端工具，验证环境
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		//验证环境,获取es状态
		String response = clientUtil.executeHttp("/",ClientInterface.HTTP_GET);
		System.out.println(response);
	}

}
