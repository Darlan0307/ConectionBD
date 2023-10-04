package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Entity.Funcionario;
import Conection.Conexao;

public class AdicionarFuncionarioDAO {
	public void cadastrarFuncionario(Funcionario funcionario) {
		//Inserindo os dados com os comandos da jdbc
		String sql = "INSERT INTO FUNCIONARIOS (NOME,EMAIL,TELEFONE,SALARIO) VALUES (?,?,?,?)";
		
		
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConnection().prepareStatement(sql);
			//Pegando os dados da variável
			ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getEmail());
            ps.setString(3, funcionario.getTelefone());
            ps.setString(4,funcionario.getSalario());
            
            ps.execute();
            ps.close();
		}catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
}
