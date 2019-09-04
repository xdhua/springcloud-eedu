package com.hxd;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="service-provider")
public interface ConsumerService extends ProviderService {

}
