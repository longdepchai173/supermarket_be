package com.project.supermarket_be.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

public class AppConfiguration {
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        FilterRegistrationBean<CorsFilter> registrationBean =
                new FilterRegistrationBean<>(new CorsFilter());
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean<TokenAdminValidationFilter> tokenAdminValidationFilter() {
        FilterRegistrationBean<TokenAdminValidationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenAdminValidationFilter());
        registrationBean.addUrlPatterns("/api/accounts/*");
        return registrationBean;
    }
}
