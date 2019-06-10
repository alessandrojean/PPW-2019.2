package br.com.bb.contacorrente.modelo;

public class ContaCorrente {
  private Long id;
  private String numero;
  private String agencia;
  private String descricao;
  private boolean ativa;
  private int variacao;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getAgencia() {
    return agencia;
  }

  public void setAgencia(String agencia) {
    this.agencia = agencia;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public boolean isAtiva() {
    return ativa;
  }

  public void setAtiva(boolean ativa) {
    this.ativa = ativa;
  }

  public int getVariacao() {
    return variacao;
  }

  public void setVariacao(int variacao) {
    this.variacao = variacao;
  }
  
  @Override
  public String toString() {
    return "ContaCorrente {\n"
        + "  id = " + id + "\n"
        + "  numero = \"" + numero + "\"\n"
        + "  agencia = \"" + agencia + "\"\n"
        + "  descricao = \"" + descricao + "\"\n"
        + "  ativa = " + (ativa ? "true" : "false") + "\n"
        + "  variacao = " + variacao + "\n"
        + "}\n";
  }

}
