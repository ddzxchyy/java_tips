package cn.behappy.common.mybatis.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class NameVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 不要使用 BeanUtil 复制属性!
     */
    private Number id;

    private String name;

    public NameVo(Number id, String name) {
        this.id = id;
        this.name = name;
    }

    public NameVo() {
    }
}
