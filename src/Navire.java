import java.util.ArrayList;
import java.util.Random;

public class Navire extends Parcelle {
	Equipe team;
	private ArrayList<Personnage> persos = new ArrayList<>();
	int xN;
	int yN;

	/**
	 * @param x
	 * @param y
	 * @param team
	 */
	public Navire(int x, int y, Equipe team) {
		this.team = team;
		super.estTraversable = true;
		xN = x;
		yN = y;
	}

	/**
	 * @param pers
	 */
	public void addPerso(Personnage pers) {
		persos.add(pers);
	}

	/**
	 * @param pers
	 * @param ile
	 */
	public void sortirPerso(Personnage pers, Ile ile) {
		Random r = new Random();
		int choix;
		boolean stop = true;
		do {
			choix = r.nextInt(4);
			switch (choix) {
			case 0:
				if (ile.getObjet(xN + 1, yN).estTraversable) {
					pers.placer(xN + 1, yN, pers, ile);
					stop = true;
				} else
					stop = false;
				break;
			case 1:
				if (ile.getObjet(xN - 1, yN).estTraversable) {
					pers.placer(xN - 1, yN, pers, ile);
					stop = true;
				} else
					stop = false;
				break;
			case 2:
				if (ile.getObjet(xN, yN + 1).estTraversable) {
					pers.placer(xN, yN + 1, pers, ile);
					stop = true;
				} else
					stop = false;
				break;
			case 3:
				if (ile.getObjet(xN, yN - 1).estTraversable) {
					pers.placer(xN, yN - 1, pers, ile);
					stop = true;
				} else
					stop = false;
				break;
			}

		} while (!stop);
		removePerso(pers);
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
		String s = "Le navire contient : ";
		for (Personnage p : persos)
			s = s + p + "\n";
		return s;
	}
}
