package projetHopital.model;

public class Medecin extends Compte {

  private String typeCompte;

  public Medecin(
    Integer idCompte,
    String login,
    String password,
    String typeCompte
  ) {
    super(idCompte, login, password);
    this.typeCompte = "M";
  }

  public String getTypeCompte() {
    return typeCompte;
  }

  public void setTypeCompte(String typeCompte) {
    this.typeCompte = typeCompte;
  }
}
