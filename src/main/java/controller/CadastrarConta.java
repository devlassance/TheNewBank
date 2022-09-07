package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.Acao;
import model.Banco;
import model.Conta;
import model.Usuario;

public class CadastrarConta implements Acao {
	public String executa(HttpServletRequest request, HttpServletResponse response, Banco banco) throws ServletException, IOException{
		
		boolean isPost = "POST".equals(request.getMethod());
		
		if(isPost) {
			 String nome = request.getParameter("nome");
			 String senha = request.getParameter("senha");
			 
			 
			 Usuario user = new Usuario();
			 user.setNome(nome);
			 user.setSenha(senha);
			 
			 Conta account = new Conta();
			 
	
			 int conta = 1001;
			 //nome da tabela, coluna de busca, parametro de busca e tipagem
			 int lastConta = (int)banco.getDataByType("SELECT * FROM Contas ORDER BY id DESC LIMIT 1", "nr_conta", "int");
			 if(lastConta != 0 ) {
				 conta = lastConta + 1;
			 }
			 
			 account.setTitular(user);
			 account.setConta(conta);
			 
			 account.insertContaDb();
			 
			 
			 HttpSession sessao = request.getSession();
		     sessao.setAttribute("contaLogada", account);
			 
			 return "redirect:DetalhesConta";
		}
		
		return "forward:CadastrarConta.jsp";
		
		 
	}

}
