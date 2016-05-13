package fr.lille.iutinfo.actions;

import fr.lille.iutinfo.affichage.Image;
import fr.lille.iutinfo.personnages.Parcelle;
import fr.lille.iutinfo.personnages.Personnage;
import fr.lille.iutinfo.personnages.Rocher;
import fr.lille.iutinfo.terrain.Ile;

public class Soulever extends Action {

	public Soulever(Personnage perso, Parcelle parcelle, Ile ile) {
		super(perso, parcelle, ile);
	}

	public void agit() {
		Rocher rocher = (Rocher) parcelle;
		perso.energie -= 5;
		if (rocher.cle) {
			perso.cle = true;
			rocher.cle = false;
			ile.afficherInfo("Clé découvete!");
		} else if (rocher.coffre && rocher.tresor) {
			ile.afficherInfo("Coffre découvert avec trésor!");
			perso.getEquipe().vue[rocher.coords.getX()][rocher.coords.getY()] = Image.COFFRE.monOrdinal();
			if (perso.cle) {
				rocher.tresor = false;
				perso.tresor = true;
				perso.cle = false;
				System.out.println("trésor!");
				perso.getEquipe().vue[rocher.coords.getX()][rocher.coords.getY()] = Image.COFFRE_OUVERT.monOrdinal();
			}
		} else if (rocher.coffre && !rocher.tresor) {
			ile.afficherInfo("Coffre découvert sans trésor!");
			perso.getEquipe().vue[rocher.coords.getX()][rocher.coords.getY()] = Image.COFFRE_OUVERT.monOrdinal();
		} else
			ile.afficherInfo("Il n'y a rien dedans");
	}

}
