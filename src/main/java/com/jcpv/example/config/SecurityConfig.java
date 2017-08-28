package com.jcpv.example.config;

;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by JanCarlo on 21/08/2017.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);


    protected void config(HttpSecurity http)throws Exception{
//
//               http.authorizeRequests()
//                .antMatchers("/user/**").hasAnyRole("ADMIN","USER")
//                .and().formLogin()
//                .loginPage("")
//                .loginProcessingUrl("")
//                .usernameParameter("")
//                .passwordParameter("")
//                .defaultSuccessUrl("")
//                .and().logout()
//                .logoutUrl("")
//                .logoutSuccessUrl("")
//                .and().exceptionHandling()
//                .accessDeniedPage("");
        http.authorizeRequests()
                .antMatchers("/user/**").hasAnyRole("ADMIN","USER")
                .and().formLogin()  //login configuration
                .loginPage("/customLogin.jsp")
                .loginProcessingUrl("/appLogin")
                .usernameParameter("app_username")
                .passwordParameter("app_password")
                .defaultSuccessUrl("/user/home")
                .and().logout()    //logout configuration
                .logoutUrl("/appLogout")
                .logoutSuccessUrl("/customLogin.jsp")
                .and().exceptionHandling() //exception handling configuration
                .accessDeniedPage("/user/error");


    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(MyUserDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

}
