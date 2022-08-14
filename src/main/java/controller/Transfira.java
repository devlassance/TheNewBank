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

public class Transfira implements Acao {
       	
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response, Banco banco)
			throws ServletException, IOException {
		
		boolean isPost = "POST".equals(request.getMethod());
		
		if(isPost) {
			String inputBeneficiario = request.getParameter("contaBeneficiaria");
			String inputTitular = request.getParameter("contaTitular");
			String inputValor = request.getParameter("valor");
			
			int nrBeneficiario = Integer.parseInt(inputBeneficiario);
			int nrTitular = Integer.parseInt(inputTitular);
			double valor = Double.parseDouble(inputValor);
			
			
			Conta beneficiario = banco.getContaByAccount(nrBeneficiario);
			Conta titular = banco.getContaByAccount(nrTitular);
			
			titular.saca(valor);
			beneficiario.deposita(valor);
			
			request.setAttribute("conta", nrTitular);
			request.setAttribute("valor", valor);
			request.setAttribute("saldoAtual", titular.getSaldo());
			
			request.setAttribute("nomeBeneficiario", beneficiario.getTitular().getNome());
			request.setAttribute("contaBeneficiario", beneficiario.getConta());
				
			Date dataAtual = new Date();
			
			Extrato extrato = new Extrato("Transferência", -valor, titular.getSaldo(), titular, dataAtual);
			Extrato extratoBeneficiario = new Extrato("Transferência", valor, beneficiario.getSaldo(), beneficiario, dataAtual);
					
			banco.adicionaExtrato(extrato);
			banco.adicionaExtrato(extratoBeneficiario);
			
			return "forward:TransfiraMsg.jsp";
		}
				
		String inputConta = request.getParameter("conta");
		int conta = Integer.parseInt(inputConta);
		
		Conta account = banco.getContaByAccount(conta);
		
		request.setAttribute("conta", account.getConta());
		request.setAttribute("saldo", account.getSaldo());
		
		return "forward:Transfira.jsp";
	}
	

}
