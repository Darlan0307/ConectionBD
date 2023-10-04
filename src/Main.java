import DAO.AdicionarFuncionarioDAO;
import DAO.AlterarFuncionarioDAO;
import DAO.ConsultaFuncionarioDAO;
import DAO.RemoverFuncionarioDAO;
import Entity.Funcionario;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner ler = new Scanner(System.in);
		int indice = 0;
		
		try {
			do {
				menu();
				indice = ler.nextInt();
	            ler.nextLine();
	            
			}while(indice != 0);
			
			
			
		}catch(InputMismatchException e) {
			System.out.println("Erro: Entrada invalida. Certifique-se de digitar um numero.");
		}finally {
			ler.close();
		}

	}
	public static void menu(){
        System.out.println("\tMENU");
        System.out.println("1 - Cadastrar funcionario");
        System.out.println("2 - Listar todos funcionarios");
        System.out.println("3 - Alterar dados");
        System.out.println("4 - Remover funcionario");
        System.out.println("0 - Finalizar sistema");
        System.out.printf("Digite o indice da opcao: ");
    }

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
            System.out.println("Codigo: " + funcionario.getCodigo());
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
            System.out.println("Usuário alterado com sucesso!");
        } else {
            System.out.println("Falha ao alterar o usuário.");
        }
    }
	
	public static void removerFuncionario(int codigo) {
    	RemoverFuncionarioDAO remocaoDAO = new RemoverFuncionarioDAO();

        if (remocaoDAO.removerFuncionario(codigo)) {
            System.out.println("Usuário removido com sucesso!");
        } else {
            System.out.println("Falha ao remover o usuário.");
        }
    }
}
