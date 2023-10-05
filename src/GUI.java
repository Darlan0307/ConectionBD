import javax.swing.*;

import DAO.AdicionarFuncionarioDAO;
import DAO.AlterarFuncionarioDAO;
import DAO.ConsultaFuncionarioDAO;
import DAO.RemoverFuncionarioDAO;
import Entity.Funcionario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI {
    public static void main(String[] args) {
//    	Criando a janela
        JFrame frame = new JFrame("Sistema de Gerenciamento de Funcionarios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Tamanho da janela
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
//       layout com 5 linhas e 1 coluna
        panel.setLayout(new GridLayout(5, 1));

//        Criando os botões do layout
        JButton cadastrarButton = new JButton("Cadastrar Funcionario");
        JButton listarButton = new JButton("Listar Funcionario");
        JButton alterarButton = new JButton("Alterar Funcionario");
        JButton removerButton = new JButton("Remover Funcionario");
        JButton sairButton = new JButton("Sair");

//        Adicionando os botoes no layout
        panel.add(cadastrarButton);
        panel.add(listarButton);
        panel.add(alterarButton);
        panel.add(removerButton);
        panel.add(sairButton);

        frame.add(panel);

        frame.setVisible(true);

//        Ações dos botões
        
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para a opção de cadastrar funcionario
                String nome = JOptionPane.showInputDialog(frame, "Digite o Nome:");
                String email = JOptionPane.showInputDialog(frame, "Digite o E-mail:");
                String telefone = JOptionPane.showInputDialog(frame, "Digite o telefone:");
                String salario = JOptionPane.showInputDialog(frame, "Digite o salario:");

                
                insertNewFuncionario(nome, email, telefone, salario);

                JOptionPane.showMessageDialog(frame, "Cadastro realizado com sucesso");
            }
        });

        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para a opção de listar funcionarios
                listarFuncionarios();
            }
        });

        alterarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para a opção de alterar funcionario
                int codigo = Integer.parseInt(JOptionPane.showInputDialog(frame, "Digite o Código do Funcionario para Alterar:"));
                String nome = JOptionPane.showInputDialog(frame, "Digite o Novo Nome:");
                String email = JOptionPane.showInputDialog(frame, "Digite o Novo E-mail:");
                String telefone = JOptionPane.showInputDialog(frame, "Digite o Novo Telefone:");
                String salario = JOptionPane.showInputDialog(frame, "Digite o Novo Salario:");

                Funcionario funcionarioAtualizado = new Funcionario();
                funcionarioAtualizado.setCodigo(codigo);
                funcionarioAtualizado.setNome(nome);
                funcionarioAtualizado.setEmail(email);           
                funcionarioAtualizado.setTelefone(telefone);
                funcionarioAtualizado.setSalario(salario);

                
                alterarFuncionario(funcionarioAtualizado);

                JOptionPane.showMessageDialog(frame, "Funcionario alterado com sucesso!");
            }
        });

        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para a opção de remover funcionario
                int codigo = Integer.parseInt(JOptionPane.showInputDialog(frame, "Digite o Código do Funcionario para Remover:"));
                
                removerFuncionario(codigo);

                JOptionPane.showMessageDialog(frame, "Funcionario removido com sucesso!");
            }
        });

        sairButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para sair do programa
                JOptionPane.showMessageDialog(frame, "Programa finalizado com sucesso.");
                System.exit(0);
            }
        });
    }

    
    // Funções CRUD
    
    public static void insertNewFuncionario(String nome,String email,String telefone,String salario){
        Funcionario u = new Funcionario();

        u.setNome(nome);
        u.setEmail(email);
        u.setTelefone(telefone);
        u.setSalario(salario);

        new AdicionarFuncionarioDAO().cadastrarFuncionario(u);
   }
    
    public static void listarFuncionarios() {
    	ConsultaFuncionarioDAO consultaDAO = new ConsultaFuncionarioDAO();

        // Chamar o método para listar usuários
        List<Funcionario> funcionarios = consultaDAO.listarFuncionarios();

        // Iterar e imprimir os resultados
        for (Funcionario funcionario : funcionarios) {
            System.out.println("\nCodigo: " + funcionario.getCodigo());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("E-mail: " + funcionario.getEmail());
            System.out.println("Telefone: " + funcionario.getTelefone());
            System.out.println("Salario: " + funcionario.getSalario());
            System.out.println("----------------------");
        }
    }
    
    public static void alterarFuncionario(Funcionario funcionarioAtualizado) {
    	AlterarFuncionarioDAO alteracaoDAO = new AlterarFuncionarioDAO();

        if (alteracaoDAO.alterarFuncionario(funcionarioAtualizado)) {
            System.out.println("Funcionario alterado com sucesso!");
        } else {
            System.out.println("Falha ao alterar o funcionario.");
        }
    }
    
    public static void removerFuncionario(int codigo) {
    	RemoverFuncionarioDAO remocaoDAO = new RemoverFuncionarioDAO();

        if (remocaoDAO.removerFuncionario(codigo)) {
            System.out.println("Funcionario removido com sucesso!");
        } else {
            System.out.println("Falha ao remover o funcionario.");
        }
    }
}