package com.jcpv.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //Banner
        //SpringApplication application = new SpringApplication(Application.class);
        //application.setBannerMode(Banner.Mode.LOG);
        //Profiles
        //ConfigurableEnvironment environment = new StandardEnvironment();
        //environment.setDefaultProfiles("dev","animal_dev");
        //application.setEnvironment(environment);
        //application.run(args);
         //Paramtros
       // Map<String, Object> map = new HashMap<>();
       // map.put("SERVER_CONTEXT_PATH", "/spring-boot-app");
       // map.put("SERVER_PORT", "8585");
       // application.setDefaultProperties(map)
        logger.debug("--Application Started--");
    }
}
