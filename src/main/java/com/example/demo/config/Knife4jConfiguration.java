package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author
 * @since
 * @version 1.0
 * @desc
**/

@Configuration
public class Knife4jConfiguration {

    @Bean(value = "KnifeApi")
    public Docket knifeApi() {
        Docket docket=new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                //分组
                .groupName("Api Test")
                .select()
                //扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .description("API TEST")
                .termsOfServiceUrl("http://www.test.com/")
                .contact(new Contact("test", "www.test.com", "test@qq.com"))
                .version("1.0")
                .build();
    }
}

