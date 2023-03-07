package projetHopital.model;

public class Secretaire extends Compte {

  private String typeCompte;

  public Secretaire(
    Integer idCompte,
    String login,
    String password,
    String typeCompte
  ) {
    super(idCompte, login, password);
    this.typeCompte = "S";
  }

  public String getTypeCompte() {
    return typeCompte;
  }

  public void setTypeCompte(String typeCompte) {
    this.typeCompte = typeCompte;
  }
}
