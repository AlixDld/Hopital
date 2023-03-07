package projetHopital.deroulement;

import projetHopital.authentification.*;
import projetHopital.model.Compte;

public class Deroulement {
	
public void boucle() {
		while(true) {
			System.out.println("-- Bienvenue dans le service informatique de l'hôpital --;");
			try {
				Compte compte = Authentification.identification();
				Authentification.authentification(compte);
			} catch (CompteIntrouvable e) {
				// compte introuvable
				continue;
			} catch (EchecAuthentification e) {
				// mauvais mdp
				continue;
			}
			
			
			
			
			
			
			//demande identificatio
			//authentifier
			//si échec continue
			
			// charge la liste des visites en mémoire
			
			//si compte == secrétaire
			//boucle secrétaire
				// interruption de secrataire continue
			
			// si compte == médecin
			//boucle médecin
				//interruption médecin continue
			
			// on sauvegarde la liste de visites
		}
		
	}



}
