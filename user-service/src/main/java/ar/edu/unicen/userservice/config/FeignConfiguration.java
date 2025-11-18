package ar.edu.unicen.userservice.config;

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
