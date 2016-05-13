package fr.lille.iutinfo.actions;

import fr.lille.iutinfo.personnages.Parcelle;
import fr.lille.iutinfo.personnages.Personnage;
import fr.lille.iutinfo.terrain.Ile;

public class Ramasser extends Action {

	public Ramasser(Personnage perso, Parcelle parcelle, Ile ile) {
		super(perso, parcelle, ile);
	}

	public void agit() {
		if (parcelle.cle) {
			perso.cle = true;
			ile.afficherInfo("Clé ramassée!");
			perso.getEquipe().removeVisible(parcelle);
			ile.removeObjet(parcelle.coords);
			ile.removePosition(parcelle.coords);
		} else if (parcelle.tresor) {
			perso.tresor = true;
			ile.afficherInfo("Tresor ramassé!");
			perso.getEquipe().removeVisible(parcelle);
			ile.removeObjet(parcelle.coords);
			ile.removePosition(parcelle.coords);
		}
	}

}
