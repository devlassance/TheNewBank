package model;

import java.util.ArrayList;

public class Conta {
	private int agencia = 1;
	private int conta = 1000;
	private double saldo = 0;
	private Usuario titular;
	
	public Usuario getTitular() {
		return titular;
	}
	public void setTitular(Usuario titular) {
		this.titular = titular;
	}
	
	public int getAgencia() {
		return agencia;
	}
	
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	
	public int getConta() {
		return conta;
	}
	
	public void setConta(int conta) {
		this.conta = conta;
	}
	
	public double getSaldo() {
		return saldo;
	}
	public void deposita(double valor) {
		this.saldo += valor;
	}
	
	public void saca(double valor) {
		this.saldo -= valor;
	}
	
	public void insertContaDb() {
		Banco banco = new Banco();
		ArrayList<String> arrayColuna = new ArrayList<String>();
		ArrayList<String> arrayDados = new ArrayList<String>(); 
		arrayColuna.add("nr_agencia");
		arrayColuna.add("nr_conta");
		arrayColuna.add("saldo");
		
		arrayDados.add("'"+this.agencia+"'");
		arrayDados.add("'"+this.conta+"'");
		arrayDados.add("'"+this.saldo+"'");
		
		int idConta = banco.insert("Contas", arrayColuna, arrayDados);
		
		ArrayList<String> arrayColunaUser = new ArrayList<String>();
		ArrayList<String> arrayDadosUser = new ArrayList<String>();
		
		arrayColunaUser.add("nome");
		arrayColunaUser.add("senha");
		arrayColunaUser.add("id_conta");
		
		arrayDadosUser.add("'"+this.getTitular().getNome()+"'");
		arrayDadosUser.add("'"+this.getTitular().getSenha()+"'");	
		arrayDadosUser.add("'"+idConta+"'");
		
		banco.insert("Usuarios", arrayColunaUser, arrayDadosUser);
		
	}
}
