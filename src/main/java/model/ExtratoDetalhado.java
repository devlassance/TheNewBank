package model;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/ExtratoDetalhado")
public class ExtratoDetalhado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Banco banco = new Banco();
		
		String inputConta = request.getParameter("conta");
		int conta = Integer.parseInt(inputConta);
		
		List<Conta> lista = banco.getContas();
		List<Extrato> listaExtratos = banco.getExtratos();
		
		Conta account = banco.getContaByAccount(lista, conta);
		List<Extrato> extratos = banco.getExtratoByAccount(listaExtratos, account);
		
		request.setAttribute("conta", account.getConta());
		request.setAttribute("extratos", extratos);
	
		
		RequestDispatcher rd = request.getRequestDispatcher("/ExtratoDetalhado.jsp");
		rd.forward(request, response);
	}

}
