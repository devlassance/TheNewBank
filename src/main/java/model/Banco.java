package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import connectionDb.ConnectionDb;

public class Banco {
	private static List<Extrato> listaExtrato = new ArrayList<>();
		
	public int insert(String table, HashMap<String, String> dataDb) {
		int id = 0;
		Statement stm = null;
		Connection con = null;
		
		ConnectionDb ConnectionDb = new ConnectionDb();
		try {
			con = ConnectionDb.connection();
			stm =  con.createStatement();
			
			ArrayList<String> collumArray = new ArrayList<String>();
			ArrayList<String> dataArray = new ArrayList<String>();
			
			//Quebrando hashmap, separando key de value e passando para o list
			for (String i : dataDb.keySet()) {
				collumArray.add(i);
				dataArray.add(dataDb.get(i));
			}
			
			//quebrando o arrayList e passando para os formatos de sql
			String collum = String.join(",", collumArray);
			String data = String.join(",", dataArray);
			
			stm.execute("INSERT INTO "+table+" ("+collum+") VALUES ("+data+")", Statement.RETURN_GENERATED_KEYS);
			ResultSet rst = stm.getGeneratedKeys();
			
			while(rst.next()) {
				id = rst.getInt(1);
						
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	
	public Object getDataByType(String select, String collum, String type) {
		Statement stm = null;
		Connection con = null;
		
		ConnectionDb ConnectionDb = new ConnectionDb();
		
		try {
			con = ConnectionDb.connection();
			stm =  con.createStatement();
			

			stm.execute(select);
			
			ResultSet rst = stm.getResultSet();
			Object value = 1;
			while(rst.next()) {
				
				switch (type) {
				case "int": 
					value = rst.getInt(collum);
					break;
					
				case "String":
					value = rst.getString(collum);
					break;
					
				case "double":
					value = rst.getDouble(collum);
					break;
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + type);
				}
			}
			
			con.close();
			
			return value;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public Conta setContaByValidId(int idValido) {
		
		try {
			
			Usuario user = null;
			Conta conta = null;
			
			ConnectionDb ConnectionDb = new ConnectionDb();
			Connection con = ConnectionDb.connection();
			Statement stm =  con.createStatement();
			
			stm.execute("SELECT * FROM Contas C INNER JOIN Usuarios U ON U.id_conta = C.id AND C.id = "+idValido);
			
			ResultSet rst = stm.getResultSet();
			while(rst.next()) {
				user = new Usuario();
				user.setNome(rst.getString("nome"));
				user.setSenha(rst.getString("senha"));
				
				conta = new Conta();
				conta.setAgencia(rst.getInt("nr_agencia"));
				conta.setConta(rst.getInt("nr_conta"));
				conta.deposita(rst.getDouble("saldo"));
				conta.setTitular(user);
			}
			
			con.close();
			
			
			return conta;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return null;
		
		
	}
	
	public void adicionaExtrato(Extrato extrato) {
		listaExtrato.add(extrato);
	}
	public List<Extrato> getExtratos(){
		return this.listaExtrato;
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







