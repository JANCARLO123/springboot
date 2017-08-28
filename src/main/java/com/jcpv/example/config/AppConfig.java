package com.jcpv.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by JanCarlo on 21/08/2017.
 */
@Configuration
@ComponentScan("com.jcpv.example")
@EnableWebMvc
public class AppConfig {
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver resolver =new InternalResourceViewResolver();
        resolver.setPrefix("WEB-INF/template");
        resolver.setSuffix(".jsp");
        return resolver;
    }


}
