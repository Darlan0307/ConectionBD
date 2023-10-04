package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Conection.Conexao;

public class RemoverFuncionarioDAO {
	public boolean removerFuncionario(int codigo) {
        String sql = "DELETE FROM FUNCIONARIOS WHERE ID_Funcionario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, codigo);

            int rowsAffected = ps.executeUpdate();

            // Verifica se algum registro foi removido
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
