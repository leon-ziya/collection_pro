package com.atguigu.flume.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author leon
 * @ClassName TimeStampInterceptor.java
 * @createTime 2022年01月23日 13:30:00
 */
public class TimeStampInterceptor implements Interceptor {

    public Logger logger = LoggerFactory.getLogger(TimeStampInterceptor.class);
    private ArrayList<Event> events = new ArrayList<>();

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        Map<String, String> headers = event.getHeaders();
        // 1. 获取Event的Body
        String log = new String(event.getBody(), StandardCharsets.UTF_8);
        logger.info("header: "+headers.toString()+"\n body: "+log);
        // 2. 解析log为json对象
        JSONObject jsonObject = JSONObject.parseObject(log);
        // 3. 获取log中的时间戳
        String ts = jsonObject.getString("ts");
        logger.info("ts: "+ts);
        // 4. 将时间戳属性配置到header中
        headers.put("timestamp",ts);
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        events.clear();
        for (Event event : list) {
            events.add(intercept(event));
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new TimeStampInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
