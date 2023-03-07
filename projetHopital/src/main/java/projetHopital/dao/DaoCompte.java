package projetHopital.dao;

import java.util.List;
import projetHopital.model.Compte;
import projetHopital.model.Patient;

public interface DaoCompte extends DaoGeneric<Compte, Integer> {
  //affichage file attente
  void affichFileAttente(List<Patient> FileAttente);
  //affichage prochain patient
  void affichProchainPatient(List<Patient> FileAttente);
}
