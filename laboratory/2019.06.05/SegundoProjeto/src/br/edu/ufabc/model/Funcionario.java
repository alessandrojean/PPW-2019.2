package br.edu.ufabc.model;

public class Funcionario {
  private String nome;
  private String cpf;
  private String status;
  private String setor;
  private double salarioMensal;

  public Funcionario(String nome, String cpf, String status, String setor, double salarioMensal) {
    this.nome = nome;
    this.cpf = cpf;
    this.status = status;
    this.setor = setor;
    this.salarioMensal = salarioMensal;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSetor() {
    return setor;
  }

  public void setSetor(String setor) {
    this.setor = setor;
  }

  public double getSalarioMensal() {
    return salarioMensal;
  }

  public void setSalarioMensal(double salarioMensal) {
    this.salarioMensal = salarioMensal;
  }

}
