package senai.spring;

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

import senai.tecnow.utils.Interceptor;

@Configuration
@EnableWebMvc
@ComponentScan("senai.tecnow")
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private Interceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Configuring to filter requests
		registry
			.addInterceptor(interceptor)
			.addPathPatterns("/**");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Configuring to use static resources
		registry
			.addResourceHandler("/assets/**")
			.addResourceLocations("/assets/");
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// Configuring to return views through Controllers
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setViewClass(JstlView.class);
		irvr.setPrefix("/WEB-INF/view/");
		irvr.setSuffix(".jsp");
		registry.viewResolver(irvr);
	}
	
}