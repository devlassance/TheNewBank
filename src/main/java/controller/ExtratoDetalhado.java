package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interfaces.Acao;
import model.Banco;
import model.Conta;
import model.Extrato;

public class ExtratoDetalhado implements Acao {
       
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response, Banco banco)
			throws ServletException, IOException {
		
		String inputConta = request.getParameter("conta");
		int conta = Integer.parseInt(inputConta);
		
		Conta account = banco.getContaByAccount(conta);
		List<Extrato> extratos = banco.getExtratoByAccount(account);
		
		request.setAttribute("conta", account.getConta());
		request.setAttribute("extratos", extratos);
		
		return "forward:ExtratoDetalhado.jsp";
	}

}
