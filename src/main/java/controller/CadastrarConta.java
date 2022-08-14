package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interfaces.Acao;
import model.Banco;
import model.Conta;
import model.Usuario;

public class CadastrarConta implements Acao {
	public String executa(HttpServletRequest request, HttpServletResponse response, Banco banco) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		
		 String nome = request.getParameter("nome");
		 String inputAgencia = request.getParameter("agencia");
		 
		 int agencia = Integer.parseInt(inputAgencia);
		 
		 Usuario user = new Usuario();
		 user.setNome(nome);
		 
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
		 account.setAgencia(agencia);
		 account.setConta(conta);
		 
		 
		 banco.adicionaConta(account);
		 
		 return "redirect:DetalhesConta/?conta="+conta;
	}

}
