import java.util.Random;

public class Ile {
	SuperPlateau plateau;
	Parcelle[][] ile;
	int[][] positions; // stock les int pour l'affichage
	int taille = 20;
	String[] imgs = { "images/sand.png", "images/water.png", "images/ship.png", "images/rocher.png" };

	public Ile() {
		positions = new int[taille][taille];
		// on place le sable et l'eau
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
					positions[i][j] = 2;
				} else {
					positions[i][j] = 1;
				}
			}
		}
		// on place les navires
		positions[1][1] = 3;
		positions[taille - 2][taille - 2] = 3;

		Random r = new Random();
		int x, y;
		int nbreSable = (taille - 2) * (taille - 2) - 2;
		int nbreRocher = (int) (nbreSable * 0.10);
		while (nbreRocher != 0) {
			x = (int) (r.nextInt(taille - 2)) + 1;
			y = (int) (r.nextInt(taille - 2)) + 1;
			if ((x != 1 && y != 1) && (x != taille - 2 && y != taille - 2) && positions[x][y] != 4) {
				positions[x][y] = 4; // on place un rocher
				nbreRocher--;
			}
		}

		ile = new Parcelle[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				ile[i][j] = new Parcelle();
				if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
					ile[i][j].elem = 0;
				} else if (i == 1 || i == taille - 2 || j == 1 || j == taille - 2) {
					ile[i][j].elem = 1;
				}
			}
		}
	}

	public void afficher() {
		plateau = new SuperPlateau(imgs, taille);
		plateau.setJeu(positions);
		plateau.affichage();
	}

}
