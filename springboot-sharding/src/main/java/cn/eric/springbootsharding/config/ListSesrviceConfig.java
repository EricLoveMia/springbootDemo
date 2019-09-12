package cn.eric.springbootsharding.config;

import cn.eric.springbootsharding.service.ListService;
import cn.eric.springbootsharding.service.impl.OnUnixCondition;
import cn.eric.springbootsharding.service.impl.OnWindowsCondition;
import cn.eric.springbootsharding.service.impl.UnixListService;
import cn.eric.springbootsharding.service.impl.WindowsListService;
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
