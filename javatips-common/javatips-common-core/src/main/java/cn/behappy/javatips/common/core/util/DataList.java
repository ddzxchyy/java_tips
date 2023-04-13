package cn.behappy.javatips.common.core.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用于返回 json 格式
 */
@Data
public class DataList<T>  implements Serializable {


    List<T> list;

    public DataList() {
    }

    public DataList(List<T> list) {
        this.list = list;
    }

}
