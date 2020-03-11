package com.example.demo.request;

import com.example.demo.apo.MyNotNull;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * 功能：
 *
 * @author 2020/1/14
 * @author zoulinjun
 */
@Data
public class FaceRequest {
    @NotBlank(message = "不允许为空ko")
    private  String version;
    @NotNull(message = "不能为空")
    @Min(value = 1, message = "最小值为1") // 最小值为1
    @Max(value = 88, message = "最大值为88") // 最大值88
    private Integer amount;
    @NotNull // 不能为空
    @DecimalMin(value = "0.1") // 最小值0.1元
    @DecimalMax(value = "10000.00") // 最大值10000元
    private Double doubleValue;
    @NotNull // 不能为空
    private LiMian liMian;


}
