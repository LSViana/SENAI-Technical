package todo.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;

//Aplica um Filter que interceptará todos as URL requisitadas. Este filter é do Spring, COm ele é possível utilziar os recursos do SPring Secuirty na sua aplicação
@Component
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
