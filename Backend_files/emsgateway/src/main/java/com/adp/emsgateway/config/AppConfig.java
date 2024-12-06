package com.adp.emsgateway.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//
//@Configuration
//public class AppConfig extends CorsConfiguration{
//
//    @Bean
//    public RestTemplate template(){
//       return new RestTemplate();
//    }
//    
////    @Bean
////    public CorsWebFilter corsWebFilter(){
////    	CorsConfiguration corsConfig =new CorsConfiguration();
////    	corsConfig.addAllowedOrigin("*");
////    	corsConfig.addAllowedMethod("*");
////    	corsConfig.addAllowedHeader("*");
////    	corsConfig.setAllowCredentials(true);
////    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////    	source.registerCorsConfiguration("/**", corsConfig);   	
////    	
////    	return new CorsWebFilter(source);
////    }
//}
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//
//@Configuration
//public class AppConfig {
//
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    @Bean
//    public CorsWebFilter corsWebFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("*"); // Allow all origins (modify as needed for security)
//        config.addAllowedMethod("*"); // Allow all HTTP methods
//        config.addAllowedHeader("*"); // Allow all headers
//        config.setAllowCredentials(true); // Allow cookies or authentication headers
//
//        source.registerCorsConfiguration("/**", config);
//        return new CorsWebFilter(source);
//    }
//}
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class AppConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); 
        config.addAllowedOrigin("https://ephemeral-choux-4b34d5.netlify.app/");// Allow requests from frontend
        config.addAllowedHeader("*"); // Allow all headers
        config.addAllowedMethod("*"); // Allow all HTTP methods

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
