
package cn.behappy.common.mybatis.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * mybatis-plus配置
 */
@Configuration(proxyBeanMethods = false)
public class MybatisPlusConfig implements WebMvcConfigurer {

    /**
     * 插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //阻止恶意或误操作的全表更新删除
//        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        //单独配置不会生效
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        pageInterceptor.setMaxLimit(65535L);
        // 开启 count 的 join 优化,只针对部分 left join
        interceptor.addInnerInterceptor(pageInterceptor);
        return interceptor;
    }

    /**
     * SQL 过滤器避免SQL 注入
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SqlFilterArgumentResolver());
    }

    @Bean
    public MetaObjectHandler mybatisPlusMetaObjectHandler() {
        return new MybatisMetaObjectHandler();
    }
}
