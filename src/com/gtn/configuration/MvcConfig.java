package com.gtn.configuration;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableWebMvc
@Import({DatabaseConfig.class,SecurityConfig.class,SchedularConfig.class})
@ComponentScan(basePackages = "com.gtn.*")
public class MvcConfig extends WebMvcConfigurerAdapter {
	private static final Logger logger = LoggerFactory
			.getLogger(MvcConfig.class);

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	    configurer.enable();
	}
	@Bean(name={"multipartResolver"})
	  public CommonsMultipartResolver multipartResolver()
	  {
		logger.info("configured multipart");
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(20971520L);
	    multipartResolver.setMaxInMemorySize(1048576);
	    return multipartResolver;
	  }
}
