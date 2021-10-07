package za.co.nwu.accountsystemapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class AccountSystemApiApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(AccountSystemApiApplication.class, args);
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("za.co.nwu.accountsystemapi"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Account System API",
                "CMPG 323 - Discovery Java SpringBoot Account System API project  one",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Lloyd Janse van Rensburg", null, "lloydjvrensburg@gmail.com"),
                "API License",
                "http://localhost:8080",
                Collections.emptyList()
        );
    }
}
