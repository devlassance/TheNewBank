package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.Acao;
import model.Banco;
import model.Conta;

public class DetalhesConta implements Acao {
       
	
	public String executa(HttpServletRequest request, HttpServletResponse response, Banco banco) throws ServletException, IOException {
				
		HttpSession sessao = request.getSession();
		
		if(sessao.getAttribute("contaLogada") != null) {
			Conta account = (Conta)sessao.getAttribute("contaLogada");
			
			request.setAttribute("nome", account.getTitular().getNome());
			request.setAttribute("agencia", "000"+account.getAgencia());
			request.setAttribute("conta", account.getConta());
			request.setAttribute("saldo", account.getSaldo());
	        
	        return "forward:DetalhesConta.jsp";
		}
		
		return "redirect:/TheNewBank";
		

    }



}
