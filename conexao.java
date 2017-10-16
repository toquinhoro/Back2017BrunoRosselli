package classes_de_conexão;

import java.sql.*;
import com.mysql.jdbc.Connection;


//CLASSE DE CONEXÃO COM O BANCO DE DEDADOS

public class conexao {


	
	public static Connection faz_conxao () throws SQLException {
		
		try {
			
			//INICIA O BANCO E FAZ AUTENTICAÇÃO
			
			Class.forName("com.mysql.jdbc.Driver");
			return  (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/back2017", "root","Loverina#1995");
			
			
		} catch (ClassNotFoundException e) {
			
			throw new SQLException(e.getException());
			
			
		}
		
		
	}
	
	
}
