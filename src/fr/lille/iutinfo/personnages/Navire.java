package fr.lille.iutinfo.personnages;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import fr.lille.iutinfo.terrain.Coordonnees;
import fr.lille.iutinfo.terrain.Ile;

public class Navire extends Parcelle {
	Equipe equipe;
	private ArrayList<Personnage> persos = new ArrayList<>();
	public boolean tresor = false;

	/**
	 * @param x
	 * @param y
	 * @param team
	 */
	public Navire(int x, int y, Equipe equipe) {
		this.equipe = equipe;
		super.estTraversable = true;
		coords.setX(x);
		coords.setY(y);
	}

	/**
	 * @param pers
	 */
	public void addPerso(Personnage pers) {
		persos.add(pers);
		pers.energie += 10;
	}

	/**
	 * @param perso
	 * @param ile
	 */
	public void sortirPerso(Personnage perso, Ile ile) {
		InputEvent event;
		int x = 0, y = 0;
		boolean arret = false;
		do {
			do {
				event = ile.plateau.waitEvent(10000);
				if (event instanceof MouseEvent) {
					y = ile.plateau.getX((MouseEvent) event);
					x = ile.plateau.getY((MouseEvent) event);
				}
			} while (event == null);
			if (ile.getObjet(new Coordonnees(x, y)).estTraversable
					&& this.faitPartiDuChampDeVision(new Coordonnees(x, y))) {
				perso.placer(x, y, ile);
				removePerso(perso);
				arret = true;
			}
		} while (!arret);
	}

	/**
	 * @param pers
	 */
	public void removePerso(Personnage pers) {
		persos.remove(pers);
	}

	/**
	 * @return La liste des personnages dans le Navire
	 */
	public ArrayList<Personnage> getPersos() {
		return persos;
	}

	boolean estPresent(Personnage p) {
		return persos.contains(p);
	}

	public String toString() {
		String s = "Le navire contient : \n\t";
		for (Personnage p : persos)
			s = s + p + "\n\t";
		return s;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public Coordonnees[] champDeVision() {
		Coordonnees coord[] = new Coordonnees[] { new Coordonnees(coords.getX() + 1, coords.getY()),
				new Coordonnees(coords.getX() - 1, coords.getY()), new Coordonnees(coords.getX(), coords.getY() + 1),
				new Coordonnees(coords.getX(), coords.getY() - 1) };
		return coord;
	}
}
