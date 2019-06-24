package br.com.bb.sistemabancario.testes;

import java.util.List;

import br.com.bb.sistemabancario.dao.ContaCorrenteDAO;
import br.com.bb.sistemabancario.model.ContaCorrente;

public class CriaContaCorrente {

  public static void main(String[] args) {
    ContaCorrente cc1 = new ContaCorrente();
    cc1.setNumero("1234-0");
    cc1.setAgencia("9876");
    cc1.setDescricao("Conta corrente do Banco do Brasil");
    cc1.setAtiva(true);
    cc1.setVariacao(0);
    
    // Gravando registro.
    ContaCorrenteDAO dao = new ContaCorrenteDAO();
    dao.insere(cc1);
    System.out.println("Gravado!");
  }

}
