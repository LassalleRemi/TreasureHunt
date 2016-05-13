package fr.lille.iutinfo.actions;

import fr.lille.iutinfo.personnages.Parcelle;
import fr.lille.iutinfo.personnages.Personnage;
import fr.lille.iutinfo.terrain.Ile;

public abstract class Action {
	protected Personnage perso;
	protected Parcelle parcelle;
	protected Ile ile;

	public Action(Personnage perso, Parcelle parcelle, Ile ile) {
		// perso effectue action sur parcelle dans ile
		this.perso = perso;
		this.parcelle = parcelle;
		this.ile = ile;
	}

	public abstract void agit();
}
