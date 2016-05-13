package fr.lille.iutinfo.personnages;

import java.awt.event.InputEvent;

import fr.lille.iutinfo.actions.Soulever;
import fr.lille.iutinfo.affichage.Image;
import fr.lille.iutinfo.terrain.Coordonnees;
import fr.lille.iutinfo.terrain.Ile;

public class Explorateur extends Personnage {

	public Explorateur(Equipe equipe) {
		super(equipe);
	}

	public void placer(int x, int y, Ile ile) {
		super.placer(x, y, ile);
		if (equipe.numero == 1) {
			ile.positions[x][y] = Image.EXPLORATEUR.monOrdinal();
			equipe.vue[x][y] = Image.EXPLORATEUR.monOrdinal();
		} else if (equipe.numero == 2) {
			ile.positions[x][y] = Image.EXPLORATEUR2.monOrdinal();
			equipe.vue[x][y] = Image.EXPLORATEUR2.monOrdinal();
		}
	}

	boolean action(int dX, int dY, Ile ile, InputEvent event) {
		Parcelle p = ile.getObjet(new Coordonnees(dX, dY));
		if (this.faitPartiDuChampDeVision(new Coordonnees(dX, dY))) {
			if (super.action(dX, dY, ile, event))
				return true; // action deja faite
			else if (p instanceof Rocher)
				action = new Soulever(this, p, ile);
			else
				return false;
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
			} else if (ile.getObjet(champDeVision[i]) instanceof Personnage) {
				Personnage perso = (Personnage) (ile.getObjet(champDeVision[i]));
				if (perso.equipe.appartient(this))
					surbrillance_vue[champDeVision[i].getX()][champDeVision[i].getY()] += 1;
			} else if (equipe.vue[champDeVision[i].getX()][champDeVision[i].getY()] != Image.PIEGE.monOrdinal()
					&& equipe.vue[champDeVision[i].getX()][champDeVision[i].getY()] != Image.EAU.monOrdinal())
				surbrillance_vue[champDeVision[i].getX()][champDeVision[i].getY()] += 1;
		}
	}

	public Coordonnees[] champDeVision() {
		Coordonnees coord[] = new Coordonnees[] { new Coordonnees(coords.getX() + 1, coords.getY()),
				new Coordonnees(coords.getX() - 1, coords.getY()), new Coordonnees(coords.getX(), coords.getY() + 1),
				new Coordonnees(coords.getX(), coords.getY() - 1) };
		return coord;
	}

}
