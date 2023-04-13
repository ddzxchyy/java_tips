package cn.behappy.common.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatisplus 字段填充
 *
 */
public class MybatisMetaObjectHandler implements MetaObjectHandler {

    /**
     * 进行逻辑删除和插入时间的填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "isDeleted", Integer.class, 0);
        // 严格要求类型 datetime不行
        Date date = new Date();
        this.strictInsertFill(metaObject, "updateTime", Date.class, date);
        this.strictInsertFill(metaObject, "createTime", Date.class, date);
    }

    /**
     * 进行更新时间的填充
     * strictUpdateFill 有 bug 无语
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date();
        this.setFieldValByName("updateTime", date, metaObject);
    }
}