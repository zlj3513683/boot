package com.example.demo.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 功能：
 *
 * @author 2020/1/16
 * @author zoulinjun
 */
@Data
public class LiMian {
    @NotNull(message = "不能为空")
    @Min(value = 1, message = "最小值为1") // 最小值为1
    @Max(value = 88, message = "最大值为88") // 最大值88
    private Integer l_amt;
}
