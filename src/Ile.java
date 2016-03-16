
import java.util.Random;

public class Ile {
	SuperPlateau plateau;
	Parcelle[][] ile;
	int[][] positions; // stock les int pour l'affichage
	int taille = 20;
	int nbreRocher;
	String[] imgs = { "images/sand.png", "images/water.png", "images/rocher.png", "images/ship.png",
			"images/ship2.png" };

	public Ile() {
		positions = new int[taille][taille];
		ile = new Parcelle[taille][taille];

		Random r = new Random();
		int x, y;
		int nbreSable = (taille - 2) * (taille - 2) - 2;
		int nbreRocher = (int) (nbreSable * 0.3);
		this.nbreRocher = nbreRocher; // on enregistre le nbre de rochers
		do {
			// on place le sable et l'eau
			for (int i = 0; i < taille; i++)
				for (int j = 0; j < taille; j++)
					if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1)
						positions[i][j] = 2;
					else
						positions[i][j] = 1;
			// on place les navires
			positions[1][1] = 4;
			positions[taille - 2][taille - 2] = 5;
			while (nbreRocher != 0) {
				x = (int) (r.nextInt(taille - 2)) + 1;
				y = (int) (r.nextInt(taille - 2)) + 1;
				if (!((x == 1 && y == 1) || (x == taille - 2 && y == taille - 2) || positions[x][y] == 4 || positions[x][y] == 5)) {
					positions[x][y] = 3; // on place un rocher
					ile[x][y] = new Rocher(x, y);
					nbreRocher--;
				}
			}
			nbreRocher = this.nbreRocher;
		} while (!rochersAccessible());

	}

	private boolean rochersAccessible() {
		// creation du tableau de test full 0 et un seul 1
		int[][] mondetempo = new int[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
					mondetempo[i][j] = 3;
				} else {
					mondetempo[i][j] = 0;
				}
			}
		}
		mondetempo[1][1] = 1; // on place le navire de depart

		// on atteint maintenant le 1 du coin en le comparant a la case du vrai
		boolean again = true;
		int nbreZero = 0;
		int nbreZero2 = 0;
		do {
			for (int i = 1; i < taille - 1; i++)
				for (int j = 1; j < taille - 1; j++)
					if (mondetempo[i][j] == 1) {
						if (positions[i + 1][j] != 3 && positions[i + 1][j] != 2) {
							mondetempo[i + 1][j] = 1;
						} else if (positions[i + 1][j] == 3)
							mondetempo[i + 1][j] = 2;
						if (positions[i - 1][j] != 3 && positions[i - 1][j] != 2) {
							mondetempo[i - 1][j] = 1;
						} else if (positions[i - 1][j] == 3)
							mondetempo[i - 1][j] = 2;
						if (positions[i][j + 1] != 3 && positions[i][j + 1] != 2) {
							mondetempo[i][j + 1] = 1;
						} else if (positions[i][j + 1] == 3)
							mondetempo[i][j + 1] = 2;
						if (positions[i][j - 1] != 3 && positions[i][j - 1] != 2) {
							mondetempo[i][j - 1] = 1;
						} else if (positions[i][j - 1] == 3)
							mondetempo[i][j - 1] = 2;
					}
			nbreZero = nbreZero(mondetempo);
			if (nbreZero == nbreZero2 || nbreZero == 0)
				again = false;
			nbreZero2 = nbreZero;
		} while (again);

		if (nbreZero == 0)
			return true;
		else
			return false;
	}

	private int nbreZero(int[][] monde) {
		int nbre = 0;
		for (int[] m : monde)
			for (int e : m)
				if (e == 0)
					nbre++;
		return nbre;
	}

	public void afficher() {
		plateau = new SuperPlateau(imgs, taille);
		plateau.setJeu(positions);
		plateau.affichage();
	}

}
