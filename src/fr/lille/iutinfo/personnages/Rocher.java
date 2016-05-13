package fr.lille.iutinfo.personnages;

import fr.lille.iutinfo.terrain.Coordonnees;

public class Rocher extends Parcelle {
	public boolean coffre = false;

	/**
	 * Constructeur de la classe Rocher.
	 */
	public Rocher() {
		super.estTraversable = false;
	}

	public Rocher(Coordonnees coords, boolean cle, boolean coffre, boolean tresor) {
		super(false, coords);
		this.cle = cle;
		this.coffre = coffre;
		this.tresor = tresor;
	}
}
