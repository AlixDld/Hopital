package projetHopital.authentification;

public class EchecAuthentification extends Exception{
	private static final long serialVersionUID = 1L;
	
	public EchecAuthentification(String message) {
		super(message);
	}
	
	public EchecAuthentification() {
		System.out.println("Mot de passe incorrect");
	}


}