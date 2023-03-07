package projetHopital.dao;

import java.util.List;
import projetHopital.model.Patient;

public interface DaoSecretaire {
  //pause secrétaire
  void departPause(List<Patient> FileAttente);
  void retourPause();

  //ajouter patient à la file attente
  List<Patient> ajoutPatientFile(Patient patient); // retourne la liste de la file attente
}
