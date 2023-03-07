package projetHopital.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import projetHopital.model.Patient;

public class DaoSecretaireImpl implements DaoSecretaire {

  @Override
  public void departPause(List<Patient> FileAttente) {
    // ecriture de la file attente dans un fichier
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    try {
      fos = new FileOutputStream("Sauvegarde_file_attente.txt");
      oos = new ObjectOutputStream(fos);
      oos.writeObject(FileAttente);
      oos.close();
      fos.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public void retourPause() {
    //récupère la file attente dans une liste
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    try {
      fis = new FileInputStream("Sauvegarde_file_attente.txt");
      ois = new ObjectInputStream(fis);
      Object obj = ois.readObject();
      List<Patient> patients = (List<Patient>) obj;
      patients.forEach(patient -> {
        System.out.println(
          patient.getIdPatient() +
          " " +
          patient.getNom() +
          " " +
          patient.getPrenom()
        );
      });
      ois.close();
      fis.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Patient> ajoutPatientFile(Patient patient) {
    List<Patient> patients = new ArrayList<>();
    patients.add(patient);
    return patients;
  }
}
