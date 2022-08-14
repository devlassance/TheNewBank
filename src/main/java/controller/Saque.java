package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interfaces.Acao;
import model.Banco;
import model.Conta;
import model.Extrato;


public class Saque implements Acao {
       
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response, Banco banco)
			throws ServletException, IOException {
		
		boolean isPost = "POST".equals(request.getMethod());
		
		String inputConta = request.getParameter("conta");
		int conta = Integer.parseInt(inputConta);
		
		if(isPost) {
			String inputValor = request.getParameter("valor");
			
			double valor = Double.parseDouble(inputValor);
			
			Conta account = banco.getContaByAccount(conta);
			
			account.saca(valor);
			request.setAttribute("conta", conta);
			request.setAttribute("valor", valor);
			request.setAttribute("saldoAtual", account.getSaldo());
			
			Date dataAtual = new Date();
			Extrato extrato = new Extrato("Saque", -valor, account.getSaldo(), account, dataAtual);
					
			banco.adicionaExtrato(extrato);
			
			return "forward:SaqueMsg.jsp";
		}
				
		Conta account = banco.getContaByAccount(conta);
		
		request.setAttribute("conta", account.getConta());
		request.setAttribute("saldo", account.getSaldo());
		return "forward:Saque.jsp";
	}

}
