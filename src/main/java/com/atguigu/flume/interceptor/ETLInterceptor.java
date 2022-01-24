package com.atguigu.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

/**
 * @author leon
 * @ClassName ETLInterceptor.java
 * @createTime 2022年01月23日 02:25:00
 */
public class ETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 1. 获取事件体
        byte[] body = event.getBody();
        // 2. 解析事件体为字符串
        String log = new String(body, StandardCharsets.UTF_8);
        // 3.判断是否是JSON字符串
        if(JSONUtils.isJSONValidate(log))
            // 是，返回事件
            return event;
        else
            // 不是，返回空
            return null;
    }

    /**
     * @param events
     * @describe 在这里，真正的将不符合要求的事件移除
     * @return
     */
    @Override
    public List<Event> intercept(List<Event> events) {
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()){
            Event event = iterator.next();
            if(intercept(event) == null)
                iterator.remove();
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new ETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
