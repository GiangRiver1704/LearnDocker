package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.validation.productValidation;

@Configuration
@EnableWebMvc
public class webConfig implements WebMvcConfigurer {
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource message = new ReloadableResourceBundleMessageSource();
		message.setBasename("classpath:validation");
		message.setDefaultEncoding("UTF-8");
		return message;
	}
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler(
	                "/webjars/**",
	                "/img/**",
	                "/css/**",
	                "/js/**")
	                .addResourceLocations(
	                        "classpath:/META-INF/resources/webjars/",
	                        "classpath:/static/img/",
	                        "classpath:/static/css/",
	                        "classpath:/static/js/");
	    }
	 @Bean
	 public productValidation productValidation() {
		 return new productValidation();
	 }

}
