package fr.lille.iutinfo.personnages;

import fr.lille.iutinfo.terrain.Coordonnees;

public class Piege extends Parcelle {

	public Equipe equipe;

	public Piege(Equipe equipe, Coordonnees coords) {
		super(true, coords);
		this.equipe = equipe;
	}

}
