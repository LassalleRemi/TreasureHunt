
import java.util.Random;

public class Ile {
	static int numEquipe = 1;
	Navire n1 = null, n2 = null;
	SuperPlateau plateau;
	private Parcelle[][] ile;
	int[][] positions; // stock les int pour l'affichage
	int taille;
	int saveRocher;
	String[] imgs = { "images/sand.png", "images/water.png", "images/rocher.png", "images/ship.png", "images/ship2.png",
			"images/surbrillance_sand.png", "images/surbrillance_rocher.png", "images/explorateur.png",
			"images/voleur.png", "images/surbrillance_navire1.png", "images/surbrillance_navire2.png",
			"images/chest.png", "images/chest_open.png" };

	/**
	 * Constructeur de l'ile, de dimension taille x taille, avec un pourcentage
	 * de rochers.
	 * 
	 * @param taille
	 * @param pourcentRocher
	 *            Pourcentage de rochers à générer.
	 * @param team
	 *            Objet de la première équipe
	 * @param team2
	 *            Objet de la seconde équipe
	 */
	public Ile(int taille, int pourcentRocher, Equipe team, Equipe team2) {
		plateau = new SuperPlateau(imgs, taille);
		this.taille = taille;
		positions = new int[taille][taille];
		ile = new Parcelle[taille][taille];
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
			n1 = placeNavire(team);
			n2 = placeNavire(team2);
			placeRochers(pourcentRocher, n1, n2);
		} while (!rochersAccessible(1, n1.yN) || !rochersAccessible(taille - 2, n2.yN));
	}

	/**
	 * Place le navire d'une équipe donnée
	 * 
	 * @param team
	 *            L'équipe a qui le navire va appartenir
	 * @return La navire qui vient d'être placé
	 */
	Navire placeNavire(Equipe team) {
		Random r = new Random();
		int yNavire = (r.nextInt(taille - 4) + 2);
		if (numEquipe == 1) {
			ile[1][yNavire] = new Navire(1, yNavire, team);
			positions[1][yNavire] = 4;
			numEquipe++;
			return (Navire) ile[1][yNavire];
		} else if (numEquipe == 2) {
			ile[taille - 2][yNavire] = new Navire(taille - 2, yNavire, team);
			positions[taille - 2][yNavire] = 5;
			numEquipe = 1;
			return (Navire) ile[taille - 2][yNavire];
		}
		return null;
	}

	/**
	 * Place les rochers en fonction du pourcentage de rochers et des navires
	 * 
	 * @param pourcentRocher
	 *            Pourcentage de rochers à générer.
	 * @param n1
	 *            Objet du premier Navire
	 * @param n2
	 *            Objet du second Navire
	 */
	void placeRochers(int pourcentRocher, Navire n1, Navire n2) {
		Random r = new Random();
		int x, y;
		int nbreSable = (taille - 2) * (taille - 2) - 2;
		int nbreRocher = (int) (nbreSable * pourcentRocher / 100);
		this.saveRocher = nbreRocher; // on enregistre le nbre de rochers
		while (nbreRocher != 0) {
			x = (int) (r.nextInt(taille - 2)) + 1;
			y = (int) (r.nextInt(taille - 2)) + 1;
			if (positions[x][y] == 1 && !(x == 2 && y == n1.yN) && !(x == 1 && y == n1.yN + 1)
					&& !(x == 1 && y == n1.yN - 1) && !(x == taille - 3 && y == n2.yN)
					&& !(x == taille - 2 && y == n2.yN + 1) && !(x == taille - 2 && y == n2.yN - 1)) {

				positions[x][y] = 3; // on place un rocher
				if (this.saveRocher == nbreRocher) {
					ile[x][y] = new Rocher(true, false, false);
					System.out.println("Cle : " + x + " " + y);
				} else if (this.saveRocher - 1 == nbreRocher) {
					ile[x][y] = new Rocher(false, true, true);
					System.out.println("Coffre : " + x + " " + y);
				} else
					ile[x][y] = new Rocher(); // on cree l objet rocher
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
		plateau.setJeu(positions);
		plateau.affichage();
	}

	/*
	 * void surbrillance(int x, int y, int type) {
	 * 
	 * if (type == 1) { if (positions[x][y + 1] == 1) { positions[x][y + 1] = 6;
	 * } else if (positions[x][y - 1] == 1) { positions[x][y - 1] = 6; } else if
	 * (positions[x - 1][y] == 1) { positions[x - 1][y] = 6; } else if
	 * (positions[x + 1][y] == 1) { positions[x + 1][y] = 6; } else if
	 * (positions[x][y + 1] == 3) { positions[x][y + 1] = 7; } else if
	 * (positions[x][y - 1] == 3) { positions[x][y - 1] = 7; } else if
	 * (positions[x - 1][y] == 3) { positions[x - 1][y] = 7; } else if
	 * (positions[x + 1][y] == 3) { positions[x + 1][y] = 7; } }
	 * affichage(positions_surbrillance) }
	 */

	/**
	 * Retourne l'objet aux coordonnees passees en parametre
	 * 
	 * @param x
	 * @param y
	 * @return L'objet correspondant
	 */
	Parcelle getObjet(int x, int y) {
		return ile[x][y];
	}

	/**
	 * Met l'objet aux coordonnees passees en parametre
	 * 
	 * @param x
	 * @param y
	 * @param obj
	 */
	void setObjet(int x, int y, Parcelle obj) {
		ile[x][y] = obj;
	}

}
