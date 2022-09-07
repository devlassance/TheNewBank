package interfaces;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Banco;

public interface Acao {
	public String executa(HttpServletRequest request, HttpServletResponse response, Banco banco) throws ServletException, IOException;  
}
