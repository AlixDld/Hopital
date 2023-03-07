package projetHopital.model;

public class Patient {

  private Integer idPatient;
  private String nom;
  private String prenom;

  public Patient(Integer idPatient, String nom, String prenom) {
    this.idPatient = idPatient;
    this.nom = nom;
    this.prenom = prenom;
  }

  public Integer getIdPatient() {
    return idPatient;
  }

  public void setIdPatient(Integer idPatient) {
    this.idPatient = idPatient;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idPatient == null) ? 0 : idPatient.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Patient other = (Patient) obj;
    if (idPatient == null) {
      if (other.idPatient != null) return false;
    } else if (!idPatient.equals(other.idPatient)) return false;
    return true;
  }
}
