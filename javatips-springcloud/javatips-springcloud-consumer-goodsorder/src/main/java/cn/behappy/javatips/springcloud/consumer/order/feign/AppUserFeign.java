package cn.behappy.javatips.springcloud.consumer.order.feign;

import cn.behappy.javatips.springcloud.provider.user.AppUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static cn.behappy.javatips.common.core.consts.ServiceNameConstants.PROVIDER_USER_BIZ_SERVICE_NAME;

@FeignClient(name = PROVIDER_USER_BIZ_SERVICE_NAME, fallback = AppUserFeignFallbackImpl.class)
public interface AppUserFeign {

    @GetMapping("/internal/app/user/{id}")
    AppUserVo getAppUser(@PathVariable("id") Integer id);
}
