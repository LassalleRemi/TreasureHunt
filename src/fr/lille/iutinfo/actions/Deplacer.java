package fr.lille.iutinfo.actions;

import fr.lille.iutinfo.personnages.Parcelle;
import fr.lille.iutinfo.personnages.Personnage;
import fr.lille.iutinfo.personnages.Piege;
import fr.lille.iutinfo.terrain.Coordonnees;
import fr.lille.iutinfo.terrain.Ile;

public class Deplacer extends Action {

	public Deplacer(Personnage perso, Parcelle parcelle, Ile ile) {
		super(perso, parcelle, ile);
	}

	public void agit() {
		perso.energie -= 1;
		if(parcelle instanceof Piege){
			Piege piege = (Piege) parcelle;
			ile.afficherInfo("Vous êtes tombé dans un piege");
			perso.energie -= 5;
			piege.equipe.removeVisible(piege); // on l'enleve dans la vue de l'équipe qui l'as posé
		}
		// copie des coordonnees pour eviter des problemes de references
		Coordonnees actuelle = new Coordonnees(perso.coords.getX(), perso.coords.getY());
		Coordonnees destination = new Coordonnees(parcelle.coords.getX(), parcelle.coords.getY());

		ile.positions[destination.getX()][destination.getY()] = ile.positions[actuelle.getX()][actuelle.getY()];
		ile.removePosition(actuelle); // on met du sable a la place du perso

		ile.setObjet(destination, perso);
		ile.removeObjet(actuelle);

		perso.getEquipe().removeVisible(perso);
		perso.coords.setX(destination.getX());
		perso.coords.setY(destination.getY());
		perso.getEquipe().rendVisible(perso, ile);
	}

}
