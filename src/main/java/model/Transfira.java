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

@WebServlet(urlPatterns="/Transfira")
public class Transfira extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Banco banco = new Banco();
		String inputConta = request.getParameter("conta");
		int conta = Integer.parseInt(inputConta);
		
		List<Conta> lista = banco.getContas();
		
		Conta account = banco.getContaByAccount(lista, conta);
		
		request.setAttribute("conta", account.getConta());
		request.setAttribute("saldo", account.getSaldo());
		 
		RequestDispatcher rd = request.getRequestDispatcher("/Transfira.jsp");
		
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Banco banco = new Banco();
		List<Conta> lista = banco.getContas();
		Extrato extrato = new Extrato();
		Extrato extratoBeneficiario = new Extrato();
		
		Date dataAtual = new Date();
		
		String inputBeneficiario = request.getParameter("contaBeneficiaria");
		String inputTitular = request.getParameter("contaTitular");
		String inputValor = request.getParameter("valor");
		
		int nrBeneficiario = Integer.parseInt(inputBeneficiario);
		int nrTitular = Integer.parseInt(inputTitular);
		double valor = Double.parseDouble(inputValor);
		
		
		Conta beneficiario = banco.getContaByAccount(lista, nrBeneficiario);
		Conta titular = banco.getContaByAccount(lista, nrTitular);
		
		titular.saca(valor);
		beneficiario.deposita(valor);
		
		request.setAttribute("conta", nrTitular);
		request.setAttribute("valor", valor);
		request.setAttribute("saldoAtual", titular.getSaldo());
		
		request.setAttribute("nomeBeneficiario", beneficiario.getTitular().getNome());
		request.setAttribute("contaBeneficiario", beneficiario.getConta());
		
		extrato.setTipoExtrato("Transferência");
		extrato.setValor(-valor);
		extrato.setSaldoMomento(titular.getSaldo());
		extrato.setConta(titular);
		extrato.setDataCadastro(dataAtual);
		
		extratoBeneficiario.setTipoExtrato("Transferência");
		extratoBeneficiario.setValor(valor);
		extratoBeneficiario.setSaldoMomento(beneficiario.getSaldo());
		extratoBeneficiario.setConta(beneficiario);
		extratoBeneficiario.setDataCadastro(dataAtual);
		
		banco.adicionaExtrato(extrato);
		banco.adicionaExtrato(extratoBeneficiario);
		
		RequestDispatcher rd = request.getRequestDispatcher("/TransfiraMsg.jsp");
		rd.forward(request, response);
	}

}
