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


@WebServlet(urlPatterns="/DetalhesConta")
public class DetalhesConta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Banco banco = new Banco();
		String inputConta = request.getParameter("conta");
		int conta = Integer.parseInt(inputConta);
						
		RequestDispatcher rd = request.getRequestDispatcher("/DetalhesConta.jsp");
        
		Conta account = banco.getContaByAccount(conta);
		
		request.setAttribute("nome", account.getTitular().getNome());
		request.setAttribute("agencia", "000"+account.getAgencia());
		request.setAttribute("conta", account.getConta());
		request.setAttribute("saldo", account.getSaldo());
		
		rd.forward(request, response);		
	
	}


}
