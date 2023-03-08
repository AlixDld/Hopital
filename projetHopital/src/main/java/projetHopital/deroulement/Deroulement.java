package projetHopital.deroulement;

import java.util.ArrayList;
import java.util.List;

import projetHopital.authentification.*;

import projetHopital.model.Compte;
import projetHopital.model.Visite;

public class Deroulement {
	
	public void boucle() {
		Compte compte;
		while(true) {
			System.out.println("-- Bienvenue dans le service informatique de l'hôpital --;");
			try {
				compte = Authentification.identification();
				Authentification.authentification(compte);
			} catch (CompteIntrouvable e) {
				// compte introuvable
				continue;
			} catch (EchecAuthentification e) {
				// mauvais mdp
				continue; // verifier sir le contnue nous rappport au while
			}
			
			
			
			switch(compte.getTypeCompte()) {
				case "S":
					Action.Secretaire.boucleConnexion();
					break;
				case "M":
					//boucle médecin
					//interruption médecin continue
					break;
			}
		}
	}
	
	private void boucleSecretaire() {
		
	}



}
