package fr.lille.iutinfo.terrain;

import java.util.Random;

import fr.lille.iutinfo.affichage.Image;
import fr.lille.iutinfo.personnages.Buisson;
import fr.lille.iutinfo.personnages.Equipe;
import fr.lille.iutinfo.personnages.Navire;
import fr.lille.iutinfo.personnages.Parcelle;
import fr.lille.iutinfo.personnages.Rocher;

public class Ile {
	static int numEquipe = 1;
	public SuperPlateau plateau;
	private Parcelle[][] ile;
	public int[][] positions; // stock les int pour l'affichage
	public int taille;
	private int saveRocher;
	String[] imgs = Image.tousChemins();

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
		Navire n1 = null, n2 = null;
		this.taille = taille;
		positions = new int[taille][taille];
		ile = new Parcelle[taille][taille];
		do {
			// on place le sable et l'eau
			for (int i = 0; i < taille; i++)
				for (int j = 0; j < taille; j++)
					if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
						positions[i][j] = Image.EAU.monOrdinal(); // de leau
						ile[i][j] = new Parcelle(false, new Coordonnees(i, j));
					} else {
						positions[i][j] = Image.SABLE.monOrdinal(); // du sable
						ile[i][j] = new Parcelle(true, new Coordonnees(i, j));
					}
			n1 = placeNavire(team);
			team.setNavire(n1, this);
			n2 = placeNavire(team2);
			team2.setNavire(n2, this);
			placeRochers(pourcentRocher, n1, n2);
		} while (!rochersAccessible(1, n1.coords.getY()) || !rochersAccessible(taille - 2, n2.coords.getY()));
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
			positions[1][yNavire] = Image.NAVIRE1.monOrdinal();
			numEquipe++;
			return (Navire) ile[1][yNavire];
		} else if (numEquipe == 2) {
			ile[taille - 2][yNavire] = new Navire(taille - 2, yNavire, team);
			positions[taille - 2][yNavire] = Image.NAVIRE2.monOrdinal();
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
			if (positions[x][y] == Image.SABLE.monOrdinal() && !(x == 2 && y == n1.coords.getY())
					&& !(x == 1 && y == n1.coords.getY() + 1) && !(x == 1 && y == n1.coords.getY() - 1)
					&& !(x == taille - 3 && y == n2.coords.getY()) && !(x == taille - 2 && y == n2.coords.getY() + 1)
					&& !(x == taille - 2 && y == n2.coords.getY() - 1)) {
				positions[x][y] = Image.ROCHER.monOrdinal();
				if (this.saveRocher == nbreRocher) {
					ile[x][y] = new Rocher(new Coordonnees(x, y), true, false, false);
					System.out.println("Cle : " + x + " " + y);
				} else if (this.saveRocher - 1 == nbreRocher) {
					ile[x][y] = new Rocher(new Coordonnees(x, y), false, true, true);
					System.out.println("Coffre : " + x + " " + y);
				} else if (this.saveRocher - 2 == nbreRocher) {
					ile[x][y] = new Buisson(new Coordonnees(x, y), false, true);
					positions[x][y] = Image.BUISSON.monOrdinal();
				}else if (this.saveRocher - 3 == nbreRocher) {
					ile[x][y] = new Buisson(new Coordonnees(x, y), true, false);
					positions[x][y] = Image.BUISSON.monOrdinal();
				}else
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

	public void afficher(int[][] vue) {
		plateau.setJeu(vue);
		plateau.affichage();
	}

	/**
	 * Retourne l'objet aux coordonnees passees en parametre
	 * 
	 * @param x
	 * @param y
	 * @return L'objet correspondant
	 */
	public Parcelle getObjet(Coordonnees coords) {
		return ile[coords.getX()][coords.getY()];
	}

	/**
	 * Met l'objet aux coordonnees passees en parametre
	 * 
	 * @param x
	 * @param y
	 * @param obj
	 */
	public void setObjet(Coordonnees coords, Parcelle obj) {
		ile[coords.getX()][coords.getY()] = obj;
	}

	// supprime un element de positions
	public void removePosition(Coordonnees coords) {
		positions[coords.getX()][coords.getY()] = Image.SABLE.monOrdinal();
	}

	// supprime un objet d ile
	public void removeObjet(Coordonnees coords) {
		ile[coords.getX()][coords.getY()] = new Parcelle(true, coords);
	}

	public void afficherInfo(String string) {
		plateau.afficherInfo(string);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		plateau.cacherInfo();
	}

}
