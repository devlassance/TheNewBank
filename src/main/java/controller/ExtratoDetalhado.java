package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Banco;
import model.Conta;
import model.Extrato;

@WebServlet(urlPatterns="/ExtratoDetalhado")
public class ExtratoDetalhado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Banco banco = new Banco();
		
		String inputConta = request.getParameter("conta");
		int conta = Integer.parseInt(inputConta);
		
		Conta account = banco.getContaByAccount(conta);
		List<Extrato> extratos = banco.getExtratoByAccount(account);
		
		request.setAttribute("conta", account.getConta());
		request.setAttribute("extratos", extratos);
	
		
		RequestDispatcher rd = request.getRequestDispatcher("/ExtratoDetalhado.jsp");
		rd.forward(request, response);
	}

}
