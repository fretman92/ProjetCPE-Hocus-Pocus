package cartes.cartesHocus;

import partie.Joueur;
import partie.Partie;

public class Malediction extends Hocus{

	private Joueur joueurVise;
	
	public Malediction(int force, Partie partie) {
		super(partie);
		super.setNom("Malediction");
		super.setDescription("Piochez des cartes dans le grimoire d'un joueur d�sign�");
		super.setForce(force);
	}
	
	@Override
	public void jouerLaCarte(){		
		getPartie().viserUnJoueur(this.getPartie().getJoueurJouant());
		
	}
	
	@Override
	public void action() {
		if(isValide())
		{
			int numJoueurGrimoire = getPartie().getJoueurByID(joueurVise.getId());
			int numJoueurQuiChoisi = getPartie().getJoueurJouant();
			getPartie().demanderCartesDuGrimoire(numJoueurGrimoire, numJoueurQuiChoisi, this.getForce());
		}
	}
	
	public Joueur getJoueurVise() {
		return joueurVise;
	}

	@Override
	public void setJoueurVise(Joueur joueurVise) {
		this.joueurVise = joueurVise;		
	}	

}
