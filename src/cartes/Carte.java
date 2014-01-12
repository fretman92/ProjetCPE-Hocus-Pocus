package cartes;

import partie.Joueur;
import partie.Partie;

public class Carte {

	public enum CarteType {
		hocus, pocus
	};

	private CarteType type;

	private String nom;
	private String description;

	private Partie partie;

	// si la carte est toujours valide, non deffaussée par une autre
	private boolean estValide;

	protected Carte(Partie maPartie) {
		estValide = true;
		setPartie(maPartie);
	}

	public Carte(String nom, String descrp) {
		setNom(nom);
		setDescription(descrp);
	}

	public String toJson() { // JSONObject json =new JSONObject();
		String nom = "";
		if (this.getType() == CarteType.hocus)
			nom = "Hocus";
		else if (this.getType() == CarteType.pocus)
			nom = "Pocus";
		nom += this.getNom();
		if (this.getForce() != 0)
			nom += this.getForce();
		// try { // json.put("nomCarte", nom); // }
		// catch (JSONException e) { // // TODO
		// Auto-generated catch block //
		// e.printStackTrace(); // }
		return nom.trim(); 
	}

	public boolean isValide() {
		return estValide;
	}

	public void setEstValide(boolean estValide) {
		this.estValide = estValide;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public CarteType getType() {
		return type;
	}

	public void setType(CarteType type) {
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return getType() + "-->" + getNom() + " : " + getDescription();
	}

	// Dummy functions
	public int getForce() {
		return 0;
	}

	public void setForce(int force) {
	}

	public void jouerLaCarte() {
	}

	public void action() {
	}

	public void setJoueurVise(Joueur jVise) {

	}

}
