package ar.edu.unicen.supportservice.config;


import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    @Bean
    public feign.Client feignClient() {
        return new OkHttpClient();
    }
}

