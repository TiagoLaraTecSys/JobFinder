package com.laratecsys.jobfinder.services;

import java.util.Date;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.laratecsys.jobfinder.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date date) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDataVencimento(cal.getTime());
	}
}
