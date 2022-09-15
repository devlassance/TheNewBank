package model;

import java.util.HashMap;

public class Conta {
	private int id;
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
		
		HashMap<String, String> dataConta = new HashMap<String, String>();
		
		dataConta.put("nr_agencia", "'"+this.agencia+"'");
		dataConta.put("nr_conta", "'"+this.conta+"'");
		dataConta.put("saldo", "'"+this.saldo+"'");
		
		int idConta = banco.insert("Contas", dataConta);
		
		HashMap<String, String> dataUser = new HashMap<String, String>();
		
		dataUser.put("nome", "'"+this.getTitular().getNome()+"'");
		dataUser.put("senha", "'"+this.getTitular().getSenha()+"'");
		dataUser.put("id_conta", "'"+idConta+"'");
		
		int idUser = banco.insert("Usuarios", dataUser);
	
		this.setId(idConta);
		this.getTitular().setId(idUser);
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
