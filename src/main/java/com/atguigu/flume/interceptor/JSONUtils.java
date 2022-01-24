package com.atguigu.flume.interceptor;
import org.mortbay.util.ajax.JSON;
/**
 * @author leon
 * @ClassName JSONUtils.java
 * @createTime 2022年01月23日 02:22:00
 */
public class JSONUtils {
    public static boolean isJSONValidate(String log){
        try {
            // 1. 解析JSON字符串
            JSON.parse(log);
            return true;
        } catch (Exception e) {
            // 2. 失败了，证明不是JSON字符串
            return false;
        }
    }
}
