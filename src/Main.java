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
	            
	            switch(indice){
                case 1:
                    System.out.println("\tPreencha os dados:\n");
                    System.out.printf("Nome: ");
                    String nome = ler.nextLine();
                    System.out.printf("E-mail: ");
                    String email = ler.nextLine();
                    System.out.printf("Telefone: ");
                    String telefone = ler.nextLine();
                    System.out.printf("Salario: ");
                    String salario = ler.nextLine();
                    System.out.printf("Enter para confirmar...");
                    ler.nextLine();
                    insertNewFuncionario(nome, email, telefone,salario);
                    System.out.println("Cadastro realizado com sucesso");
                    break;
                case 2:
                	listarFuncionarios();
                	System.out.printf("Enter para voltar ");
                	ler.nextLine();
                    break;
                case 3:
                	System.out.printf("Codigo do funcionario para alterar os dados: ");
                	int newcodigo = ler.nextInt();
                	ler.nextLine();
                	System.out.printf("Nome: ");
                	String newnome = ler.nextLine();
                	System.out.printf("Email: ");
                	String newemail = ler.nextLine();
                	System.out.printf("Telefone: ");
                	String newtelefone = ler.nextLine();
                	System.out.printf("Salario: ");
                	String newsalario = ler.nextLine();
                
                	Funcionario funcionarioAtualizado = new Funcionario();
                    funcionarioAtualizado.setCodigo(newcodigo); 
                    funcionarioAtualizado.setNome(newnome);
                    funcionarioAtualizado.setEmail(newemail);   
                    funcionarioAtualizado.setTelefone(newtelefone);
                    funcionarioAtualizado.setSalario(newsalario);
                    alterarFuncionario(funcionarioAtualizado);
                    break;
                case 4:
                	listarFuncionarios();
                	System.out.printf("Digite o codigo do funcionario para remover: ");
                	int codigo = ler.nextInt();
                	removerFuncionario(codigo);
                	ler.nextLine();
                    break;
                default:
                    if(indice == 0){
                        System.out.println("Programa finalizado com sucesso");
                        ler.nextLine();
                        
                    }else{
                        System.out.println("Opcao invalida tente de novo");
                        ler.nextLine();
                        continue;
                    }
                break;
               }
	            
	            
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
