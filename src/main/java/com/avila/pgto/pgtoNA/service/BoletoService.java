package com.avila.pgto.pgtoNA.service;

import com.avila.pgto.pgtoNA.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instantedoPEdido){

        Calendar cal = Calendar.getInstance();
        cal.setTime(instantedoPEdido);
        cal.add(Calendar.DAY_OF_MONTH,7);
        pagto.setDataVencimento(cal.getTime());

    }
}
