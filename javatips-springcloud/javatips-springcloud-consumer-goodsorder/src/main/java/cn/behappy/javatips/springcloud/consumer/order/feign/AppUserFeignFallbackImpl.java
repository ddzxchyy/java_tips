package cn.behappy.javatips.springcloud.consumer.order.feign;

import cn.behappy.javatips.springcloud.provider.user.AppUserVo;
import org.springframework.stereotype.Component;

@Component
public class AppUserFeignFallbackImpl implements AppUserFeign{
    @Override
    public AppUserVo getAppUser(Integer id) {
        return new AppUserVo();
    }
}
