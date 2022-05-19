package com.muah.muahbackend.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket api() {
        // Authorization header 글로벌 처리
        List global = new ArrayList();
        global.add(new ParameterBuilder()
                .name("Authorization")
                .description("Access Token")
                .parameterType("header")
                .required(true)
                .modelRef(new ModelRef("string"))
                .scalarExample("Bearer AAA.BBB.CCC")
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(global)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Muah API Docs")
                .version("1.0")
                .description("무아 API 명세서")
                .build();
    }

    /*
    * swagger-ui 페이지 연결 핸들러 설정
    * base_url/swagger-ui.html 로 접근할 수 있다.
    * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        super.addResourceHandlers(registry);
    }

}
