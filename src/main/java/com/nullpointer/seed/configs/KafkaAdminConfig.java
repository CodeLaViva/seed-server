package com.nullpointer.seed.configs;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lihongzheng
 * @date 2021/8/24
 * @description kafka admin config
 */
@EnableKafka
@Configuration
public class KafkaAdminConfig {
    /**
     * yml配置文件中的变量
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String hosts;

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>(1);
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, hosts);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic seedTopic(){
        // 第一个是参数是topic名字，第二个参数是分区个数
        // 第三个是topic的复制因子个数
        // ----------------->>>>>>>>>>>>>>>当broker个数为1个时会创建topic失败，
        //提示：replication factor: 2 larger than available brokers: 1
        //只有在集群中才能使用kafka的备份功能
        return new NewTopic("seedTopic3", 3, (short) 3);
    }

//    @Bean
//    public NewTopic topic2(){
//        return new NewTopic("", 5, (short) 1);
//    }
//
//    @Bean
//    public NewTopic topic3(){
//        return new NewTopic("", 5, (short) 3);
//    }
//
//    @Bean
//    public NewTopic topic4(){
//        return new NewTopic("", 3, (short) 2);
//    }
}