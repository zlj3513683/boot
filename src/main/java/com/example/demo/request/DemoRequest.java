package com.example.demo.request;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.apo.DemoParm;
import com.example.demo.apo.formatter.DateFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

/**
 * 功能：
 *
 * @author 2020/1/21
 * @author zoulinjun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DemoRequest extends AbstratRequest {

    public DemoRequest(JSONObject json) {
        super(json);
    }

    @DemoParm(msg = "payChannel不允许为空")
    private String payChannel;
    /**
     * 前置交易时间yyyyMMddHHmmss
     */
    @DemoParm(formatter = DateFormatter.class, formatPattern = "yyyyMMddHHmmss" )
    private Date transTime;
}
