package projetHopital.deroulement;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

import projetHopital.model.Visite;
import projetHopital.model.Patient;
import env.Environnement;

import projetHopital.dao.Context;
import projetHopital.dao.DaoPatient;


public class Action {
	
	static class Secretaire{
		
		private static List<Visite> fileDAttente = null;
		
		static boolean chargerFile() {
			try {
	            FileInputStream fileIn = new FileInputStream(Environnement.cheminFile);
	            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	            List<Visite> file = (List<Visite>) objectIn.readObject();
	            System.out.println("La file d'attente est chargée en mémoire.");
	            objectIn.close();
	            return true;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return false;
	        }
		}
		
		static boolean ecrireFile() {
			List<Visite> file = Action.Secretaire.fileDAttente;
			try {
	            FileOutputStream fileOut = new FileOutputStream(Environnement.cheminFile);
	            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	            objectOut.writeObject(file);
	            objectOut.close();
	            System.out.println("La file d'attente a été sauvegardée sous " + Environnement.cheminFile);
	            return true;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return false;
	        }
		}
		
		static void boucleConnexion() {
			Action.Secretaire.chargerFile();
			// TODO vérif si le chargement a réussi
			int choix;
			boolean connexion = true;
			while(connexion) {
				choix = Secretaire.selection();
				switch(choix) {
				case 1:
					int id = Action.Secretaire.demanderIndex("Entrez l'ID du patient :");
					if (id == -1) {
						System.out.println("annulation"); // a remplacer par un try catch
						continue;
					}
					int salle = Action.Secretaire.demanderIndex("Entrez le numéro de la salle :");
					if (salle == -1) {
						System.out.println("annulation"); // a remplacer par un try catch
						continue;
					}
					Patient patient = Action.Secretaire.chercherPatient(id);
					if (patient == null) {					// a remplacer par un try catch
						System.out.println("patient introuvable");
						continue;
					}
					Action.Secretaire.ajouterVisite(patient, salle);
					// vérif 
					break;
				case 2:
					//Afficher l'état de la file d'attente 
					// ???
					break;
				case 3:
					// Partir en pause
					Action.Secretaire.ecrireFile();
					// que faire qi l'écriture échoue
					connexion = false;
					break;
				default:
					break;
				}
			}
		}
		
		private static Patient chercherPatient(int id) {
			Context.getDaoPatient();
			DaoPatient daoPatient = (DaoPatient) Context.getDaoPatient();
			Patient patient = daoPatient.findByKey(id);
			return patient;
		}
		
		static int demanderIndex(String invite) {
			System.out.println(invite);
			Scanner sc = new Scanner(System.in);
			int id = sc.nextInt();
			sc.close();
			return id;
			
		}
	
		
		
		
		private static void ajouterVisite(Patient patient, int salle) {
			//créer un obket Visite
			//Secretaire.fileDAttente.add()
			
		}

		static int selection() {
			return Action.selection(new String[] {"Ajouter un patient à la file d'attente",
					"Afficher l'état de la file d'attente",
					"Partir en pause"});
		}
		
		
	}
	
	
	static int selection(String [] options) {
		Scanner sc = new Scanner(System.in);
		int choix = 0;
		int n=0;
		while(choix > 3 || choix < 0) {
			System.out.println("Sélectionnez une action");
			for(String chaine : options) {
				n++;
				System.out.println(n + ") " + chaine + "\n");
			}
			sc.nextInt();
		}
		sc.close();
		return choix;
	}
	
	

}
