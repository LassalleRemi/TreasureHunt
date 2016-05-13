package fr.lille.iutinfo.actions;

import fr.lille.iutinfo.personnages.Parcelle;
import fr.lille.iutinfo.personnages.Personnage;
import fr.lille.iutinfo.terrain.Ile;

public class Attaquer extends Action {

	public Attaquer(Personnage perso, Parcelle parcelle, Ile ile) {
		super(perso, parcelle, ile);
	}

	public void agit() {
		perso.energie -= 10;
		Personnage adversaire = (Personnage) parcelle;
		adversaire.energie -= adversaire.energie;
		adversaire.estMort(ile);
		perso.getEquipe().vueCommune(ile); // on maj la vue de l'équipe
		ile.afficher(perso.getEquipe().vue); // et on l'affiche
	}

}
