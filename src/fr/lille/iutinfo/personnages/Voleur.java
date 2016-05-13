package fr.lille.iutinfo.personnages;

import java.awt.event.InputEvent;

import fr.lille.iutinfo.actions.Voler;
import fr.lille.iutinfo.affichage.Image;
import fr.lille.iutinfo.terrain.Coordonnees;
import fr.lille.iutinfo.terrain.Ile;

public class Voleur extends Personnage {

	public Voleur(Equipe equipe) {
		super(equipe);
	}

	public void placer(int x, int y, Ile ile) {
		super.placer(x, y, ile);
		if (equipe.numero == 1) {
			ile.positions[x][y] = Image.VOLEUR.monOrdinal();
			equipe.vue[x][y] = Image.VOLEUR.monOrdinal();
		} else if (equipe.numero == 2) {
			ile.positions[x][y] = Image.VOLEUR2.monOrdinal();
			equipe.vue[x][y] = Image.VOLEUR2.monOrdinal();
		}
	}

	boolean action(int dX, int dY, Ile ile, InputEvent event) {
		// parcelle de l'action
		Parcelle p = ile.getObjet(new Coordonnees(dX, dY));
		if (this.faitPartiDuChampDeVision(new Coordonnees(dX, dY))) {
			if (super.action(dX, dY, ile, event))
				return true;
			else if (p instanceof Personnage) {
				Personnage perso = (Personnage) p;
				if (!equipe.appartient(perso))
					action = new Voler(this, p, ile);
				else
					ile.afficherInfo("Ce perso est de votre �quipe");
			} else
				return false; // pas d action faite
		} else
			return false;
		action.agit();
		return true; // une action a ete faite
	}

	protected void surbrillance(int[][] surbrillance_vue, Ile ile) {
		Coordonnees champDeVision[] = champDeVision();
		for (int i = 0; i < champDeVision.length; i++) {
			if (ile.getObjet(champDeVision[i]) instanceof Navire) {
				Navire n = (Navire) (ile.getObjet(champDeVision[i]));
				if (n.equipe.appartient(this))
					surbrillance_vue[champDeVision[i].getX()][champDeVision[i].getY()] += 1;
			} else if (equipe.vue[champDeVision[i].getX()][champDeVision[i].getY()] != Image.PIEGE.monOrdinal()
					&& !(ile.getObjet(champDeVision[i]) instanceof Rocher)
					&& equipe.vue[champDeVision[i].getX()][champDeVision[i].getY()] != Image.EAU.monOrdinal())
				surbrillance_vue[champDeVision[i].getX()][champDeVision[i].getY()] += 1;
		}
	}

	public Coordonnees[] champDeVision() {
		Coordonnees coord[] = new Coordonnees[] { new Coordonnees(coords.getX() + 1, coords.getY()),
				new Coordonnees(coords.getX() - 1, coords.getY()), new Coordonnees(coords.getX(), coords.getY() + 1),
				new Coordonnees(coords.getX(), coords.getY() - 1),
				new Coordonnees(coords.getX() + 1, coords.getY() + 1),
				new Coordonnees(coords.getX() - 1, coords.getY() - 1),
				new Coordonnees(coords.getX() + 1, coords.getY() - 1),
				new Coordonnees(coords.getX() - 1, coords.getY() + 1) };
		return coord;
	}
}
