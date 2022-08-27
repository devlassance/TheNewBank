package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.Acao;
import model.Banco;
import model.Conta;
import model.Extrato;

public class Transfira implements Acao {
       	
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response, Banco banco)
			throws ServletException, IOException {
		
		boolean isPost = "POST".equals(request.getMethod());
		
		HttpSession sessao = request.getSession();
		
		if(sessao.getAttribute("contaLogada") != null) {
			Conta titular = (Conta)sessao.getAttribute("contaLogada");
			
			if(isPost) {
				String inputBeneficiario = request.getParameter("contaBeneficiaria");
				String inputValor = request.getParameter("valor");
				
				int nrBeneficiario = Integer.parseInt(inputBeneficiario);
				double valor = Double.parseDouble(inputValor);
				
				
				Conta beneficiario = banco.getContaByAccount(nrBeneficiario);
				
				titular.saca(valor);
				beneficiario.deposita(valor);
				
				request.setAttribute("conta", titular.getConta());
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
			
			request.setAttribute("conta", titular.getConta());
			request.setAttribute("saldo", titular.getSaldo());
			
			return "forward:Transfira.jsp";
		}
		
		return "redirect:/TheNewBank";
		
	}
	

}
