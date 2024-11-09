package com.example.denuncia;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permite CORS para todos os endpoints
                        .allowedOrigins("http://localhost:5173") // Permite apenas o frontend em localhost:5173
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                        .allowedHeaders("*"); // Permite todos os cabeçalhos
            }
        };
    }
}
