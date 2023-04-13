package cn.behappy.javatips.springcloud.consumer.order.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsOrderVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderId;

    private String username;

}


