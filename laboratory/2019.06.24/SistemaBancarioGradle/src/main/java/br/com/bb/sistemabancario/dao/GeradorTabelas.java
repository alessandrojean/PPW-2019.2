package br.com.bb.sistemabancario.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeradorTabelas {

  public static void main(String[] args) {
    EntityManagerFactory factory = Persistence
        .createEntityManagerFactory("sistemabancario");
    System.out.println("Tabela gerada!");
    factory.close();
  }

}
