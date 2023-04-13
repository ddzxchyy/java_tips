package cn.behappy.javatips.springcloud.provider.user.biz;

import cn.behappy.javatips.springcloud.provider.user.AppUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AppUserDao extends BaseMapper<AppUserEntity> {
}
