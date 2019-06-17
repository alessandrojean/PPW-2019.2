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
    listaContas(dao);
    
    // Obtendo registro para alterar.
    ContaCorrente ccBd = dao.buscaPelaAgenciaENumero("9876", "1234-0");
    ccBd.setAtiva(false);
    dao.altera(ccBd);
    System.out.println("Alterado!");
    listaContas(dao);
    
    // Removendo registro.
    dao.remove(ccBd);
    System.out.println("Removido!");
    listaContas(dao);
  }
  
  private static void listaContas(ContaCorrenteDAO dao) {
    List<ContaCorrente> resultado = dao.getLista();
    if (resultado.size() == 0) {
      System.out.println("Sem resultados!");
      return;
    }
    
    resultado.stream().forEach(System.out::println);
  }

}
