package com.loveprogrammer.springboot.elasticsearch.config;

import com.loveprogrammer.springboot.elasticsearch.service.ListService;
import com.loveprogrammer.springboot.elasticsearch.service.impl.OnUnixCondition;
import com.loveprogrammer.springboot.elasticsearch.service.impl.OnWindowsCondition;
import com.loveprogrammer.springboot.elasticsearch.service.impl.UnixListService;
import com.loveprogrammer.springboot.elasticsearch.service.impl.WindowsListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: ListSesrviceConfig
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 16:04
 **/
@Configuration
public class ListSesrviceConfig {

    @Bean
    @Conditional(OnWindowsCondition.class)
    public ListService windowsListService() {
        return new WindowsListService();
    }

    @Bean
    @Conditional(OnUnixCondition.class)
    public ListService UnixListService() {
        return new UnixListService();
    }
}
