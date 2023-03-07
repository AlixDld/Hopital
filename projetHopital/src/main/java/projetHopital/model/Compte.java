package projetHopital.model;

public class Compte {

  private Integer idCompte;
  private String login;
  private String pwd;

  public Compte() {}

  public Compte(Integer idCompte, String login, String password) {
    this.idCompte = idCompte;
    this.login = login;
    password = pwd;
  }

  public Integer getIdCompte() {
    return idCompte;
  }

  public void setIdCompte(Integer idCompte) {
    this.idCompte = idCompte;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
}
