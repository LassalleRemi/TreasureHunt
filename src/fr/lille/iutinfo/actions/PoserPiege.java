package fr.lille.iutinfo.actions;

import fr.lille.iutinfo.affichage.Image;
import fr.lille.iutinfo.personnages.Parcelle;
import fr.lille.iutinfo.personnages.Personnage;
import fr.lille.iutinfo.personnages.Piege;
import fr.lille.iutinfo.terrain.Ile;

public class PoserPiege extends Action {

	public PoserPiege(Personnage perso, Parcelle parcelle, Ile ile) {
		super(perso, parcelle, ile);
	}

	public void agit() {
		perso.energie -= 5;
		perso.getEquipe().vue[parcelle.coords.getX()][parcelle.coords.getY()] = Image.PIEGE.monOrdinal();
		ile.setObjet(parcelle.coords, new Piege(perso.getEquipe(), parcelle.coords));
	}
}
