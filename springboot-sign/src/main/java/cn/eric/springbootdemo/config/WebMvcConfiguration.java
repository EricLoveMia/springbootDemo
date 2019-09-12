package cn.eric.springbootdemo.config;

import cn.eric.springbootdemo.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: WebMvcConfiguration
 * @Description: TODO
 * @company lsj
 * @date 2019/9/12 15:31
 **/
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    private static final String[] excludePathPatterns = {"/api/token/api_token"};

    @Resource
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/api/**").excludePathPatterns(excludePathPatterns);
    }

}
