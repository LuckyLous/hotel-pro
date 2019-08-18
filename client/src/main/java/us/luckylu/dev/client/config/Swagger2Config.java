package us.luckylu.dev.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lu
 * @create 2019-03-22 14:09
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .host("intochina.deva.lous.us/clien/api/")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .build()
                .apiInfo(productApiInfo());
    }

    private ApiInfo productApiInfo() {
        return new ApiInfoBuilder()
                .title("客户端")
                .version("1.0")
                .description("文档描述")
                .termsOfServiceUrl("API TERMS URL")
                .license("许可证书")
                .licenseUrl("许可证书url")
                .build();

    }
}
