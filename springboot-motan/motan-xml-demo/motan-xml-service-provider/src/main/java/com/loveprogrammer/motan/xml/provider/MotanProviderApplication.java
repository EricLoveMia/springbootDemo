package com.loveprogrammer.motan.xml.provider;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/***
 * @version 1.0.0
 * @description:
 * @author: eric
 * @date: 2022-11-30 11:03
 **/
@SpringBootApplication
@ImportResource("classpath:motan.xml")
public class MotanProviderApplication {

    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        SpringApplication.run(MotanProviderApplication.class, args);
        // 设置 Motan 开启对外服务
        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
    }
}
