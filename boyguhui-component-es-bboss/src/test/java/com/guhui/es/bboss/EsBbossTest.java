package com.guhui.es.bboss;

import com.guhui.es.bboss.entity.Demo;
import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.client.ClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: es-bboss测试类
 * @Author: guhui
 * @CreateDate: 2019/4/4$ 17:01$
 * @Version: 1.0
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = EsBbossApp.class)
public class EsBbossTest {

	private static SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void test1(){
		//创建es客户端工具，验证环境
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		//验证环境,获取es状态
		String response = clientUtil.executeHttp("/",ClientInterface.HTTP_GET);
		System.out.println(response);
	}

	@Test
	public void test2(){
		/* GET system-2019.03.05/logs/AWlMzjlciGjnzV0CT611
           GET lagou/job/1 */
		//创建批量创建文档的客户端对象，单实例多线程安全
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		//获取索引文档，json格式
		String response = clientUtil.getDocument("lagou",//索引表
				"job",//索引类型
				"1");//文档id
		System.out.println("getDocument-------------------------");
		System.out.println(response);
	}

	@Test
	public void test3(){
		ClientInterface clientUtil = ElasticSearchHelper.
				getConfigRestClientUtil("esmapper/estrace/ESTemplate.xml");

		//创建模板
		String response = clientUtil.createTempate("demotemplate_one",//模板名称
				"demoTemplate");//模板对应的脚本名称，在esmapper/estrace/ESTemplate.xml中配置
		System.out.println("createTempate-------------------------");
		System.out.println(response);

	}

//	@Test
//	public void test4(){
//		ClientInterface clientUtil = ElasticSearchHelper.
//				getConfigRestClientUtil("esmapper/estrace/ESTemplate.xml");
//		/**
//		 * 指定模板
//		 * /_template/demoTemplate_1
//		 * /_template/demoTemplate*
//		 * 所有模板 /_template
//		 *
//		 */
//		//获取模板
//		String template = clientUtil.executeHttp("/_template/demotemplate_one", ClientUtil.HTTP_GET);
//		System.out.println("HTTP_GET-------------------------");
//		System.out.println(template);
//
//		//删除模板
//		template = clientUtil.executeHttp("/_template/demotemplate_1",ClientUtil.HTTP_DELETE);
//		System.out.println("HTTP_DELETE-------------------------");
//		System.out.println(template);
//	}
//
//	@Test
//	public void test5(){
//		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/ESTemplate.xml");
//		String ret = clientUtil.createIndiceMapping("trace", "createTraceIndice") ;
//		System.out.println(ret);
//	}

	public void testGetmapping(){
		String date = formatYMD.format(new Date());
		ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
		System.out.println(clientUtil.getIndexMapping("demo-*"));
		clientUtil.dropIndice("demo-"+date);
	}

	/**
	 * 失败接口，创建索引文档失败
	 */
	@Test
	public void test6(){
		String date = formatYMD.format(new Date());
		ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/estrace/ESTemplate.xml");
		Demo demo = new Demo();
		demo.setDemoId(51);
		demo.setAgentStarttime("2019-04-08 17:40:51");
		demo.setApplicationName("blackcatdemo");
		demo.setContentbody("this is content body");
		//根据dsl脚本创建索引文档，将文档保存到当天的索引表中demo-2018.02.03
		String response = clientUtil.addDateDocument("demo",//索引表,自动添加日期信息到索引表名称中
				"demo",//索引类型
				"createDemoDocument",//创建文档对应的脚本名称，在esmapper/estrace/ESTracesMapper.xml中配置
				demo);

		System.out.println("addDateDocument-------------------------");
		System.out.println(response);
		//根据文档id获取索引文档,返回json格式
		response = clientUtil.getDocument("demo-"+date,//索引表，手动指定日期信息
				"demo",//索引类型
				"5");
		System.out.println("getDocument-------------------------");
		System.out.println(response);
//根据文档id获取索引文档,返回Demo对象
		demo = clientUtil.getDocument("demo-"+date,//索引表
				"demo",//索引类型
				"5",//索引文档ID
				Demo.class);
		System.out.println(demo);
		testGetmapping();
	}

}
