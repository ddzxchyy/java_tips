package cn.behappy.javatips.common.core.util;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author behappy
 */
public class ExceptionUtil {
    /**
     * TODO 未来如果要修改包名，需要修改此处
     */
    public static String getClassAndLine(Exception e) {
        String packagePrefix = "cn.behappy.javatips";
        List<StackTraceElement> stackTraceElementList = new ArrayList<>();
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            if (StrUtil.startWith(stackTraceElement.getClassName(), packagePrefix)
                    && !StrUtil.contains(stackTraceElement.getClassName(), "CGLIB")) {
                stackTraceElementList.add(stackTraceElement);
                break;
            }
        }
        if (stackTraceElementList.isEmpty()) {
            stackTraceElementList.add(e.getStackTrace()[0]);
        }
        return stackTraceElementList.get(0).toString();
    }

    public static String getMsg(Exception e) {
        return ExceptionUtil.getClassAndLine(e) + ", " + e.getMessage();
    }
}
