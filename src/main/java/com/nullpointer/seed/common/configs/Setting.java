package com.nullpointer.seed.common.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lihongzheng
 * @date 2021/6/15
 * @description application.yml setting
 */
@Component
@ConfigurationProperties(prefix = "app")
@Data
public class Setting {
    private String environment;
    private String keyVaultName;
}
