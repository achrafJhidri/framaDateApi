package com.example.framadate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
    Contact contact = new Contact(
            "Achraf JHIDRI",
            "https://www.github.com/achrafjhidri",
            "achrafjh@gmail.com"
    );

    List<VendorExtension> vendorExtensions = new ArrayList<>();

    ApiInfo apiInfo = new ApiInfo(
            "FramaDate Demo app RESTful Web Service documentation",
            "This pages documents FramaDate a RESTful Web Service endpoints", "1.0",
            "https://www.github.com/achrafjhidri", contact,
            "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", vendorExtensions);

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.framadate"))
                .paths(PathSelectors.any())
                .build();
    }
}