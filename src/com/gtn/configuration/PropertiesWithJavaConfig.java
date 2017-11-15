package com.gtn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:email.properties")
public class PropertiesWithJavaConfig {
	 @Bean
	 public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	     return new PropertySourcesPlaceholderConfigurer();
	 }
}