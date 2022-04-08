package ir.blacksparrow.websitebackend.swagger;


import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
//@EnableSwagger2
public class Config {
    @Bean
    public Docket DocketSwaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .groupName("springshop-public")
                .apiInfo(ApiInfo());
    }

    private ApiInfo ApiInfo() {
        return new ApiInfoBuilder()
                .title("Black Sparrow Api Documentation")
                .description("Black Sparrow Documentation - For Postman: /postman instead of /docs.")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("Terms of Service")
                .version("1.0.0")
                .contact(new Contact("Black Sparrow", "https://blackSparrow.ir/", "esmirk.137@gmail.com"))
                .build();
    }

    private Tag[] ApiDescriptions() {
        return new Tag[]{
                new Tag("Category element", "Category elements are items which........"),
        };
    }
}
