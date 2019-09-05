/**   
* Copyright: Copyright (c) 2019 LanRu-Caifu
* 
* @ClassName: FallBackProviderService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: hxd
* @date: 2019年9月5日 下午3:41:10 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年9月5日     hxd           v1.0.0               新增
*/
package com.hxd.fallback;

import org.springframework.stereotype.Component;

import com.hxd.feign.ConsumerFeignService;
import com.hxd.model.User;


/**
 * @author hxd
 *调用微服务 当前服务 异常时候 服务降级 调用接口 操作
 */
@Component
public class FallBackProviderService implements ConsumerFeignService {

	@Override
	public String getName(User user) {
		return "服务降级，提示信息";
	}
}
