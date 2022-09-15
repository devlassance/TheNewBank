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


public class Saque implements Acao {
       
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response, Banco banco)
			throws ServletException, IOException {
		
		boolean isPost = "POST".equals(request.getMethod());
		
		HttpSession sessao = request.getSession();
		
		if(sessao.getAttribute("contaLogada") != null) {
			Conta account = (Conta)sessao.getAttribute("contaLogada");
			
			if(isPost) {
				String inputValor = request.getParameter("valor");
				
				double valor = Double.parseDouble(inputValor);
				
				banco.setDataByParam("UPDATE Contas SET saldo = saldo - "+valor+" WHERE id = "+account.getId());
				
				account.saca(valor);
				
				double valorNegativado = valor * -1;
				
				banco.setExtrato(2, valorNegativado, account.getSaldo(), account.getId());
				
				request.setAttribute("conta", account.getConta());
				request.setAttribute("valor", valor);
				request.setAttribute("saldoAtual", account.getSaldo());
 				
				return "forward:SaqueMsg.jsp";
			}
			
			request.setAttribute("conta", account.getConta());
			request.setAttribute("saldo", account.getSaldo());
			return "forward:Saque.jsp";
		}
		
		return "redirect:/TheNewBank";
		
	}

}
