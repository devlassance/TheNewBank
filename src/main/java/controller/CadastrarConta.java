package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
			 
			 List<Conta> listConta = banco.getContas();
			 
			 int conta = account.getConta();
			 conta += 1;
			 
			 if(listConta.size() > 0) {
				Conta lastConta = listConta.get(listConta.size() - 1);
				conta = lastConta.getConta();
				conta += 1;
			 }
			 
			 account.setTitular(user);
			 account.setConta(conta);
			 
			 banco.adicionaConta(account);
			 
			 HttpSession sessao = request.getSession();
		     sessao.setAttribute("contaLogada", account);
			 
			 return "redirect:DetalhesConta";
		}
		
		return "forward:CadastrarConta.jsp";
		
		 
	}

}
