package com.lydia.vurrukkulluk;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

// Go to http://localhost:8080/swagger-ui/index.html for the documentation
// We use SpringDoc. See https://springdoc.org/migrating-from-springfox.html to find out which annotations to use
@Configuration
public class SpringDocConfig {
  @Bean
  public Docket apiDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.any())
      .paths(PathSelectors.any())
      .build();
  }
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }


}
