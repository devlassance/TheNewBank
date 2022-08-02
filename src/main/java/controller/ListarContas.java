package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Banco;
import model.Conta;

@WebServlet(urlPatterns="/ListarContas")
public class ListarContas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Banco banco = new Banco();
		
		
		List<Conta> lista = banco.getContas();
		
		out.println("<html><body>");
		
		out.println("<ul>");
		
		for (Conta conta : lista) {
			out.println("<li><strong>Agencia: </strong>"+conta.getAgencia()+"</li>");
			out.println("<li><strong>Conta: </strong>"+conta.getConta()+"</li>");
			out.println("<li><strong>Nome do titular: </strong>"+conta.getTitular().getNome()+"</li>");
			out.println("<hr>");
		}
		
		out.println("</ul>");

		out.println("</html></body>");
	}


}
