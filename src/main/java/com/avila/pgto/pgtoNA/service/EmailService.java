package com.avila.pgto.pgtoNA.service;

import com.avila.pgto.pgtoNA.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;


public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);

}
