package todo.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;

//Aplica um Filter que interceptar� todos as URL requisitadas. Este filter � do Spring, COm ele � poss�vel utilziar os recursos do SPring Secuirty na sua aplica��o
@Component
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
