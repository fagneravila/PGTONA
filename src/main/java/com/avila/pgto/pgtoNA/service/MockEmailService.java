package com.avila.pgto.pgtoNA.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService{

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
    @Override
    public void sendEmail(SimpleMailMessage msg) {
       LOG.info("Simulando envio de e-mail");
       LOG.info(msg.toString());
       LOG.info("Envio de e-mail");
    }
}
