package com.luciene.usuario.infrastructure.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class WebConfig {

    public void addCorsMappings(CorsRegistry registry) {
        // Permitir requisições do frontend (em http://localhost:3000) para o backend
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")  // Aqui você coloca a URL do seu frontend (onde está rodando o React/Vue)
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos permitidos
                .allowedHeaders("*");  // Permite todos os cabeçalhos
    }
}
