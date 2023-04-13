package cn.behappy.common.mybatis.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NameVoUtil {

    @SneakyThrows
    public static <T> List<NameVo> listNameVo(List<T> entityList) {
        List<NameVo> voList = new ArrayList<>(entityList.size());
        for (T t : entityList) {
            Object id = ReflectUtil.invoke(t, t.getClass().getMethod("getId"));
            Object name = ReflectUtil.invoke(t, t.getClass().getMethod("getName"));
            NameVo vo = new NameVo();
            voList.add(vo);
            vo.setId(Convert.toLong(id));
            vo.setName(Convert.toStr(name));
        }
        return voList;
    }

}
