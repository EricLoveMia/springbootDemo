package com.loveprogrammer.springboot.thrift.client.config;

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
 * @ClassName SwaggerConfig
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/8/2
 * @Version V1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Create rest api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.eric.thrift.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo metaData() {

        return new ApiInfoBuilder()
                .title("SpringBoot Thrift Demo API文档")
                .description("SpringBoot 集成Thrift 所记录")
                .termsOfServiceUrl("")
                .contact(new Contact("eric", "", "money9sun@163.com"))
                .version("1.0")
                .build();
    }
}
