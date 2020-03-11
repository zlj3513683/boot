package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 功能：
 *
 * @author 2020/1/13
 * @author zoulinjun
 */
@Data
public class CsInfo {
    // 消息类型
    private Integer type;
    // 消息内容
    private String content;
}
