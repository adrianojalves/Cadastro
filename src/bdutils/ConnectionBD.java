package bdutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionBD {

	private static Connection conexao;
	private static String erro;
	
	private static Connection getConexao() {
		if(conexao == null)
			createConnection();
		
		return conexao;
	}
	
	public PreparedStatement getPrepareStatement(String sql) throws SQLException {
		System.out.println(sql);
		return getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	}
	
	public static String getErro() {
		return erro;
	}
	
	public static void createConnection() {
		String driverName = "com.mysql.cj.jdbc.Driver"; //classe do driver de conexão
		
        String stringConexao = "jdbc:mysql://localhost/Cadastro?" +
                				"user=root&password=pamonha&useSSL=false";

        try {
			Class.forName(driverName).newInstance(); //Carregando driver
			
			conexao = DriverManager.getConnection(stringConexao);
			
			if (conexao == null) 
				erro = "STATUS - Não foi possivel realizar conexão";
			
		} catch (ClassNotFoundException e) {
			erro = "Classe do driver não encontrada";
			
			e.printStackTrace();
		} catch (SQLException e) {
			erro = "Não foi possível conectar no banco de dados";
			
			e.printStackTrace();
		} catch (InstantiationException e) {
			erro = "Não foi possível carregar uma instância do driver";
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
	}
	
	public static boolean close() {
        try {
            conexao.close();
            conexao = null;
            System.out.println("Fechada conexão com o banco");
            return true;

        } catch (SQLException e) {
        	e.printStackTrace();
            return false;
        }
    }
}