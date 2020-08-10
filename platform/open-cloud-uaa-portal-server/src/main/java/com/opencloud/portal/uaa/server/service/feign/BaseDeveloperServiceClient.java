package com.opencloud.portal.uaa.server.service.feign;

import com.opencloud.base.client.constants.BaseConstants;
import com.opencloud.base.client.service.IBaseDeveloperServiceClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author: szz
 * @date: 2018/10/24 16:49
 * @description:
 */
@Component
@FeignClient(value = BaseConstants.BASE_SERVER)
public interface BaseDeveloperServiceClient extends IBaseDeveloperServiceClient {


}
