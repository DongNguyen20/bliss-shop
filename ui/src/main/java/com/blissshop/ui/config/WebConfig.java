package com.blissshop.ui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final String EXTERNAL_PATH = "file:/C:/Users/MrDongNguyen/Downloads/design/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/external/**")
                .addResourceLocations(EXTERNAL_PATH);
    }
}
