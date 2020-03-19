package com.laratecsys.jobfinder.services;

import org.springframework.mail.SimpleMailMessage;

import com.laratecsys.jobfinder.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
}
