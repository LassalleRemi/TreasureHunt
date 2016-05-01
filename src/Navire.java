import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Navire extends Parcelle {
	Equipe team;
	private ArrayList<Personnage> persos = new ArrayList<>();
	boolean tresor = false;

	/**
	 * @param x
	 * @param y
	 * @param team
	 */
	public Navire(int x, int y, Equipe team) {
		this.team = team;
		super.estTraversable = true;
		this.x = x;
		this.y = y;
	}

	/**
	 * @param pers
	 */
	public void addPerso(Personnage pers) {
		// f pers a un tresor finit
		persos.add(pers);
		pers.energie += 0;
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
			ile.plateau.println(
					"Veuillez choisir une case autour du navire pour débarquer ou annuler pour ne pas débarquer");
			do {
				event = ile.plateau.waitEvent(10000);
				if (event instanceof MouseEvent) {
					y = ile.plateau.getX((MouseEvent) event);
					x = ile.plateau.getY((MouseEvent) event);
					System.out.println("bateau: " + x + " " + y + ", clique: " + x + " " + y);
				}
			} while (event == null);
			if (ile.getObjet(x, y).estTraversable && (this.x == x + 1 && this.y == y) ^ (this.x == x - 1 && this.y == y)
					^ (this.x == x && this.y == y + 1) ^ (this.x == x && this.y == y - 1)) {
				perso.placer(x, y, perso, ile);
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
}
