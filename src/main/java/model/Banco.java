package model;

import java.util.ArrayList;
import java.util.List;

public class Banco {
	private static List<Conta> listaConta = new ArrayList<>();
	private static List<Extrato> listaExtrato = new ArrayList<>();
	
	public void adicionaConta(Conta conta) {
		listaConta.add(conta);
	}
	public List<Conta> getContas(){
		return this.listaConta;
	}
	
	public void adicionaExtrato(Extrato extrato) {
		listaExtrato.add(extrato);
	}
	public List<Extrato> getExtratos(){
		return this.listaExtrato;
	}
	
	public Conta getContaByAccount(int nrConta) {
		for (Conta conta : listaConta) {
			if(conta.getConta() == nrConta) {
				return conta;
			}
		}
		return null;
	}
	
	public List<Extrato> getExtratoByAccount(Conta conta){
		
		List<Extrato> allExtratos = new ArrayList<>();
		
		for(Extrato extrato : listaExtrato) {
			if(extrato.getConta() == conta) {
				allExtratos.add(extrato);
			}
		}
		
		return allExtratos;
	}
}







