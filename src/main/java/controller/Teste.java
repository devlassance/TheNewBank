package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectionDb.ConnectionDb;

public class Teste {

	public static void main(String[] args) throws SQLException {
		
		
		ArrayList<String> arrayColunas = new ArrayList<String>();
		ArrayList<String> arrayDados = new ArrayList<String>();
		arrayColunas.add("nome");
		arrayColunas.add("cidade");
		
		arrayDados.add("'Dep4'");
		arrayDados.add("'São pedro da Aldeia'");

		
		String colunas = String.join(",", arrayColunas);
		String dados = String.join(",", arrayDados);
		
		System.out.println("INSERT INTO departamento ("+colunas+") VALUES ("+dados+")");
		
		ConnectionDb ConnectionDb = new ConnectionDb();
		Connection con = ConnectionDb.connection();
		Statement stm =  con.createStatement();
		stm.execute("INSERT INTO departamento ("+colunas+") VALUES ("+dados+")", Statement.RETURN_GENERATED_KEYS);
		
		//ResultSet rst = stm.getResultSet();
		ResultSet rst = stm.getGeneratedKeys();
		
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O id criado foi: "+id);
					
		}
		
		
		con.close();
		
	
	}

}
