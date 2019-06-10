package br.com.bb.contacorrente.testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {

  public static void main(String[] args) {
    Connection conexao = null;
    try {
      String url = "jdbc:postgresql://localhost/SistemaBancario";
      conexao = DriverManager.getConnection(url, "postgres", "ufabc");
      // Para o H2 descomente as linhas abaixo e comente as linhas acima.
      // String url = "jdbc:h2:tcp://localhost/~/progweb";
      // conexao = DriverManager.getConnection(url, "admin", "admin");
      System.out.println("Conectou!");
    } catch (SQLException e1) {
      System.out.println("Erro ao abrir a conexão: " + e1.getMessage());
    } finally {
      try {
        conexao.close();
      } catch (SQLException e2) {
        System.out.println("Erro ao fechar a conexão: " + e2.getMessage());
      }
    }
  }

}
