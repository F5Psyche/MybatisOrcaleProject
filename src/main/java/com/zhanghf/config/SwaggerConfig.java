package com.zhanghf.config;

import io.swagger.annotations.Api;
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
 * @author zhanghf
 * @version 1.0
 * @date 10:49 2020/11/30
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                //扫描有Api注解的Controller
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build().apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("", "", "");
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        return apiInfoBuilder.title("API接口")
                .description("")
                .version("1.0")
                .termsOfServiceUrl("")
                .contact(contact)
                .license("")
                .licenseUrl("")
                .build();
    }
}
