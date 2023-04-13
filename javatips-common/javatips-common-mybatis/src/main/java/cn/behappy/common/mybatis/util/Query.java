
package cn.behappy.common.mybatis.util;

import cn.behappy.common.mybatis.consts.PageConstant;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * 查询参数
 */
public class Query<T> {

    public IPage<T> getPage(Map<String, Object> params) {
        return this.getPage(params, null, false);
    }

    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = 1;
        long limit = 10;

        if (params.get(PageConstant.PAGE) != null) {
            curPage = Long.parseLong(params.get(PageConstant.PAGE).toString());
        }
        if (params.get(PageConstant.LIMIT) != null) {
            limit = Long.parseLong(params.get(PageConstant.LIMIT).toString());
        }

        //分页对象
        Page<T> page = new Page<>(curPage, limit);

        //分页参数
        params.put(PageConstant.PAGE, page);

        //排序字段
        //防止SQL注入（因为 order 是通过拼接 SQL 实现排序的，会有 SQL 注入风险）
        String orderField = SQLFilter.sqlInject((String) params.get(PageConstant.ORDER_FIELD));
        String order = (String) params.get(PageConstant.ORDER);


        //前端字段排序
        if (StrUtil.isNotEmpty(orderField) && StrUtil.isNotEmpty(order)) {
            if (PageConstant.ASC.equalsIgnoreCase(order)) {
                return page.addOrder(OrderItem.asc(orderField));
            } else {
                return page.addOrder(OrderItem.desc(orderField));
            }
        }

        //没有排序字段，则不排序
        if (StrUtil.isBlank(defaultOrderField)) {
            return page;
        }

        //默认排序
        if (isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        } else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }

        return page;
    }
}
