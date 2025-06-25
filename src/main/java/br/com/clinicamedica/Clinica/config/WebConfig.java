package br.com.clinicamedica.Clinica.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    //http://localhost:8000/clinica/api/swagger-ui/index.html
        //http://localhost:8000/clinica/api/api-docs

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("POST", "PUT", "GET", "DELETE",
                        "OPTIONS", "HEAD", "TRACE", "CONNECT")
                .allowedOrigins("http://localhost:3000","http://localhost:8000")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}