package com.mumu.park.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 当业务模块需要调用第三方HTTP接口时使用
 * 比方说调用微信支付，商城项目进行短信发送
 * 后面只要需要用到第三方接口，就直接在service层进行依赖注入就好
 */

@Configuration
public class RestTemplateConfiguration {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}