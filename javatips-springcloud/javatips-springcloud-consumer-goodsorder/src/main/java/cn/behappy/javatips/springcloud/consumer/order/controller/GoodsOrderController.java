package cn.behappy.javatips.springcloud.consumer.order.controller;

import cn.behappy.javatips.common.core.util.DataList;
import cn.behappy.javatips.common.core.util.R;
import cn.behappy.javatips.springcloud.consumer.order.dao.GoodsOrderDao;
import cn.behappy.javatips.springcloud.consumer.order.entity.GoodsOrderEntity;
import cn.behappy.javatips.springcloud.consumer.order.feign.AppUserFeign;
import cn.behappy.javatips.springcloud.consumer.order.vo.GoodsOrderVo;
import cn.behappy.javatips.springcloud.provider.user.AppUserVo;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GoodsOrderController {

    private final GoodsOrderDao goodsOrderDao;
    private final AppUserFeign appUserFeign;

    /**
     * 实际开发过程中 userId 是根据 token 获取的
     */
    @GetMapping("/user-order/{userId}")
    public R<DataList<GoodsOrderVo>> listOrder(@PathVariable Integer userId) {
        List<GoodsOrderEntity> goodsOrderEntityList = goodsOrderDao.selectList(new LambdaQueryWrapper<GoodsOrderEntity>()
                .eq(GoodsOrderEntity::getUserId, userId));
        AppUserVo appUserVo = appUserFeign.getAppUser(userId);
        return R.ok(new DataList<>(listOrderVo(goodsOrderEntityList, appUserVo)));
    }

    private List<GoodsOrderVo> listOrderVo(List<GoodsOrderEntity> goodsOrderEntityList, AppUserVo appUserVo) {
        List<GoodsOrderVo> goodsOrderVoList = new ArrayList<>();
        for (GoodsOrderEntity goodsOrderEntity : goodsOrderEntityList) {
            GoodsOrderVo goodsOrderVo = new GoodsOrderVo();
            goodsOrderVoList.add(goodsOrderVo);
            BeanUtil.copyProperties(goodsOrderEntity, goodsOrderVo);
            goodsOrderVo.setUsername(appUserVo.getName());
        }
        return goodsOrderVoList;
    }
}
