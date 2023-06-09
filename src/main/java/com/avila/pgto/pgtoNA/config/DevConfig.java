package com.avila.pgto.pgtoNA.config;

import com.avila.pgto.pgtoNA.service.DBService;
import com.avila.pgto.pgtoNA.service.EmailService;
import com.avila.pgto.pgtoNA.service.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;
    @Bean
    public boolean instantiateDataBase() throws ParseException {
      dbService.InstateateTestDataBase();
        return true;
    }
    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }

}
