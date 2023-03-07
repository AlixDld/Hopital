package projetHopital.authentification;

public class CompteIntrouvable extends Exception{
	private static final long serialVersionUID = 1L;

	public CompteIntrouvable(String message) {
		super(message);
	}
	
	public CompteIntrouvable(int id) {
		System.out.println("Identifiant " + id + " introuvable dans la base de donnée");
	}


}

