package cn.behappy.common.mybatis.util;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * mybatis-plus
 * 自定义条件构造器
 * 自增 自减
 *
 * @author db
 */
public class CustomLambdaUpdateWrapper<T> extends LambdaUpdateWrapper<T> {
    public CustomLambdaUpdateWrapper() {
        super();
    }

    /**
     * 自增 +1
     *
     * @param column 字段名
     */
    public CustomLambdaUpdateWrapper<T> incr(SFunction<T, ?> column) {
        return incr(column, 1);
    }

    /**
     * 自增 +1
     *
     * @param column 字段名
     */
    public CustomLambdaUpdateWrapper<T> incr(boolean condition, SFunction<T, ?> column) {
        return incr(condition, column, 1);
    }

    /**
     * 自增
     *
     * @param column 字段名
     * @param val    自增值
     */
    public CustomLambdaUpdateWrapper<T> incr(SFunction<T, ?> column, int val) {
        String columnsToString = super.columnsToString(column);
        String format = String.format("%s = %s + %s", columnsToString, columnsToString, val);
        setSql(format);
        return this;
    }

    /**
     * 自增
     *
     * @param column 字段名
     * @param val    自增值
     */
    public CustomLambdaUpdateWrapper<T> incr(boolean condition, SFunction<T, ?> column, int val) {
        if (condition) {
            return incr(column, val);
        }
        return this;
    }

    /**
     * 自减 -1
     *
     * @param column 字段名
     */
    public CustomLambdaUpdateWrapper<T> reduce(boolean condition, SFunction<T, ?> column) {
        return reduce(condition, column, 1);
    }

    /**
     * 自减 -1
     *
     * @param column 字段名
     */
    public CustomLambdaUpdateWrapper<T> reduce(SFunction<T, ?> column) {
        return reduce(column, 1);
    }

    /**
     * 自减
     *
     * @param column 字段名
     * @param val    自减值
     */
    public CustomLambdaUpdateWrapper<T> reduce(SFunction<T, ?> column, int val) {
        String columnsToString = super.columnsToString(column);
        String format = String.format("%s = %s - %s", columnsToString, columnsToString, val);
        setSql(format);
        return this;
    }

    /**
     * 自减
     *
     * @param column 字段名
     * @param val    自减值
     */
    public CustomLambdaUpdateWrapper<T> reduce(boolean condition, SFunction<T, ?> column, int val) {
        if (condition) {
            return reduce(column, val);
        }
        return this;
    }

    /**
     * limit
     *
     * @param val limit值
     */
    public CustomLambdaUpdateWrapper<T> limit(int val) {
        last(String.format("limit %s", val));
        return this;
    }
}
