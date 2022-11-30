package com.loveprogrammer.springboot.elasticsearch.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: Swagger2Config
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 16:34
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("demo").apiInfo(apiInfo()).select()
                // 设置basePackage会将包下的所有类的所有方法作为api
//                .apis(RequestHandlerSelectors.basePackage("com.example.demo2.controller"))
                // 只有标记@ApiOperation才会暴露出给swagger
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.regex("/api/.*")).build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder().title("API接口文档").description("RESTful风格接口").termsOfServiceUrl("https://blog.csdn.net/vbirdbest")  // 服务条款网址
                .version("1.0").contact(new Contact("eric", "http://www.xxx.com", "money9sun@163.com")).build();
    }
}
