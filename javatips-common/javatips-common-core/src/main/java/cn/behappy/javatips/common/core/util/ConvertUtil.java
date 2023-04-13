package cn.behappy.javatips.common.core.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * 类型转换工具
 *
 */
public class ConvertUtil {

    private static final String defaultSeparator = ",";

    private ConvertUtil() {
    }

    /**
     * 将 list转为 str, 默认分隔符为 ','
     */
    public static <T> String list2Str(Collection<T> list) {
        return listSeparation(list, T::toString, defaultSeparator);
    }

    public static <T, R> String list2Str(Collection<T> list, Function<T, R> function) {
        return listSeparation(list, function, defaultSeparator);
    }

    public static <T> String list2Str(Collection<T> list, String separator) {
        return listSeparation(list, T::toString, separator);
    }

    /**
     * eg. list2Str(voList, NameVo::getName, ",")
     */
    public static <T, R> String list2Str(Collection<T> list, Function<T, R> function, String separator) {
        return listSeparation(list, function, separator);
    }

    public static <T> List<T> str2List(String str) {
        return toList(str, defaultSeparator);
    }


    public static <T> List<T> str2List(String str, String separator) {
        return toList(str, separator);
    }

    public static <T> List<Integer> str2IntList(String str) {
        if (StrUtil.isBlank(str)) {
            return Collections.emptyList();
        }
        String[] list = str.split(",");
        List<Integer> intList = new ArrayList<>();
        for (String s : list) {
            intList.add(Convert.toInt(s));
        }
        return intList;
    }

    private static <T> List<T> toList(String str, String separator) {
        if (StrUtil.isBlank(str)) {
            return Collections.emptyList();
        }
        String[] array = str.split(separator);
        List<T> list = new ArrayList<>();
        for (String s : array) {
            list.add((T) s);
        }
        return list;
    }


    private static <T, R> String listSeparation(Collection<T> list, Function<T, R> function, String separator) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T i : list) {
            sb.append(function.apply(i));
            sb.append(separator);
        }
        String str = sb.toString();
        str = str.substring(0, str.length() - 1);
        return str;
    }


    public static void main(String[] args) {
        String s1 = "1,2,3,";
        List<Integer> list1 = str2List(s1);
        System.out.println(list1);
    }

}

