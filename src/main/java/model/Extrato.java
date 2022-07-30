package model;

import java.util.Date;

public class Extrato {
	private String tipoExtrato;
	private double valor;
	private double saldoMomento;
	private Conta conta;
	private Date dataCadastro;
	
	public Extrato(String tipo, double valor, double saldoMomento, Conta conta, Date dataAtual) {
		this.tipoExtrato = tipo;
		this.valor = valor;
		this.saldoMomento = saldoMomento;
		this.conta = conta;
		this.dataCadastro = dataAtual;
	}
	
	public String getTipoExtrato() {
		return tipoExtrato;
	}
	public void setTipoExtrato(String tipoExtrato) {
		this.tipoExtrato = tipoExtrato;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getSaldoMomento() {
		return saldoMomento;
	}
	public void setSaldoMomento(double saldoMomento) {
		this.saldoMomento = saldoMomento;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
