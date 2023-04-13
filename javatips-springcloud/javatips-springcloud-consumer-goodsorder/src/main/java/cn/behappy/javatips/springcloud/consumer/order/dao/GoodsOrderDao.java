package cn.behappy.javatips.springcloud.consumer.order.dao;

import cn.behappy.javatips.springcloud.consumer.order.entity.GoodsOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GoodsOrderDao extends BaseMapper<GoodsOrderEntity> {
}
