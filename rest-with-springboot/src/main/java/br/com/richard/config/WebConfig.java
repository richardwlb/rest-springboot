package br.com.richard.config;

import java.util.List;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.richard.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMessageConverter());
    }

    // Configurando CORS globalmente com esse método
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
            .allowedMethods("GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        
        /*  --- Content Negotiation ---
            Expõe em JSON, XML e etc.
            3 formas de fazer
        */

        // ***** Uma forma de fazer *****
        // http://localhost:8080/api/person/v1.json
        // configurer.favorParameter(false)
        //     .ignoreAcceptHeader(false)
        //     .defaultContentType(MediaType.APPLICATION_JSON)
        //     .mediaType("json", MediaType.APPLICATION_JSON)
        //     .mediaType("xml", MediaType.APPLICATION_XML);

        // ***** Via Query Param *****
        // Ex: http://localhost:8080/api/person/v1?mediaType=xml
        // configurer.favorPathExtension(false)
        //     .favorParameter(true)
        //     .parameterName("mediaType")
        //     .ignoreAcceptHeader(true)
        //     .useRegisteredExtensionsOnly(false)
        //     .defaultContentType(MediaType.APPLICATION_JSON)
        //     .mediaType("json", MediaType.APPLICATION_JSON)
        //     .mediaType("xml", MediaType.APPLICATION_XML);

                    // ***** Uma forma de fazer *****
        // Ex: http://localhost:8080/api/person/v1
        // No header:
        // Accept: application/xml
        configurer.favorPathExtension(false)
            .favorParameter(false)
            .ignoreAcceptHeader(false)
            .useRegisteredExtensionsOnly(false)
            .defaultContentType(MediaType.APPLICATION_JSON)
            .mediaType("json", MediaType.APPLICATION_JSON)
            .mediaType("xml", MediaType.APPLICATION_XML)
            .mediaType("x-yaml", MEDIA_TYPE_YML);
    } 
}
