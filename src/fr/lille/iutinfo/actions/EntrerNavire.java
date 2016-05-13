package fr.lille.iutinfo.actions;

import fr.lille.iutinfo.affichage.Image;
import fr.lille.iutinfo.personnages.Navire;
import fr.lille.iutinfo.personnages.Parcelle;
import fr.lille.iutinfo.personnages.Personnage;
import fr.lille.iutinfo.terrain.Ile;

public class EntrerNavire extends Action {

	public EntrerNavire(Personnage perso, Parcelle parcelle, Ile ile) {
		super(perso, parcelle, ile);
	}

	public void agit() {
		Navire navire = (Navire) parcelle;
		if (navire.getEquipe().appartient(perso)) {
			perso.energie -= 1;
			navire.addPerso(perso);
			navire.getEquipe().removeVisible(perso);
			ile.positions[perso.coords.getX()][perso.coords.getY()] = Image.SABLE.monOrdinal(); // du sable
			ile.setObjet(perso.coords, new Parcelle(true, perso.coords));
			if (perso.tresor) {
				ile.afficherInfo("Bravo, l'équipe " + perso.getEquipe() + " gagne la partie");
				navire.tresor = true;
			}
		} else
			ile.afficherInfo("Ce n'est pas votre bateau");
	}

}
