package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.example.model.Organization;
import com.example.repository.OrganizationRepository;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
@EnableSwagger2
public class OrganizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationApplication.class, args);
    }

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller")).paths(PathSelectors.any()).build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Organization API")
                        .description("Documentation Organization API v1.0").build());
    }

    @Bean
    OrganizationRepository repository() {
        OrganizationRepository repository = new OrganizationRepository();
        repository.add(new Organization("Microsoft", "Redmond, Washington, USA"));
        repository.add(new Organization("Oracle", "Redwood City, California, USA"));
        return repository;
    }
}
