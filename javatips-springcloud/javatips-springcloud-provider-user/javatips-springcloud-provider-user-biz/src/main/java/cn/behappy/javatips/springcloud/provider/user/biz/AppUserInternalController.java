package cn.behappy.javatips.springcloud.provider.user.biz;

import cn.behappy.javatips.springcloud.provider.user.AppUserEntity;
import cn.behappy.javatips.springcloud.provider.user.AppUserVo;
import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内部请求不要暴露到公网
 * 应该保证除了网关需要暴露到公网，其它请求都只能内网访问
 */
@RestController
@RequestMapping("/internal/app/user")
@AllArgsConstructor
public class AppUserInternalController {

    private final AppUserDao appUserDao;

    @GetMapping("/{id}")
    public AppUserVo getUser(@PathVariable("id")Integer id) {
        AppUserEntity appUserEntity = appUserDao.selectById(id);
        AppUserVo appUserVo = new AppUserVo();
        BeanUtil.copyProperties(appUserEntity, appUserVo);
        return appUserVo;
    }
}
