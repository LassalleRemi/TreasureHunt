package fr.lille.iutinfo.actions;

import fr.lille.iutinfo.personnages.Parcelle;
import fr.lille.iutinfo.personnages.Personnage;
import fr.lille.iutinfo.terrain.Ile;

public class Voler extends Action {

	public Voler(Personnage perso, Parcelle parcelle, Ile ile) {
		super(perso, parcelle, ile);
	}

	public void agit() {
		Personnage adversaire = (Personnage) parcelle;
		perso.energie -= 10;
		if (adversaire.cle) {
			perso.cle = true;
			adversaire.cle = false;
			ile.afficherInfo(adversaire + " n'a plus sa clé, c'est " + perso + " qui l'as mtn");
		} else
			ile.afficherInfo(adversaire + " n'a pas de clé sur lui");
		if (adversaire.tresor) {
			perso.tresor = true;
			adversaire.tresor = false;
			ile.afficherInfo(adversaire + " n'a plus son tresor, c'est " + perso + " qui l'as mtn");
		} else
			ile.afficherInfo(adversaire + " n'a pas de tresor sur lui");
	}
}
