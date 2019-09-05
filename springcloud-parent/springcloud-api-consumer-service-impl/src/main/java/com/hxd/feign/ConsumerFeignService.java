package com.hxd.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.hxd.ProviderService;
import com.hxd.fallback.FallBackProviderService;

/**
 *  
 * @author hxd
 *feign 客户端 当服务出现异常时候降级 调用 同一个类不同实现
 */
@FeignClient(name="service-provider", fallback=FallBackProviderService.class)
//@FeignClient(value="service-provider")
public interface ConsumerFeignService extends ProviderService {

}
