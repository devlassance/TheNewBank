package http;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interfaces.Acao;
import model.Banco;

@WebServlet(urlPatterns="/Conta/*")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Banco banco = new Banco();
		String pathInfo = request.getRequestURI(); 
		String url = null;
		
		//pegando a ultima / para recorrer ao nome da classe
    	String[] className = pathInfo.split("/");
    	
    	try {
    		String classNamePath = "controller."+className[3];
    		
		    Class<?> classe = Class.forName(classNamePath);
		    Acao acao = (Acao) classe.getDeclaredConstructor().newInstance();
		    url = acao.executa(request, response, banco);  
		} catch (Exception e) {
		    throw new ServletException(e);
		}
    	
	    String[] entrada = url.split(":");
	    
	    if(entrada[0].equals("forward")) {
	    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/" + entrada[1]);
	        rd.forward(request, response);
	    } else {
	        response.sendRedirect(entrada[1]);

	    }
	    

	}

}
