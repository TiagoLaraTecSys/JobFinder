package com.laratecsys.jobfinder.services;

import java.awt.SystemColor;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.laratecsys.jobfinder.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		
		SimpleMailMessage sm = prepareSimpleMailMessage(pedido);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessage(Pedido pedido) {
		SimpleMailMessage newSMM = new SimpleMailMessage();
		
		newSMM.setTo(pedido.getCliente().getEmail());
		newSMM.setFrom(sender);
		newSMM.setSubject("Confirmação de Pedido, código: " + pedido.getId());
		newSMM.setSentDate(new Date(System.currentTimeMillis()));
		newSMM.setText(pedido.toString());
		return newSMM;
	}

	
}
