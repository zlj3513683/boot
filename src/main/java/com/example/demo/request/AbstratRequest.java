package com.example.demo.request;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.apo.DemoParm;
import com.example.demo.apo.formatter.Formatter;
import com.example.demo.exception.AppBizException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

/**
 * 功能：
 *
 * @author 2020/1/21
 * @author zoulinjun
 */
@Data
@Slf4j
public abstract class AbstratRequest {

    @DemoParm
    private String version;

    public AbstratRequest() {

    }

    public AbstratRequest(JSONObject json) {
        try {
            parseV1(json);
        } catch (AppBizException e) {
            throw new AppBizException(e.getCode(),e.getMsg());
        }catch (Exception e) {
            throw new IllegalArgumentException("parse json by v1 failed!", e);
        }
    }

    protected void parseV1(JSONObject json) throws IllegalArgumentException, IllegalAccessException, InstantiationException, UnsupportedEncodingException, IOException {
        Field[] fields = getClass().getDeclaredFields();
        for (Field f : fields) {
            DemoParm params = null;
            if ((params = f.getAnnotation(DemoParm.class)) != null) {
                String name = f.getName();
                String value = null;
                try {
                    value = (String) json.getString(name);
                } catch (JSONException e) {
                    continue;
                }
                if (value == null) {
                    log.info("parse json:" + name + " is null!");
                    throw new AppBizException("0101",params.msg());
//                    continue;
                }
                Formatter formatter = params.formatter().newInstance();
                String pattern = params.formatPattern();
                if (!StringUtils.isEmpty(pattern)) {
                    formatter.setPattern(pattern);
                }
                Object rslt = formatter.unformat(value);

                f.setAccessible(true);
                f.set(this, rslt);
            }
        }
    }
}
