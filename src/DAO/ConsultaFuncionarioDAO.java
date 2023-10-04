package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conection.Conexao;
import Entity.Funcionario;


public class ConsultaFuncionarioDAO {

    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM FUNCIONARIOS";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            	Funcionario funcionario = new Funcionario();
                funcionario.setCodigo(rs.getInt("ID_Funcionario"));
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setEmail(rs.getString("Email"));
                funcionario.setTelefone(rs.getString("Telefone"));
                funcionario.setSalario(rs.getString("Salario"));
                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionarios;
    }
}
