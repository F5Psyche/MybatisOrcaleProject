package com.zhanghf.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
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
public class SwaggerConfig extends WebMvcConfigurationSupport {
    private static final String SWAGGER_SCAN_EXT = "com.zhanghf.controller.ext";

    private static final String SWAGGER_SCAN_IMAGE = "com.zhanghf.controller.image";

    private static final String SPLIT = ";";


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(basePackage(SWAGGER_SCAN_EXT + SPLIT + SWAGGER_SCAN_IMAGE))
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


    private static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            for (String strPackage : basePackage.split(SPLIT)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }
}
