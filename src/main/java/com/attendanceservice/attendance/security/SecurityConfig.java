package com.attendanceservice.attendance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class SecurityConfig extends WebMvcConfigurerAdapter {

    private final static int ORDER = 1;
    private final static String REGISTRATION_BEAN_NAME = "AuthenticatedRoutes";
    private final static String AUTHENTICATION_NOT_REQUIRED_PATH_TOKEN = "/api/token";
    private final static String AUTHENTICATION_NOT_REQUIRED_PATH_REFRESH = "/api/register";
    private final static String AUTHENTICATION_NOT_REQUIRED_PATH_LOGOUT = "/api/logout";

    private final JwtToken jwtToken;

    @Autowired
    public SecurityConfig(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }


    @Bean
    public FilterRegistrationBean authenticatedRoutesFilter (){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName(REGISTRATION_BEAN_NAME);
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationNotRequiredPathMatcher(), jwtToken);
        registrationBean.setFilter(authenticationFilter);
        registrationBean.setOrder(ORDER);
        return registrationBean;
    }

    @Bean
    public AuthenticationNotRequiredPathMatcher authenticationNotRequiredPathMatcher(){
        return new AuthenticationNotRequiredPathMatcherImplementation()
                .addEndPoint(AUTHENTICATION_NOT_REQUIRED_PATH_TOKEN)
                .addEndPoint(AUTHENTICATION_NOT_REQUIRED_PATH_REFRESH)
                .addEndPoint(AUTHENTICATION_NOT_REQUIRED_PATH_LOGOUT);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
