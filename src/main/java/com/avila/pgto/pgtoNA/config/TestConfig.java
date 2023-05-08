package com.avila.pgto.pgtoNA.config;

import com.avila.pgto.pgtoNA.service.DBService;
import com.avila.pgto.pgtoNA.service.EmailService;
import com.avila.pgto.pgtoNA.service.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Value("spring.jpa.hibernate.ddl-auto=create")
    private String strategy;


    @Bean
    public boolean instantiateDataBase() throws ParseException {
     if(!"create".equals(strategy)) {
         return false;
     }
        dbService.InstateateTestDataBase();
        return true;
    }

    @Bean
    public EmailService emailService(){
    return new MockEmailService();
    }
}