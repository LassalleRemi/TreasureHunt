package fr.lille.iutinfo.actions;

import fr.lille.iutinfo.affichage.Image;
import fr.lille.iutinfo.personnages.Buisson;
import fr.lille.iutinfo.personnages.Parcelle;
import fr.lille.iutinfo.personnages.Personnage;
import fr.lille.iutinfo.terrain.Ile;

public class Fouiller extends Action {

	public Fouiller(Personnage perso, Parcelle parcelle, Ile ile) {
		super(perso, parcelle, ile);
	}

	public void agit() {
		Buisson buisson = (Buisson) parcelle;
		if (buisson.nourriture) {
			buisson.nourriture = false;
			perso.energie += 5;
			perso.getEquipe().vue[buisson.coords.getX()][buisson.coords.getY()] = Image.NOURRITURE.monOrdinal();
			ile.afficher(perso.getEquipe().vue);
			ile.afficherInfo("Vous gagnez 5 pts d'énergie, vous en avez : " + perso.energie);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			perso.getEquipe().vue[buisson.coords.getX()][buisson.coords.getY()] = Image.BUISSON.monOrdinal();
		} else if (buisson.bouteille) {
			buisson.bouteille = false;
			perso.energie += 5;
			perso.getEquipe().vue[buisson.coords.getX()][buisson.coords.getY()] = Image.BOUTEILLE.monOrdinal();
			ile.afficher(perso.getEquipe().vue);
			ile.afficherInfo("Vous gagnez 5 pts d'énergie, vous en avez : " + perso.energie);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			perso.getEquipe().vue[buisson.coords.getX()][buisson.coords.getY()] = Image.BUISSON.monOrdinal();
		} else
			ile.afficherInfo("Il n'y a plus rien dans ce buisson");
	}

}
