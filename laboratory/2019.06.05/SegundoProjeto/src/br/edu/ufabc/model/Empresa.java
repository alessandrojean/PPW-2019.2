package br.edu.ufabc.model;

import java.util.ArrayList;

public class Empresa {
  private String nome;
  private String cnpj;
  private ArrayList<Funcionario> funcionarios;

  public Empresa(String nome, String cnpj) {
    this.nome = nome;
    this.cnpj = cnpj;
    this.funcionarios = new ArrayList<>();
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public ArrayList<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void adicionarFuncionario(Funcionario funcionario) {
    funcionarios.add(funcionario);
  }

  public void listarFuncionarios() {
    listarFuncionarios("");
  }

  public void listarFuncionarios(String setor) {
    for (Functionario f : funcionarios) {
      if (f.getSetor().equals("") || f.getSetor().equals(setor)) {
        System.out.printf("Nome: %s\n", f.getNome());
        System.out.printf("CPF: %s\n", f.getCpf());
        System.out.printf("Status: %s\n", f.getStatus());
        System.out.printf("Setor: %s\n", f.getSetor());
        System.out.printf("Salário: %.2f\n\n", f.getSalarioMensal());
      }
    }
  }

  public void listarFuncionariosAtivos() {
    for (Functionario f : funcionarios) {
      if (f.getStatus().equals("ativo")) {
        System.out.printf("Nome: %s\n", f.getNome());
        System.out.printf("CPF: %s\n", f.getCpf());
        System.out.printf("Status: %s\n", f.getStatus());
        System.out.printf("Setor: %s\n", f.getSetor());
        System.out.printf("Salário: %.2f\n\n", f.getSalarioMensal());
      }
    }
  }

  public Funcionario buscarFuncionario(String cpf) {
    for (Funcionario f : funcionarios) {
      if (f.getCpf().equals(cpf))
        return f;
    }

    return null;
  }

}
