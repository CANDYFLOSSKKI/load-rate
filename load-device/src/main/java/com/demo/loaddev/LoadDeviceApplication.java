package com.demo.loaddev;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.support.converter.MessageConverter;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class LoadDeviceApplication {
    @Bean
    public MessageConverter JsonMsgConverter(){
        return new Jackson2JsonMessageConverter();
    }
    public static void main(String[] args) {
        SpringApplication.run(LoadDeviceApplication.class, args);
    }

}
