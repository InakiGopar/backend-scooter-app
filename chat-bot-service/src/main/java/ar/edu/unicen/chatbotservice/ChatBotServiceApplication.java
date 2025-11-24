package ar.edu.unicen.chatbotservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
        org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class
})
@EnableFeignClients
public class ChatBotServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatBotServiceApplication.class, args);
    }

}
