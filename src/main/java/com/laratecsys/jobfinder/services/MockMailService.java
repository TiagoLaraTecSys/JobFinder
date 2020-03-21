package com.laratecsys.jobfinder.services;

import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;

public class MockMailService extends AbstractEmailService{

	private static final Logger LOG = LoggerFactory.getLogger(MockMailService.class);
	@Override
	public void sendEmail(SimpleMailMessage msg) {

		LOG.info("Iniciando Serivço de Email...");
		LOG.info(msg.toString());
		LOG.info("Email enviado!");
		
	}
	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Iniciando Serivço de Email Html...");
		LOG.info(msg.toString());
		LOG.info("Email enviado!");
	}

}
