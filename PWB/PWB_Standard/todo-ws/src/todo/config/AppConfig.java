package todo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("todo")
@Import({PersistenceConfig.class, SecurityConfig.class,  WebConfig.class})
public class AppConfig {
	
	
}
