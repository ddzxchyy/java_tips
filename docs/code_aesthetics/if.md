# if 优化

项目中经常会有 if 判断，如果处理不好会影响阅读理解代码。

下面就 if 用于判断和分支给出不同的优化建议。

## 卫语句

对于判断的条件语句，可以使用卫语句提前 return 或者 抛出异常。减少嵌套，便于理解代码。

示例代码如下：

```java
if (list.size() > 0) {
    if (StrUtil.isNotBlank(user.getMobile())) {
        // do something
    }
}

// 使用卫语句优化
if (list.size() == 0) {
    return;
}
if (StrUtil.isNotBlank(user.getMobile())) {
    throw new RuntimeException("手机号不能为空");
}
// do something
```



## 策略模式

分支的 if 情况可以用策略模 +工厂模式来优化。

优点：将逻辑代码和业务代码解耦。

缺点：需要创建很多额外的类。



不使用该模式的代码：

```java 
public R<UnifiedOrderInfoVo> doUnifiedOrder(Integer module) {
     UnifiedOrderInfoVo unifiedOrderInfoVo;
	if (module == 1) {
      //  unifiedOrderInfoVo =
        // do something
    } else if (mobule == 2) {
        // do something
    } else if (mobule == 3) {
        // do something
    }
    
}
```

使用该模式的示例代码：

```java
public R<UnifiedOrderInfoVo> doUnifiedOrder(UnifiedOrderForm unifiedOrderForm) {
     UnifiedOrderInfoVo unifiedOrderInfoVo = UnifiedServiceOrderFactory.creator(unifiedOrderForm.getModule()).creteServiceOrder(unifiedOrderForm);             
}
```



需要额外添加的类：

```java
// 策略类
public interface UnifiedServiceOrderStrategy {
    UnifiedOrderInfoVo creteServiceOrder(UnifiedOrderForm unifiedOrderForm);
}

// 具体策略类
public class AStrategy implements UnifiedServiceOrderStrategy {
   public UnifiedOrderInfoVo creteServiceOrder(UnifiedOrderForm unifiedOrderForm){
       return new UnifiedOrderInfoVo();
   }
}

public class BStrategy implements UnifiedServiceOrderStrategy {
   public UnifiedOrderInfoVo creteServiceOrder(UnifiedOrderForm unifiedOrderForm){
       return new UnifiedOrderInfoVo();
   }
}

// 工厂类
public class UnifiedServiceOrderFactory {
    private static AStrategy aStrategy;
    private static BStrategy bStrategy;

    static {
       UnifiedServiceOrderFactory.aStrategy = (AStrategy) SpringContextUtils.getBean("AStrategy");
       UnifiedServiceOrderFactory.aStrategy = (BStrategy) SpringContextUtils.getBean("BStrategy");
    }

    private static final Map<Integer, UnifiedServiceOrderStrategy> STRATEGY_MAP = new HashMap<>();

    static {
        STRATEGY_MAP.put(PayServiceModuleEnum.A.getModule(), aStrategy);
        STRATEGY_MAP.put(PayServiceModuleEnum.B.getModule(), bStrategy);
    }

    public static UnifiedServiceOrderStrategy creator(Integer module) {
        return STRATEGY_MAP.get(module);
    }
}
```



```java

```

工厂类：

```java



```



