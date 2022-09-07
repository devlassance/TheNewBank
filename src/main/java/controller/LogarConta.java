package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.Acao;
import model.Banco;
import model.Conta;
import model.Usuario;

public class LogarConta implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response, Banco banco)
			throws ServletException, IOException {
		
		boolean isPost = "POST".equals(request.getMethod());
		if(isPost) {
			
			String inputConta = request.getParameter("conta");
			String inputAgencia = request.getParameter("agencia");
			String password = request.getParameter("senha");
			
			int nrConta = Integer.parseInt(inputConta);
			int nrAgencia = Integer.parseInt(inputAgencia);
			
			int idValido = (int)banco.getDataByType("SELECT C.id as idConta FROM Contas C "
					+ "INNER JOIN Usuarios U "
					+ "ON U.id_conta = C.id AND C.nr_agencia = "+nrAgencia+" AND C.nr_conta = "+nrConta+" AND senha = '"+password+"'", 
					"idConta", "int");         
			
		
			if(idValido > 0) {
				
				Conta contaValida = banco.setContaByValidId(idValido);
				
				HttpSession sessao = request.getSession();
			    sessao.setAttribute("contaLogada", contaValida);
				
				return "redirect:DetalhesConta";
			}
			
		}
		return "redirect:/TheNewBank";
	}

}
	