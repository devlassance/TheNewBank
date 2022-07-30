package model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/CadastrarConta")
public class CadastrarConta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		 String nome = request.getParameter("nome");
		 String inputAgencia = request.getParameter("agencia");
		 String inputConta = request.getParameter("conta");
		 
		 int agencia = Integer.parseInt(inputAgencia);
		 int conta = Integer.parseInt(inputConta);
		 
		 Usuario user = new Usuario();
		 user.setNome(nome);
		 
		 Conta account = new Conta();
		 
		 account.setTitular(user);
		 account.setAgencia(agencia);
		 account.setConta(conta);
		 
		 Banco banco = new Banco();
		 
		 banco.adicionaConta(account);
		 
		 RequestDispatcher rd = request.getRequestDispatcher("/UsuarioCadastrado.jsp");
         request.setAttribute("conta", conta);
         rd.forward(request, response);
		 
		 
		 
	}

}
