package br.com.bb.sistemabancario.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.bb.sistemabancario.model.ContaCorrente;

public class ContaCorrenteDAO {
  
  public void salva(ContaCorrente cc) {
    EntityManagerFactory factory = Persistence
        .createEntityManagerFactory("sistemabancario");
    EntityManager manager = factory.createEntityManager();
    
    try {
      manager.getTransaction().begin();
      
      if (cc.getId() == null)
        manager.persist(cc);
      else
        manager.merge(cc);
      
      manager.getTransaction().commit();
    } finally {
      if (manager.getTransaction().isActive())
        manager.getTransaction().rollback();
    }
    manager.close();
  }
  
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
  
  public void remove(ContaCorrente cc) {
    EntityManagerFactory factory = Persistence
        .createEntityManagerFactory("sistemabancario");
    EntityManager manager = factory.createEntityManager();
    
    cc = manager.find(ContaCorrente.class, cc.getId());
    try {
      manager.getTransaction().begin();
      manager.remove(cc);
      manager.getTransaction().commit();
    } finally {
      if (manager.getTransaction().isActive())
        manager.getTransaction().rollback();
    }
  }
  
  public void altera(ContaCorrente cc) {
    EntityManagerFactory factory = Persistence
        .createEntityManagerFactory("sistemabancario");
    EntityManager manager = factory.createEntityManager();
    
    try {
      manager.getTransaction().begin();
      manager.merge(cc);
      manager.getTransaction().commit();
    } finally {
      if (manager.getTransaction().isActive())
        manager.getTransaction().rollback();
    }
  }
  
  public List<ContaCorrente> getLista() {
    EntityManagerFactory factory = Persistence
        .createEntityManagerFactory("sistemabancario");
    EntityManager manager = factory.createEntityManager();
    
    List<ContaCorrente> ccs = manager
        .createQuery("select cc from ContaCorrente cc", ContaCorrente.class)
        .getResultList();
    
    manager.close();
    return ccs;
  }
  
  public ContaCorrente buscaPelaAgenciaENumero(String agencia, String numero) {
    EntityManagerFactory factory = Persistence
        .createEntityManagerFactory("sistemabancario");
    EntityManager manager = factory.createEntityManager();
    
    ContaCorrente cc = manager
        .createQuery("from ContaCorrente cc where cc.numero = :numero and cc.agencia = :agencia", ContaCorrente.class)
        .setParameter("numero", numero)
        .setParameter("agencia", agencia)
        .getResultList()
        .get(0);
    
    manager.close();
    return cc;
  }
  
  public List<ContaCorrente> buscaPelaDescricao(String descricao) {
    EntityManagerFactory factory = Persistence
        .createEntityManagerFactory("sistemabancario");
    EntityManager manager = factory.createEntityManager();
    
    List<ContaCorrente> ccs = manager
        .createQuery("from ContaCorrente cc where lower(cc.descricao) == lower(:descricao)", ContaCorrente.class)
        .setParameter("descricao", descricao)
        .getResultList();
    
    manager.close();
    return ccs;
  }

}
