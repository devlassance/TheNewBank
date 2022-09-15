package http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Banco;
import model.Conta;

/**
 * Servlet implementation class Teste
 */
@WebServlet("/Teste")
public class Teste extends HttpServlet {
	private static final long serialVersionUID = 1L;
 	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		
 		Banco banco = new Banco();
 		
 		//List<Conta> lista = banco.getContas();
 		ArrayList<String> lista = null;
 		
 		Gson gson = new Gson();
        String json = gson.toJson(lista); 

        response.setContentType("application/json");
        response.getWriter().print(json);
	}

}
