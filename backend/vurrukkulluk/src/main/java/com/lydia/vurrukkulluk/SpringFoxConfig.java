package com.lydia.vurrukkulluk;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

// Go to http://localhost:8080/swagger-ui/index.html for the documentation
@Configuration
public class SpringFoxConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.any())
      .build()
      .apiInfo(apiInfo());
  }
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  private ApiInfo apiInfo() {
    ApiInfo apiInfo = new ApiInfo("Service API", "Simple REST Service", "0.0.1",
      "mail@mail.com", "mail@mail.com", " ", " ");
    return apiInfo;
  }
}
