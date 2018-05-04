package senai.sstorage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import senai.sstorage.utils.WebUtils;

//import senai.sstorage.interceptors.AuthenticationInterceptor;

@Configuration
@EnableWebMvc
@Import(value = { PersistenceConfig.class, SecurityConfig.class })
@ComponentScan(value = "senai.sstorage")
public class AppConfig implements WebMvcConfigurer {
	
	public static final String
	DB_HOST = "localhost",
	DB_PORT = "3306",
	DB_NAME = "sstorage",
	DB_USER = "root",
	DB_PASS = "root132",
	MODELS_PACKAGE = "senai.sstorage.models";
	
	@Bean
	public WebUtils getWebUtils() {
		return new WebUtils();
	}
	
//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setViewClass(JstlView.class);
//		resolver.setPrefix("/WEB-INF/views/");
//		resolver.setSuffix(".jsp");
//		registry.viewResolver(resolver);
//	}
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry
//			.addResourceHandler("/assets/**")
//			.addResourceLocations("/assets/");
//		registry
//			.addResourceHandler("/resources/**")
//			.addResourceLocations("/resources/");
//	}
	
//	@Bean
//	public MessageSource messageSource() {
//		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("/WEB-INF/messages/validations");
//		messageSource.setDefaultEncoding("UTF-8");
//		messageSource.setCacheSeconds(1);
//		return messageSource;
//	}
	
//	@Bean
//	public AuthenticationInterceptor getAuthenticationInterceptor() {
//		return new AuthenticationInterceptor();
//	}
	
//	@Bean
//	public MultipartResolver getMultipartResolver() {
//		CommonsMultipartResolver mpr = new CommonsMultipartResolver();
//		mpr.setMaxUploadSize(5 * 1024 * 1024);
//		return mpr;
//	}
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(getAuthenticationInterceptor())
//		.addPathPatterns("/**");
//	}

}
