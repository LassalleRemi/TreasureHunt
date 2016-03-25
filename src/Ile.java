
import java.util.Random;

public class Ile {
	SuperPlateau plateau;
	Parcelle[][] ile;
	int[][] positions; // stock les int pour l'affichage
	int taille;
	int saveRocher;
	String[] imgs = { "images/sand.png", "images/water.png", "images/rocher.png", "images/ship.png", "images/ship2.png",
			"images/surbrillance.png", "images/surbrillanceRocher.png", "images/explorateur.png" };

	/**
	 * Constructeur de l'ile, de dimension taille x taille, avec un pourcentage
	 * de rochers.
	 * 
	 * @param taille
	 *            Dimension de la fenêtre.
	 * @param pourcentRocher
	 *            Pourcentage de rochers à générer.
	 */
	public Ile(int taille, int pourcentRocher) {
		Random r = new Random();
		this.taille = taille;
		positions = new int[taille][taille];
		ile = new Parcelle[taille][taille];
		int yNavire1, yNavire2;
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
			placeNavires(yNavire1, yNavire2);
			placeRochers(pourcentRocher, yNavire1, yNavire2);
		} while (!rochersAccessible(1, yNavire1) || !rochersAccessible(taille - 2, yNavire2));
	}

	void placeNavires(int yNavire1, int yNavire2){
		positions[1][yNavire1] = 4;
		ile[1][yNavire1] = new Navire(1, yNavire1);
		positions[taille - 2][yNavire2] = 5;
		ile[taille - 2][yNavire2] = new Navire(taille - 2, yNavire2);
	}
	void placeRochers(int pourcentRocher, int yNavire1, int yNavire2) {
		Random r = new Random();
		int x, y;
		int nbreSable = (taille - 2) * (taille - 2) - 2;
		int nbreRocher = (int) (nbreSable * pourcentRocher / 100);
		this.saveRocher = nbreRocher; // on enregistre le nbre de rochers
		while (nbreRocher != 0) {
			x = (int) (r.nextInt(taille - 2)) + 1;
			y = (int) (r.nextInt(taille - 2)) + 1;
			if (positions[x][y] == 1 && !(x == 2 && y == yNavire1) && !(x == 1 && y == yNavire1 + 1)
					&& !(x == 1 && y == yNavire1 - 1) && !(x == taille - 3 && y == yNavire2)
					&& !(x == taille - 2 && y == yNavire2 + 1) && !(x == taille - 2 && y == yNavire2 - 1)) {
				positions[x][y] = 3; // on place un rocher
				ile[x][y] = new Rocher(); // on cree l objet rocher
				if (this.saveRocher == nbreRocher) {
					ile[x][y].cle = true;
					positions[x][y] = 3;
					System.out.println("Cle : " + x + " " + y);
				} else if (this.saveRocher - 1 == nbreRocher) {
					ile[x][y].coffre = true;
					positions[x][y] = 3;
					System.out.println("Coffre : " + x + " " + y);
				}
				nbreRocher--;
			}

		}
		nbreRocher = this.saveRocher;
	}

	/**
	 * Méthode permettant de vérifier l'accessibilité des rochers à partir d'un
	 * point donné.
	 * 
	 * @param xN
	 *            L'abscisse à partir duquel tous les rochers vont être
	 *            vérifiés.
	 * @param yN
	 *            L'ordonnée à partir de laquelle tous les rochers vont être
	 *            vérifiés.
	 * @return Un boolean qui retourne true si tous les rochers sont accessibles
	 *         à partir du xN et yN.
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

	/**
	 * Méthode retournant le nombre de zéros dans le tableau donné.
	 * 
	 * @param monde
	 *            Tableau de int à 2 dimensions.
	 * @return Le nombre de zéros trouvé.
	 */
	private int nbreZero(int[][] monde) {
		int nbre = 0;
		for (int[] m : monde)
			for (int e : m)
				if (e == 0)
					nbre++;
		return nbre;
	}

	/**
	 * Méthode qui affiche dans une fenetre le jeu.
	 */
	public void afficher() {
		plateau = new SuperPlateau(imgs, taille);
		plateau.setJeu(positions);
		plateau.affichage();
	}

	void surbrillance(int x, int y, int type) {
		if (type == 1) {
			if (positions[x][y + 1] == 0) {
				positions[x][y + 1] = 5;
			} else if (positions[x][y - 1] == 0) {
				positions[x][y - 1] = 5;
			} else if (positions[x - 1][y] == 0) {
				positions[x - 1][y] = 5;
			} else if (positions[x + 1][y] == 0) {
				positions[x + 1][y] = 5;
			} else if (positions[x][y + 1] == 2) {
				positions[x][y + 1] = 6;
			} else if (positions[x][y - 1] == 2) {
				positions[x][y - 1] = 6;
			} else if (positions[x - 1][y] == 2) {
				positions[x - 1][y] = 6;
			} else if (positions[x + 1][y] == 2) {
				positions[x + 1][y] = 6;
			}
		}
	}
	
	int[] getCoord(int n){
		int[] coords = null;
		for(int x=0; x<taille; x++)
			for(int y=0; y<taille; y++)
				if(positions[x][y] == n)
					coords = new int[]{x, y};
		return coords;
	}

}
