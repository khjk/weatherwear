package com.kitri.weatherwear.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /*
    https://localhost:8088/v2/api-docs
    https://localhost:8088/swagger-ui.html
     */
    private static final Contact DEFAULT_CONTACT = new Contact("Hyojung Kang",
            "https://github.com/khjk/weatherwear","begywjd@naver.com");

    private ApiInfo apiInfo() {
<<<<<<< HEAD
        return new ApiInfoBuilder().title("WeatherWear API")
                .description("We provide the right outfit for the weather")
                .version("1.0")
                .contact(DEFAULT_CONTACT)
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0").build();
=======
       return new ApiInfoBuilder().title("WeatherWear API")
               .description("We provide the right outfit for the weather")
               .version("1.0")
               .contact(DEFAULT_CONTACT)
               .license("Apache 2.0")
               .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0").build();
>>>>>>> origin/develop
    }
    private static final String DEFAULT_PRODUCES_AND_CONSUMES = "application/json";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .produces(Collections.singleton(DEFAULT_PRODUCES_AND_CONSUMES))
                .consumes(Collections.singleton(DEFAULT_PRODUCES_AND_CONSUMES));
    }
    /*
    https://localhost:8088/actuator/health SERVER UP-DOWN 상태확인가능
    */
}
