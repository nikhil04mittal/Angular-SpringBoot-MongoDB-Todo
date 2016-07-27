package com.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.InternalResourceViewResolver

/**
 * Created by ttnd on 7/1/16.
 */

@Configuration
@EnableWebMvc
class WebMvcConfig extends WebMvcConfigurerAdapter {
    private static Logger LOGGER = LoggerFactory.getLogger(WebMvcConfig.class)

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/html/");
        resolver.setSuffix(".html");
        return resolver;
    }
}