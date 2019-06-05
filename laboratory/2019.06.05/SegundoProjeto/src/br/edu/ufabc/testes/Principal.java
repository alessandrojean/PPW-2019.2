package br.edu.ufabc.testes;

import java.util.Scanner;

import br.edu.ufabc.model.Empresa;
import br.edu.ufabc.model.Funcionario;

public class Principal {
  private static final int OPCAO_MOSTRAR_INFORMACOES = 1;
  private static final int OPCAO_LISTAR_FUNCIONARIOS = 2;
  private static final int OPCAO_SAIR = 3;

  public static void main(String[] args) {
    Empresa emp = criarEmpresa();
    
    int opcao = realizarPrompt();
    System.out.println();
    
    tratarOpcao(emp, opcao);
  }
  
  private static Empresa criarEmpresa() {
    Empresa emp = new Empresa("UFABC", "08.992.904/0001-52");

    emp.adicionarFuncionario(
      new Funcionario("Fulano", "066.278.770-66", "Programador", "TI", 7000));
    emp.adicionarFuncionario(
      new Funcionario("Beltrano", "564.096.560-68", "Analista", "TI", 2000));
    emp.adicionarFuncionario(
      new Funcionario("Ciclano", "281.447.250-00", "Analista Junior", "TI", 1500));
    emp.adicionarFuncionario(
      new Funcionario("Roberto", "789.504.110-08", "Programador Junior", "TI", 3000));
    emp.adicionarFuncionario(
      new Funcionario("Carlos", "276.910.440-38", "Programador Junior", "TI", 3000));
    
    return emp;
  }
  
  private static int realizarPrompt() {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Opções:");
    System.out.println("  1) Mostrar informações da empresa.");
    System.out.println("  2) Listar funcionários da empresa.");
    System.out.println("  3) Sair.");
    System.out.print("Escolha: ");
    
    return sc.nextInt();
  }
  
  private static void tratarOpcao(Empresa emp, int opcao) {
    switch (opcao) {
      case OPCAO_MOSTRAR_INFORMACOES:
        System.out.printf("Nome: %s\n", emp.getNome());
        System.out.printf("CNPJ: %s\n", emp.getCnpj());
        break;
      case OPCAO_LISTAR_FUNCIONARIOS:
        emp.listarFuncionarios();
        break;
      case OPCAO_SAIR:
        return;
      default:
        System.err.println("Opção inválida, tente novamente.");
    }
  }

}
