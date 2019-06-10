package br.com.bb.sistemabancario.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bb.contacorrente.modelo.ContaCorrente;
import br.com.bb.sistemabancario.jdbc.ConnectionFactory;

public class ContaCorrenteDAO {

  private Connection connection;

  private static final String SQL_INSERT = "insert into \"ContaCorrente\" "
      + "(numero, agencia, descricao, ativa, variacao) "
      + "values (?, ?, ?, ?, ?);";
  private static final String SQL_DELETE = "delete from \"ContaCorrente\" "
      + "where id = ?;";
  private static final String SQL_UPDATE = "update \"ContaCorrente\" set "
      + "numero = ?, agencia = ?, descricao = ?, ativa = ?, variacao = ? "
      + "where id = ?;";
  private static final String SQL_SELECT = "select * from \"ContaCorrente\" "
      + "order by numero;";
  private static final String SQL_SEARCH = "select * from \"ContaCorrente\" "
      + "where numero = ?;";

  public ContaCorrenteDAO() {
    this.connection = new ConnectionFactory().getConnection();
  }

  public void insere(ContaCorrente cc) {
    try {
      // Prepared Statement para inserção.
      PreparedStatement stmt = connection.prepareStatement(SQL_INSERT);
      // Define valores.
      stmt.setString(1, cc.getNumero());
      stmt.setString(2, cc.getAgencia());
      stmt.setString(3, cc.getDescricao());
      stmt.setBoolean(4, true);
      stmt.setInt(5, cc.getVariacao());
      // Executa.
      stmt.execute();
      // Fecha o Statement.
      stmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void remove(ContaCorrente cc) {
    try {
      PreparedStatement stmt = connection.prepareStatement(SQL_DELETE);

      stmt.setLong(1, cc.getId());

      stmt.execute();
      stmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void altera(ContaCorrente cc) {
    try {
      PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE);

      stmt.setString(1, cc.getNumero());
      stmt.setString(2, cc.getAgencia());
      stmt.setString(3, cc.getDescricao());
      stmt.setBoolean(4, cc.isAtiva());
      stmt.setInt(5, cc.getVariacao());
      stmt.setLong(6, cc.getId());

      stmt.execute();
      stmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<ContaCorrente> getLista() {
    List<ContaCorrente> ccs = new ArrayList<>();

    try {
      PreparedStatement stmt = connection.prepareStatement(SQL_SELECT);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        ContaCorrente cc = new ContaCorrente();
        cc.setId(rs.getLong("id"));
        cc.setNumero(rs.getString("numero"));
        cc.setAgencia(rs.getString("agencia"));
        cc.setDescricao(rs.getString("descricao"));
        cc.setAtiva(rs.getBoolean("ativa"));
        cc.setVariacao(rs.getInt("variacao"));

        ccs.add(cc);
      }

      rs.close();
      stmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return ccs;
  }

  public ContaCorrente buscaPeloNumero(String numero) {
    ContaCorrente cc = null;

    try {
      PreparedStatement stmt = connection.prepareStatement(SQL_SEARCH);
      stmt.setString(1, numero);

      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        cc = new ContaCorrente();
        cc.setId(rs.getLong("id"));
        cc.setNumero(rs.getString("numero"));
        cc.setAgencia(rs.getString("agencia"));
        cc.setDescricao(rs.getString("descricao"));
        cc.setAtiva(rs.getBoolean("ativa"));
        cc.setVariacao(rs.getInt("variacao"));
      }

      rs.close();
      stmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return cc;
  }

}
