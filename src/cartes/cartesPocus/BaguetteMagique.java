package cartes.cartesPocus;

import interfaceclientserveur.Interface;
import partie.Partie;
import cartes.Carte;

public class BaguetteMagique extends Pocus {

	private Carte carteVisee;

	public Carte getCarteVisee() {
		return carteVisee;
	}

	public void setCarteVisee(Carte visee) {
		this.carteVisee = visee;
	}

	public BaguetteMagique(Partie partie) {
		super(partie);
		super.setNom("Baguette Magique");
		super.setDescription("Doublez la valeur de la carte HOCUS");
	}

	public void jouerLaCarte() {
		Carte visee = this.getPartie().getAireDeJeu().getPileDeCarte().get(0);

		if (visee.getForce() != 0)
			setCarteVisee(visee);
		else
			Interface
					.Console(
							"La carte hocus en question ne peu pas etre cible d'une Baguette Magique.",
							this.getPartie());
	}

	@Override
	public void action() {
		if (isValide()) {
			if (carteVisee != null) {
				int force = carteVisee.getForce();
				carteVisee.setForce(force * 2);
				Interface.Console("Baguette magique : " + carteVisee,
						this.getPartie());
			}
			else
				Interface.Console("La Baguette Magique n'a pas d'effet sur cette carte", getPartie());
		}
	}
}
