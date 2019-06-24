package br.com.bb.sistemabancario.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.bb.sistemabancario.model.ContaCorrente;

public class ContaCorrenteDAO {
  
  public void insere(ContaCorrente cc) {
    EntityManagerFactory factory = Persistence
        .createEntityManagerFactory("sistemabancario");
    EntityManager manager = factory.createEntityManager();
    
    try {
      manager.getTransaction().begin();
      manager.persist(cc);
      manager.getTransaction().commit();
    } finally {
      if (manager.getTransaction().isActive())
        manager.getTransaction().rollback();
    }
    manager.close();
  }
  
}
