package projetHopital.authentification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import projetHopital.model.*;
import projetHopital.dao.*;


public class Authentification {
	String reponse;
	int id = 0;


	
	public static Compte identification() throws CompteIntrouvable{
		Scanner sc = new Scanner(System.in);
		int choix;
		Compte compte = null;
		boolean drapeau = true;
		
		while(drapeau) {
			System.out.println("Choisissez un mode d'identification : " +
					"\n1) ID numérique" +
					"\n2) nom et prénom" +
					"\n3) quitter");
			choix = sc.nextInt();
			switch(choix) {
				case 1:
					return identificationId();
				case 2:
					// TODO identification nom + prénom
					break;
				case 3:
					//TODO quitter l'identification
					drapeau = false;
					break;
				default:
					//TODO
					break;
			}
		
		}
		sc.close();
		return compte;
	}
	
	public static Compte identificationId() throws CompteIntrouvable {
		
		Scanner sc = new Scanner(System.in);
		int id = 0;
		boolean drapeau = true;
		
		DaoCompte compteDao = Context.getDaoCompte();
		Compte compte;
	

		while(drapeau) {
			System.out.println("Entrez votre identifiant numérique : ");
			id = sc.nextInt();
			// verif intégrité id
		}
		compte = compteDao.findByKey(id);
		if(compte == null) {
			sc.close();
			throw new CompteIntrouvable(id); // retour au menu
		}

		return compte;
	}
	
	public static boolean authentification(Compte compte) throws EchecAuthentification{
		String mdp;
		Scanner sc = new Scanner(System.in);
		
		for(int essai = 0 ; essai < 3 ; essai++) {
			System.out.println("Entrez votre mdp : ");
			mdp = sc.nextLine();
			if (mdp.equals(compte.getPwd()))
				return true;
		}
		System.out.println("Échec de l'authentification : ");
		sc.close();
		throw new EchecAuthentification();
		
	}
	
	
	
	
	
	
//	public Object chargerFile() {
//		
//        try {
// 
//            FileInputStream fileIn = new FileInputStream(Configuration.cheminFile);
//            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
// 
//            List<Patient> file = (List<Patient>) objectIn.readObject();
// 
//            System.out.println("La file d'attente est chargée en mémoire.");
//            objectIn.close();
//            return file;
// 
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//	}
//	
//	public void ecrireFile(List<Patient> file) {
//        try {
//            FileOutputStream fileOut = new FileOutputStream(Configuration.cheminFile);
//            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
//            objectOut.writeObject(file);
//            objectOut.close();
//            System.out.println("La file d'attente a été sauvegardée sous " + Configuration.cheminFile);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//	
//	public void init() {
//		// écrire une liste d'attente vide le répertoire courant
//		// vérif si sql fonctionne
//	}
	
	

}
