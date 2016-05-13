package fr.lille.iutinfo.personnages;

import fr.lille.iutinfo.terrain.Coordonnees;

public class Parcelle {
	public boolean estTraversable = false; // si cette case est accessible
	public Coordonnees coords = new Coordonnees(0, 0);
	public boolean cle = false;
	public boolean tresor = false;
	public boolean piege = false;

	/**
	 * Constructeur par d�faut de la classe Parcelle.
	 */
	public Parcelle() {
	}

	/**
	 * Constructeur de la classe Parcelle, un param�tre demand�.
	 * 
	 * @param estTraversable
	 *            Boolean qui d�termine si l'objet cr�� est traversable ou non.
	 */
	public Parcelle(boolean estTraversable, Coordonnees coords) {
		this.estTraversable = estTraversable;
		this.coords = coords;
	}

	// retourne un tableau contenant les coordonnes du champ de vision de la
	// parcelle
	public Coordonnees[] champDeVision() {
		return null;
	}

	// retourne un boolean qui signifie si les coordonnees pass�es en parametre
	// font parti du champ de vision
	public boolean faitPartiDuChampDeVision(Coordonnees coords) {
		Coordonnees champDeVision[] = champDeVision();
		for (int i = 0; i < champDeVision.length; i++)
			if (coords.equals(champDeVision[i]))
				return true;
		return false;
	}
}
