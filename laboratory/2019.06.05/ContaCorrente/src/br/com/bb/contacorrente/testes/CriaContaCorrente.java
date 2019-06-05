package br.com.bb.contacorrente.testes;

import br.com.bb.contacorrente.modelo.ContaCorrente;

public class CriaContaCorrente {

  public static void main(String[] args) {
    ContaCorrente cc1 = new ContaCorrente();
    cc1.setNumero("1234-0");
    cc1.setAgencia("9876");
    cc1.setDescricao("Conta corrente do Banco do Brasil");
    cc1.setAtiva(true);
    cc1.setVariacao(0);
    cc1.setId(3L);

    ContaCorrente cc2 = new ContaCorrente();
    cc2.setNumero("5678-0");
    cc2.setAgencia("9877");
    cc2.setDescricao("Conta corrente do Banco do Brasil");
    cc2.setAtiva(true);
    cc2.setVariacao(0);
    cc2.setId(4L);

    ContaCorrente[] contas = { cc1, cc2 };

    for (ContaCorrente cc : contas) {
      System.out.println(cc.getNumero());
      System.out.println(cc.getAgencia());
      System.out.println(cc.getDescricao());
      System.out.println(cc.isAtiva());
      System.out.println(cc.getVariacao());
      System.out.println(cc.getId());
      System.out.println();
    }
  }

}
