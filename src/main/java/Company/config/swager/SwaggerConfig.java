package Company.config.swager;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;


@Configuration
public class SwaggerConfig {
//    private static final String API_KEY = "Bearer Token ";
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components()
//                        .addSecuritySchemes(API_KEY, (io.swagger.v3.oas.models.security.SecurityScheme) apiKeySecuritySchema()))
//                .info(new Info().title("Restaurant"))
//                .security(Collections.singletonList(new SecurityRequirement().addList(API_KEY)));
//    }
//
//    public SecurityScheme apiKeySecuritySchema() {
//        return new SecurityScheme()
//                .name("Auth API")
//                .description("Please, put the token")
//                .in(SecurityScheme.In.HEADER)
//                .type(SecurityScheme.Type.HTTP)
//                .scheme("Bearer");
//    }
}

