# Clean Code 的函数优化实战

以下是《Clean Code》的函数优化的最重要原则：

1. 函数要短小。
2. 每个函数只做一件事情。
3. **函数的抽象层级相同**。

我自己还有一个习惯，方法名称只需告诉别人你要干什么，你怎么做我并不关系（除非我需要你）。

在看 《Clean Code》 时，作者提到每个函数一个抽象层级的时候，我突然有种醍醐灌顶的感觉，就像张无忌学会乾坤大挪移一样。我立马冲到电脑前，打开 idea 进行实操， 就像找到了刚开始写代码的感觉。



## 待优化代码示例

下面的代码是处理用户下单的接口

```java
 @Override
 @Transactional(rollbackFor = Exception.class)
public R<UnifiedOrderInfoVo> doUnifiedOrder(UnifiedOrderForm unifiedOrderForm) {
 
	AppUserEntity userEntity = appUserDao.selectById(SecurityUtils.getUserId());
    if (userEntity == null || StrUtil.isBlank(userEntity.getName())) {
       throw new RRException("请先完成实名认证");
    }
    // 其他校验
    UnifiedOrderInfoVo unifiedOrderInfoVo;
    if (Objects.equals(unifiedOrderForm.getSysModule(), PayServiceModuleEnum.A)) {
        // 创建订单 A 的具体逻辑
        // unifiedOrderInfoVo = 
        // 锁库存的具体逻辑
    } else if (Objects.equals(unifiedOrderForm.getSysModule(), PayServiceModuleEnum.B)) {
        // 创建订单 B 的具体逻辑
        // 锁库存的具体逻辑
    }
   // 添加订单超时的延迟消息
    if (unifiedOrderInfoVo.getExpireTime() != null && new Date().before(unifiedOrderInfoVo.getExpireTime())) {
            amqpTemplate.convertAndSend(AmqpDelayConsts.EXCHANGE_A,
                    AmqpDelayConsts.ROUTE_A,
                    unifiedOrderInfoVo, message -> {
                        message.getMessageProperties().setHeader("x-delay",
                                3_000 + unifiedOrderInfoVo.getExpireTime().getTime() - new Date().getTime()
                        );
                        return message;
                    });
	}
}    
```



基于以上原则优化过后的代码

```java
 @Override
 @Transactional(rollbackFor = Exception.class)
 public R<UnifiedOrderInfoVo> doUnifiedOrder(UnifiedOrderForm unifiedOrderForm) {
    
        checkPayable(unifiedOrderForm);
     
        // 创建订单 此处采用策略模式和工厂模式相结合的方式，解决多重 else if 的问题
        UnifiedServiceOrderContext unifiedServiceOrderContext = new UnifiedServiceOrderContext();
        UnifiedOrderInfoVo unifiedOrderInfoVo = unifiedServiceOrderContext.createServiceOrder(unifiedOrderForm);
     
        orderTimeOutMsg(unifiedOrderInfoVo);
        return new R<>(unifiedOrderInfoVo);
}

private void checkPayable(UnifiedOrderForm unifiedOrderForm) {
    // do check
}
private void orderTimeOutMsg(UnifiedOrderInfoVo unifiedOrderInfoVo) {
 // do send msg
}
```



## 为什么需要不断优化

软件开发中唯一不变的就是需求在改变，现在偷的懒，日后会加倍返回。

就像上面优化后的代码，可以看到方法的主逻辑非常清晰：校验 、创建订单、订单超时的延迟消息。

日后需求有变动的时候，就可以只改有变动的地方。



## 编程小建议

1.  先写一个粗颗粒度的大方法，快速实现功能。毕竟空想不可能面面俱到，写着写着就突然有灵感了。
2.  在根据抽象层级，抽小方法出来。用方法名告诉读者你是做什么的（不需要体现怎么做的）。

要尽快优化掉，毕竟 Later equals never 。
