package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.Acao;
import model.Banco;
import model.Conta;
import model.Extrato;

public class ExtratoDetalhado implements Acao {
       
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response, Banco banco)
			throws ServletException, IOException {
		
		HttpSession sessao = request.getSession();
		
		if(sessao.getAttribute("contaLogada") != null) {
			Conta account = (Conta)sessao.getAttribute("contaLogada");
			ArrayList<Extrato> extratos = banco.getExtratos(account.getId());
			
			request.setAttribute("conta", account.getConta());
			request.setAttribute("extratos", extratos);
			
			return "forward:ExtratoDetalhado.jsp";
		}
		
		return "redirect:/TheNewBank";
		
	}

}
