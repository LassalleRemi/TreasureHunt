package fr.lille.iutinfo.actions;

import fr.lille.iutinfo.personnages.Parcelle;
import fr.lille.iutinfo.personnages.Personnage;
import fr.lille.iutinfo.terrain.Ile;

public class Echanger extends Action {

	public Echanger(Personnage perso, Parcelle parcelle, Ile ile) {
		super(perso, parcelle, ile);
	}

	public void agit() {
		Personnage copain = (Personnage) parcelle;
		if (copain.cle) {
			perso.cle = true;
			copain.cle = false;
			ile.afficherInfo("Ce perso n'a plus sa clé, c'est " + perso + " qui l'as mtn");
		} else
			ile.afficherInfo("Ce perso n'a pas de clé sur lui");
		if (copain.tresor) {
			perso.tresor = true;
			copain.tresor = false;
			ile.afficherInfo("Ce perso n'a plus son tresor, c'est " + perso + " qui l'as mtn");
		} else
			ile.afficherInfo("Ce perso n'a pas de tresor sur lui");
	}

}
