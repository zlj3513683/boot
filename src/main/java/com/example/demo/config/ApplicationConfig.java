package com.example.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 功能：
 *
 * @author 2020/1/14
 * @author zoulinjun
 */
@Configurable
@Component
@Data
public class ApplicationConfig {
    @Value("${enc_key_pub}")
    private String enc_key_pub;
    @Value("${sign_private_key}")
    private String sign_private_key;
}
