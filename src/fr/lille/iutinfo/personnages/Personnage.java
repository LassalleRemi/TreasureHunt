package fr.lille.iutinfo.personnages;

import java.awt.event.InputEvent;

import fr.lille.iutinfo.actions.Action;
import fr.lille.iutinfo.actions.Deplacer;
import fr.lille.iutinfo.actions.Echanger;
import fr.lille.iutinfo.actions.EntrerNavire;
import fr.lille.iutinfo.actions.Fouiller;
import fr.lille.iutinfo.actions.Ramasser;
import fr.lille.iutinfo.affichage.Image;
import fr.lille.iutinfo.terrain.Coordonnees;
import fr.lille.iutinfo.terrain.Ile;

public abstract class Personnage extends Parcelle {
	Equipe equipe;
	Action action = null;
	public int energie = 90;

	/**
	 * @param equipe
	 */
	public Personnage(Equipe equipe) {
		this.equipe = equipe;
		equipe.addPerso(this); // on ajoute le perso a l'équipe
		equipe.getNavire().addPerso(this); // on ajoute le perso a son navire
		estTraversable = false;
	}

	/**
	 * @param x
	 * @param y
	 * @param p
	 * @param ile
	 */
	public void placer(int x, int y, Ile ile) {
		ile.setObjet(new Coordonnees(x, y), this);
		this.coords.setX(x);
		this.coords.setY(y);
		equipe.rendVisible(this, ile);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param dX
	 * @param dY
	 * @param ile
	 * @return
	 */
	boolean action(int dX, int dY, Ile ile, InputEvent event) {
		Parcelle p = ile.getObjet(new Coordonnees(dX, dY)); // parcelle ou on
															// fait l action
		if (p instanceof Navire)
			action = new EntrerNavire(this, p, ile);
		else if (p instanceof Personnage && equipe.appartient((Personnage) p)) {
			action = new Echanger(this, p, ile);
		} else if (p.estTraversable) {
			action = new Deplacer(this, p, ile);
		} else if ((p.tresor || p.cle) && !(p instanceof Rocher) && !(p instanceof Personnage)) {
			action = new Ramasser(this, p, ile);
		} else if (p instanceof Buisson) {
			action = new Fouiller(this, p, ile);
		}else
			return false;
		action.agit();
		return true; // une action a ete faite
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public String toString() {
		return this.getClass().getSimpleName() + ", de l'équipe " + equipe + ", avec " + energie + " d'énergie";
	}

	public int[][] surbrillance(Ile ile) {
		int[][] surbrillanceVue = new int[ile.taille][ile.taille];
		// on copie la vue de l'équipe
		for (int i = 0; i < ile.taille; i++)
			for (int j = 0; j < ile.taille; j++)
				surbrillanceVue[i][j] = equipe.vue[i][j];
		surbrillance(surbrillanceVue, ile);
		return surbrillanceVue;
	}

	protected abstract void surbrillance(int[][] surbrillance_vue, Ile ile);

	public boolean estMort(Ile ile) {
		if (energie <= 0) {
			equipe.removeVisible(this);
			ile.removeObjet(coords);
			ile.removePosition(coords);
			// si le perso a des objets il les lache
			// on lache un objet sur sa position actuelle
			if (cle) {
				ile.getObjet(coords).cle = true;
				ile.getObjet(coords).estTraversable = false;
				equipe.vue[coords.getX()][coords.getY()] = Image.CLE.monOrdinal();
				ile.positions[coords.getX()][coords.getY()] = Image.CLE.monOrdinal();
				this.cle = false; // il perd sa cle
			} else if (tresor) {
				ile.getObjet(coords).tresor = true;
				ile.getObjet(coords).estTraversable = false;
				equipe.vue[coords.getX()][coords.getY()] = Image.TRESOR.monOrdinal();
				ile.positions[coords.getX()][coords.getY()] = Image.TRESOR.monOrdinal();
				this.tresor = false; // il perd son tresor
			}
			equipe.sontMorts(ile);
			ile.afficherInfo(this + " est mort");
			return true;

		} else
			return false;
	}

}
