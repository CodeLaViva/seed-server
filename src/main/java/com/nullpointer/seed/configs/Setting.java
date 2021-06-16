package com.nullpointer.seed.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lihongzheng
 * @date 2021/6/15
 * @description application.yml setting
 */
@Component
@ConfigurationProperties(prefix = "app")
public class Setting {
    private String environment;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
