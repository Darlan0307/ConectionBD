package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Conection.Conexao;
import Entity.Funcionario;

public class AlterarFuncionarioDAO {
	public boolean alterarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE FUNCIONARIOS SET NOME = ?, EMAIL = ?, TELEFONE = ?, SALARIO = ? WHERE ID_Funcionario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getEmail());
            ps.setString(3, funcionario.getTelefone());
            ps.setString(4, funcionario.getSalario());
            ps.setInt(5, funcionario.getCodigo());

            int rowsAffected = ps.executeUpdate();

            // Verifica se algum registro foi atualizado
            if (rowsAffected > 0) {
                
                return true;
            } else {
                
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
