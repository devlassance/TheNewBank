package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.Acao;
import model.Banco;
import model.Conta;
import model.Extrato;

public class Transfira implements Acao {
       	
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response, Banco banco)
			throws ServletException, IOException {
		
		boolean isPost = "POST".equals(request.getMethod());
		
		HttpSession sessao = request.getSession();
		
		if(sessao.getAttribute("contaLogada") != null) {
			Conta titular = (Conta)sessao.getAttribute("contaLogada");
			
			if(isPost) {
				String contaBeneficiario = request.getParameter("contaBeneficiario");
				String agenciaBeneficiario = request.getParameter("agenciaBeneficiario");
				String inputValor = request.getParameter("valor");
				
				int nrContaBenefeciario = Integer.parseInt(contaBeneficiario);
				int nrAgenciaBeneficiario = Integer.parseInt(agenciaBeneficiario);
				
				double valor = Double.parseDouble(inputValor);
				double valorNegativado = valor * -1;
				
				banco.setDataByParam("UPDATE Contas SET saldo = saldo - "+valor+" WHERE id = "+titular.getId());
				banco.setDataByParam("UPDATE Contas SET saldo = saldo + "+valor+" WHERE "
						+ "nr_agencia = "+nrAgenciaBeneficiario+" AND nr_conta = "+nrContaBenefeciario);
				
				int idBeneficiario = (int)banco.getDataByType("SELECT id FROM Contas "
						+ "WHERE nr_agencia = "+nrAgenciaBeneficiario+" and nr_conta = "+nrContaBenefeciario,
						"id", "int");
				
				titular.saca(valor);
				Conta beneficiario = banco.setContaByValidId(idBeneficiario);
				
				banco.setExtrato(3, valorNegativado, titular.getSaldo(), titular.getId());
				banco.setExtrato(3, valor, beneficiario.getSaldo(), beneficiario.getId());
							
				request.setAttribute("conta", titular.getConta());
				request.setAttribute("valor", valor);
				request.setAttribute("saldoAtual", titular.getSaldo());
				
				request.setAttribute("contaBeneficiario", nrContaBenefeciario);
				request.setAttribute("agenciaBeneficiario", nrAgenciaBeneficiario);
				
				return "forward:TransfiraMsg.jsp";
			}
			
			request.setAttribute("conta", titular.getConta());
			request.setAttribute("saldo", titular.getSaldo());
			
			return "forward:Transfira.jsp";
		}
		
		return "redirect:/TheNewBank";
		
	}
	

}
