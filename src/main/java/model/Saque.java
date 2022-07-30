package model;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns="/Saque")
public class Saque extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Banco banco = new Banco();
		
		String inputConta = request.getParameter("conta");
		int conta = Integer.parseInt(inputConta);
		
		List<Conta> lista = banco.getContas();
		
		Conta account = banco.getContaByAccount(lista, conta);
		
		request.setAttribute("conta", account.getConta());
		request.setAttribute("saldo", account.getSaldo());
		 
		RequestDispatcher rd = request.getRequestDispatcher("/Saque.jsp");
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Banco banco = new Banco();
		List<Conta> lista = banco.getContas();
		Extrato extrato = new Extrato();
		
		Date dataAtual = new Date();
		
		String inputValor = request.getParameter("valor");
		String inputConta = request.getParameter("conta");
		
		double valor = Double.parseDouble(inputValor);
		int conta = Integer.parseInt(inputConta);
		
		Conta account = banco.getContaByAccount(lista, conta);
		
		account.saca(valor);
		request.setAttribute("conta", conta);
		request.setAttribute("valor", valor);
		request.setAttribute("saldoAtual", account.getSaldo());
		
		extrato.setTipoExtrato("Saque");
		extrato.setValor(-valor);
		extrato.setSaldoMomento(account.getSaldo());
		extrato.setConta(account);
		extrato.setDataCadastro(dataAtual);
		
		banco.adicionaExtrato(extrato);
		
		RequestDispatcher rd = request.getRequestDispatcher("/SaqueMsg.jsp");
		rd.forward(request, response);
	}

}
