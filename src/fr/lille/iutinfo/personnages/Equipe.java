package fr.lille.iutinfo.personnages;

import java.util.ArrayList;

import fr.lille.iutinfo.affichage.Image;
import fr.lille.iutinfo.terrain.Coordonnees;
import fr.lille.iutinfo.terrain.Ile;

public class Equipe {
	private ArrayList<Personnage> equipe = new ArrayList<>();
	String nom;
	private Navire navire;
	public int[][] vue;
	public int numero;
	private static int num = 1;

	public Equipe(String nom) {
		this.nom = nom;
		numero = num;
		num++;
	}

	public Navire getNavire() {
		return navire;
	}

	public void setNavire(Navire navire, Ile ile) {
		this.navire = navire;
		initVue(ile.taille);
		rendVisible(navire, ile);
	}

	private void initVue(int taille) {
		vue = new int[taille][taille];
		for (int i = 0; i < taille; i++)
			for (int j = 0; j < taille; j++)
				if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1)
					vue[i][j] = Image.EAU.monOrdinal(); // de leau normale
				else
					vue[i][j] = Image.SOMBRE.monOrdinal(); // du sable sombre
	}

	// on rends visible les elements autour de la parcelle
	public void rendVisible(Parcelle p, Ile ile) {
		Coordonnees champDeVision[] = p.champDeVision();
		Coordonnees coords;
		// on met le perso dans vue
		vue[p.coords.getX()][p.coords.getY()] = ile.positions[p.coords.getX()][p.coords.getY()];
		for (int i = 0; i < champDeVision.length; i++) {
			coords = champDeVision[i];
			if (vue[coords.getX()][coords.getY()] == Image.SOMBRE.monOrdinal())
				vue[coords.getX()][coords.getY()] = ile.positions[coords.getX()][coords.getY()];
		}
	}

	// synchronisation avec positions
	public void vueCommune(Ile ile) {
		for (int i = 0; i < vue.length; i++)
			for (int j = 0; j < vue.length; j++)
				if (vue[i][j] != Image.SOMBRE.monOrdinal() && vue[i][j] != Image.COFFRE.monOrdinal()
						&& vue[i][j] != Image.COFFRE_OUVERT.monOrdinal() && vue[i][j] != Image.PIEGE.monOrdinal()) {
					vue[i][j] = ile.positions[i][j];
				}
	}

	public void removeVisible(Parcelle p) {
		vue[p.coords.getX()][p.coords.getY()] = Image.SABLE.monOrdinal();
	}

	public String toString() {
		return nom;
	}

	public void addPerso(Personnage pers) {
		equipe.add(pers);
	}

	public void removePerso(Personnage pers) {
		equipe.remove(pers);
	}

	public ArrayList<Personnage> getEquipe() {
		return equipe;
	}

	/**
	 * Vérifie si un personnage donné appartient à l'équipe
	 * 
	 * @param pers
	 *            Le personnage à vérifier l'appartenance
	 * @return Si le personnage appartient ou non à l'équipe
	 */
	public boolean appartient(Personnage pers) {
		for (Personnage p : equipe)
			if (pers == p)
				return true;
		return false;
	}

	public boolean sontMorts(Ile ile) {
		ArrayList<Personnage> tempo = new ArrayList<>();
		// copie
		for (Personnage p : equipe) {
			tempo.add(p);
		}
		for (Personnage p : tempo) {
			if (p.energie <= 0) {
				removePerso(p);
			}
		}
		if (equipe.isEmpty()) {
			return true;
		} else
			return false;
	}

}
