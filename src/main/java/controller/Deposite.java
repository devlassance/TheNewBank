package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@WebServlet(urlPatterns="/Deposite")
public class Deposite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Banco banco = new Banco();
		
		String inputConta = request.getParameter("conta");
		int conta = Integer.parseInt(inputConta);
				
		Conta account = banco.getContaByAccount(conta);
		
		request.setAttribute("conta", account.getConta());
		request.setAttribute("saldo", account.getSaldo());
		 
		RequestDispatcher rd = request.getRequestDispatcher("/Deposite.jsp");
		
		rd.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Banco banco = new Banco();
		
		String inputValor = request.getParameter("valor");
		String inputConta = request.getParameter("conta");
		
		double valor = Double.parseDouble(inputValor);
		int conta = Integer.parseInt(inputConta);
		
		Conta account = banco.getContaByAccount(conta);
		
		account.deposita(valor);
		request.setAttribute("conta", conta);
		request.setAttribute("valor", valor);
		request.setAttribute("saldoAtual", account.getSaldo());
		
		Date dataAtual = new Date();
		Extrato extrato = new Extrato("Deposito", valor, account.getSaldo(), account, dataAtual);
	    
		banco.adicionaExtrato(extrato);
		
		RequestDispatcher rd = request.getRequestDispatcher("/DepositeMsg.jsp");
		rd.forward(request, response);
		
	}

}
