package br.com.bb.sistemabancario.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
  public Connection getConnection() {
    try {
      String url = "jdbc:postgresql://localhost/SistemaBancario";
      return DriverManager.getConnection(url, "postgres", "ufabc");
      
      // Para o H2: descomente a parte abaixo e comente as duas linhas acima.
      // String url = "jdbc:h2:tcp://localhost/~/progweb";
      // return DriverManager.getConnection(url, "admin", "admin");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
