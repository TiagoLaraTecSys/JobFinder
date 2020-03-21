package com.laratecsys.jobfinder.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.laratecsys.jobfinder.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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

	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("/email/confirmacaoEmail", context);
		
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
		
		MimeMessage mm;
		try {
			mm = prepareMimeMessage(pedido);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			sendOrderConfirmationEmail(pedido);
		}
		
	}
	
	protected MimeMessage prepareMimeMessage(Pedido obj) throws MessagingException {
		
		MimeMessage newMM = javaMailSender.createMimeMessage();
		MimeMessageHelper newMMH = new MimeMessageHelper(newMM,true);
		
		newMMH.setTo(obj.getCliente().getEmail());
		newMMH.setFrom(sender);
		newMMH.setSubject("Confirmação de Pedido, código: " + obj.getId());
		newMMH.setSentDate(new Date(System.currentTimeMillis()));
		
		newMMH.setText(htmlFromTemplatePedido(obj),true);
		
		return newMM;
		
		
		
	}
}
