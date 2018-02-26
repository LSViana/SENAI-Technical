package com.senai.sp.colliboration.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.senai.sp.colliboration.utils.Interceptor;

/**
 * This annotation determines this class is going to configure Spring Framework
 * @author TI-09
 */
@Configuration
/**
 * This annotation enables Web MVC module at Spring Framework
 * @author TI-09
 */
@EnableWebMvc
/**
 * This annotation tells to Spring Framework where to find Component Classes
 * @author TI-09
 */
@ComponentScan("com.senai.sp.colliboration")
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private Interceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(interceptor)
			.addPathPatterns("/**");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/assets/**")
			.addResourceLocations("/assets/");
	}
	
	@Override
	/**
	 * This method determines where Spring Framework is going to find Views
	 */
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setViewClass(JstlView.class);
		irvr.setPrefix("/WEB-INF/view/");
		irvr.setSuffix(".jsp");
		// Registering the @irvr object at @registry
		registry.viewResolver(irvr);
	}
}
