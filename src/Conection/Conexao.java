package Conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
//	Informações necessárias para a conexão
    private static final String url = "jdbc:mysql://localhost:3306/bancodados"; //Só trocar o nome do BD
    private static final String user = "root"; 
    private static final String password = "root"; 

    private static Connection conn;

    public static Connection getConnection(){
        
        try {
            //Verificando se a conexão existe
            if(conn == null || conn.isClosed()){
                    //Criando a conexao
                    conn = DriverManager.getConnection(url, user, password);   
                }
                return conn;
        } 
        //Tratando uma possivel exception
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    
    }
}
