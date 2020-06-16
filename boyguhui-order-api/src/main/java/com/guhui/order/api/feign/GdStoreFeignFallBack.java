package com.guhui.order.api.feign;

import com.guhui.order.api.entity.BizResult;
import com.guhui.order.api.vo.GdStoreVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by guhui ^-^ on 2019/1/15.
 *
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/1/15$ 15:03$
 * @Version: 1.0
 */
@Component
public class GdStoreFeignFallBack implements GdStoreFeignClient{

	private static final Logger log = LoggerFactory.getLogger(GdStoreFeignFallBack.class);

	@Override
	public BizResult<GdStoreVO> getGdStoreById(GdStoreVO gdStoreVO) {
		log.error("boyguhui-order-api --> GdStoreFeignFallBack --> getGdStoreById 错误");
		return BizResult.error("nishizhuma");
	}

	@Override
	public BizResult<Long> inster(GdStoreVO gdStoreVO) {
		log.error("boyguhui-order-api --> GdStoreFeignFallBack --> inster 错误");
		return BizResult.error("nishizhuma");
	}
}
