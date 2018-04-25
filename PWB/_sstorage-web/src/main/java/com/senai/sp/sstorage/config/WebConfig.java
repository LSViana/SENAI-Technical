package com.senai.sp.sstorage.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.senai.sp.sstorage.interceptors.InterceptorAuthentication;

@Configuration
@EnableWebMvc
@Import(value = PersistenceConfig.class)
@ComponentScan(value = "com.senai.sp.sstorage")
public class WebConfig implements WebMvcConfigurer {
	
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setViewClass(JstlView.class);
		irvr.setPrefix("/src/main/webapp/WEB-INF/views/");
		irvr.setSuffix(".jsp");
		registry.viewResolver(irvr);
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/src/main/resources/**").addResourceLocations("/resources");
	}
	
	@Bean
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource rrbms = new ReloadableResourceBundleMessageSource();
		rrbms.setBasename("/src/main/webapp/WEB-INF/messages/validations");
		rrbms.setDefaultEncoding("UTF-8");
		rrbms.setCacheSeconds(1);
		return rrbms;
	}
	
	@Bean
	public InterceptorAuthentication getInterceptorAuthentication() {
		return new InterceptorAuthentication();
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(getInterceptorAuthentication())
			.addPathPatterns("/**");
	}

}
