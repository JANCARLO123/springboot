package com.jcpv.example.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by JanCarlo on 15/09/17.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.jcpv.example.entity"})
@EnableJpaRepositories(basePackages = {"com.jcpv.example.repository"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
