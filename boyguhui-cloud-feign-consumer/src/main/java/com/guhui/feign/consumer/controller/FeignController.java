//package com.guhui.feign.consumer.controller;
//
//import com.guhui.order.api.entity.BizResult;
//import com.guhui.order.api.feign.GdStoreFeignClient;
//import com.guhui.order.api.vo.GdStoreVO;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @Project boyguhui-parent
// * @Description: 测试feign功能controller
// * @Author: guhui
// * @Date: 2019/8/8
// */
//@RestController
//@RequestMapping("/feign")
//public class FeignController {
//
//
//	@Resource
//	private GdStoreFeignClient gdStoreFeignClient;
//
//	@GetMapping("/testFeign/{id}")
//	public BizResult<GdStoreVO> testFeign(@PathVariable("id") Integer id){
//		return this.gdStoreFeignClient.getGdStoreById(new GdStoreVO(id));
//	}
//
//}
