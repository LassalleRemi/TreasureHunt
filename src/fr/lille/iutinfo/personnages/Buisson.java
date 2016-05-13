package fr.lille.iutinfo.personnages;

import fr.lille.iutinfo.terrain.Coordonnees;

public class Buisson extends Parcelle {

	public boolean nourriture;
	public boolean bouteille;

	public Buisson(Coordonnees coords, boolean nourriture, boolean bouteille) {
		super(false, coords);
		this.nourriture = nourriture;
		this.bouteille = bouteille;
	}

}
