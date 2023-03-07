package projetHopital.model;

import java.time.LocalDate;

public class Visite {

  private Integer numeroVisite; //auto-increment
  private Patient patient;
  private Compte medecin;
  private int coutVisite;
  private int salle;
  private LocalDate dateVisite;

  public Visite(
    Integer numeroVisite,
    Patient patient,
    Compte medecin,
    int coutVisite,
    int salle,
    LocalDate dateVisite
  ) {
    this.numeroVisite = numeroVisite;
    this.patient = patient;
    this.medecin = medecin;
    this.coutVisite = coutVisite;
    this.salle = salle;
    this.dateVisite = dateVisite;
  }

  public Integer getNumeroVisite() {
    return numeroVisite;
  }

  public void setNumeroVisite(Integer numeroVisite) {
    this.numeroVisite = numeroVisite;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public Compte getMedecin() {
    return medecin;
  }

  public void setMedecin(Compte medecin) {
    this.medecin = medecin;
  }

  public int getCoutVisite() {
    return coutVisite;
  }

  public void setCoutVisite(int coutVisite) {
    this.coutVisite = coutVisite;
  }

  public int getSalle() {
    return salle;
  }

  public void setSalle(int salle) {
    this.salle = salle;
  }

  public LocalDate getDateVisite() {
    return dateVisite;
  }

  public void setDateVisite(LocalDate dateVisite) {
    this.dateVisite = dateVisite;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result =
      prime * result + ((numeroVisite == null) ? 0 : numeroVisite.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Visite other = (Visite) obj;
    if (numeroVisite == null) {
      if (other.numeroVisite != null) return false;
    } else if (!numeroVisite.equals(other.numeroVisite)) return false;
    return true;
  }
}
