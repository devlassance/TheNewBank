package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
		 
		 Usuario user = new Usuario();
		 user.setNome(nome);
		 
		 Conta account = new Conta();
		 Banco banco = new Banco();
		 
		 List<Conta> listConta = banco.getContas();
		 
		 int conta = account.getConta();
		 conta += 1;
		 
		 if(listConta.size() > 0) {
			Conta lastConta = listConta.get(listConta.size() - 1);
			conta = lastConta.getConta();
			conta += 1;
		 }
		 
		 account.setTitular(user);
		 account.setAgencia(agencia);
		 account.setConta(conta);
		 
		 
		 banco.adicionaConta(account);
		 
		 RequestDispatcher rd = request.getRequestDispatcher("/UsuarioCadastrado.jsp");
         request.setAttribute("conta", conta);
         rd.forward(request, response);
		 
		 
		 
	}

}
