package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.Acao;
import model.Banco;
import model.Conta;

public class LogarConta implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response, Banco banco)
			throws ServletException, IOException {
		
		boolean isPost = "POST".equals(request.getMethod());
		if(isPost) {
			
			String inputConta = request.getParameter("conta");
			String inputAgencia = request.getParameter("agencia");
			String password = request.getParameter("senha");
			
			int nrConta = Integer.parseInt(inputConta);
			int nrAgencia = Integer.parseInt(inputAgencia);
			
			if(banco.authAccount(nrConta, nrAgencia, password)) {
				
				//Setando a conta logada dentro da sessão
				Conta conta = banco.getContaByAccount(nrConta);
				HttpSession sessao = request.getSession();
			    sessao.setAttribute("contaLogada", conta);
				
				return "redirect:DetalhesConta";
			}
		}
		return "redirect:/TheNewBank";
	}

}
	