
import java.util.Random;

public class Ile {
	SuperPlateau plateau;
	Parcelle[][] ile;
	int[][] positions; // stock les int pour l'affichage
	int taille;
	int saveRocher;
	String[] imgs = { "images/sand.png", "images/water.png", "images/rocher.png", "images/ship.png",
			"images/ship2.png", "images/chest.png", "images/key.png" };

	public Ile(int taille, int pourcentRocher) {
		this.taille = taille;
		positions = new int[taille][taille];
		ile = new Parcelle[taille][taille];

		Random r = new Random();
		int x, y;
		int nbreSable = (taille - 2) * (taille - 2) - 2;
		int nbreRocher = (int) (nbreSable * pourcentRocher / 100);
		int yNavire1, yNavire2;
		this.saveRocher = nbreRocher; // on enregistre le nbre de rochers
		do {
			// on place le sable et l'eau
			for (int i = 0; i < taille; i++)
				for (int j = 0; j < taille; j++)
					if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
						positions[i][j] = 2; // de leau
						ile[i][j] = new Parcelle(false);
					} else {
						positions[i][j] = 1; // du sable
						ile[i][j] = new Parcelle(true);
					}
			// on place les navires
			yNavire1 = (r.nextInt(taille - 2) + 1);
			yNavire2 = (r.nextInt(taille - 2) + 1);
			positions[1][yNavire1] = 4;
			ile[1][yNavire1] = new Parcelle(false);
			positions[taille - 2][yNavire2] = 5;
			ile[taille - 2][yNavire2] = new Parcelle(false);
			while (nbreRocher != 0) {
				x = (int) (r.nextInt(taille - 2)) + 1;
				y = (int) (r.nextInt(taille - 2)) + 1;
				if (positions[x][y] == 1 && !(x == 2 && y == yNavire1) && !(x == 1 && y == yNavire1 + 1)
						&& !(x == 1 && y == yNavire1 - 1) && !(x == taille - 3 && y == yNavire2)
						&& !(x == taille - 2 && y == yNavire2 + 1) && !(x == taille - 2 && y == yNavire2 - 1)) {
					positions[x][y] = 3; // on place un rocher
					ile[x][y] = new Rocher(); // on cree l objet rocher
					if (this.saveRocher == nbreRocher) {
						((Rocher) ile[x][y]).cle = true;
						positions[x][y] = 7; // on place une cle
						System.out.println(x + " " + y);
					} else if (this.saveRocher - 1 == nbreRocher) {
						((Rocher) ile[x][y]).coffre = true;
						positions[x][y] = 6; // on place une cle
						System.out.println(x + " " + y);
					}
					nbreRocher--;
				}

			}
			nbreRocher = this.saveRocher;
		} while (!rochersAccessible(1, yNavire1) || !rochersAccessible(taille - 2, yNavire2));
	}

	/**
	 * @param xN
	 * @param yN
	 * @return
	 */
	private boolean rochersAccessible(int xN, int yN) {
		// creation du tableau de test full 0 et un seul 1
		int[][] mondetempo = new int[taille][taille];
		for (int i = 0; i < taille; i++)
			for (int j = 0; j < taille; j++) {
				if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
					mondetempo[i][j] = 3;
				} else
					mondetempo[i][j] = 0;
			}
		mondetempo[xN][yN] = 1; // on place le navire de depart
		ile[xN][yN].estTraversable = true;

		boolean again = true;
		int nbreZero = 0;
		int nbreZero2 = 0;
		do {
			for (int i = 1; i < taille - 1; i++)
				for (int j = 1; j < taille - 1; j++)
					if (mondetempo[i][j] == 1) {
						if (ile[i + 1][j].estTraversable) {
							mondetempo[i + 1][j] = 1;
						} else
							mondetempo[i + 1][j] = 2;
						if (ile[i - 1][j].estTraversable) {
							mondetempo[i - 1][j] = 1;
						} else
							mondetempo[i - 1][j] = 2;
						if (ile[i][j + 1].estTraversable) {
							mondetempo[i][j + 1] = 1;
						} else
							mondetempo[i][j + 1] = 2;
						if (ile[i][j - 1].estTraversable) {
							mondetempo[i][j - 1] = 1;
						} else
							mondetempo[i][j - 1] = 2;
					}

			nbreZero = nbreZero(mondetempo);
			if (nbreZero == nbreZero2 || nbreZero == 0)
				again = false;
			nbreZero2 = nbreZero;
		} while (again);

		ile[xN][yN].estTraversable = false; // on remet comme avant
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
