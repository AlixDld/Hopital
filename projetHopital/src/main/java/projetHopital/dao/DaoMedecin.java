package projetHopital.dao;

public interface DaoMedecin {
  //salle dispo : accueille patient => retire la patient file attente ET ajoute à visite
  void medecindispo();

  //sauvegarder liste visites dans la base de données
  void sauvegardeVisit();
}
