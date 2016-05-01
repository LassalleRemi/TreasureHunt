import java.util.ArrayList;

public class Equipe {
	private ArrayList<Personnage> equipe = new ArrayList<>();
	String nom;
	private Navire navire;
	int[][] vue;

	public Equipe(String nom) {
		this.nom = nom;
	}

	public Navire getNavire() {
		return navire;
	}

	public void setNavire(Navire navire, Ile ile) {
		this.navire = navire;
		initVue(ile.taille);
		visible(navire, ile);
	}

	private void initVue(int taille) {
		vue = new int[taille][taille];
		for (int i = 0; i < taille; i++)
			for (int j = 0; j < taille; j++)
				if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
					vue[i][j] = 3; // de leau normale
				} else {
					vue[i][j] = 16; // du sable sombre
				}
	}

	public void visible(Parcelle p, Ile ile) {
		if (p instanceof Navire) {
			vue[p.x][p.y] = ile.positions[p.x][p.y]; // on affiche le navire
			if (ile.getObjet(p.x + 1, p.y).estTraversable)
				vue[p.x + 1][p.y] = 1; // on affiche le sable
			if (ile.getObjet(p.x - 1, p.y).estTraversable)
				vue[p.x - 1][p.y] = 1; // on affiche le sable
			if (ile.getObjet(p.x, p.y + 1).estTraversable)
				vue[p.x][p.y + 1] = 1; // on affiche le sable
			if (ile.getObjet(p.x, p.y - 1).estTraversable)
				vue[p.x][p.y - 1] = 1; // on affiche le sable
		} else if (p instanceof Explorateur) {
			vue[p.x][p.y] = 10; // on affiche l'explorateur
			vue[p.x + 1][p.y] = ile.positions[p.x + 1][p.y];
			vue[p.x - 1][p.y] = ile.positions[p.x - 1][p.y];
			vue[p.x][p.y + 1] = ile.positions[p.x][p.y + 1];
			vue[p.x][p.y - 1] = ile.positions[p.x][p.y - 1];
		} else if (p instanceof Voleur) {
			vue[p.x][p.y] = 12; // on affiche le voleur
			vue[p.x + 1][p.y] = ile.positions[p.x + 1][p.y];
			vue[p.x - 1][p.y] = ile.positions[p.x - 1][p.y];
			vue[p.x][p.y + 1] = ile.positions[p.x][p.y + 1];
			vue[p.x][p.y - 1] = ile.positions[p.x][p.y - 1];
			vue[p.x + 1][p.y + 1] = ile.positions[p.x + 1][p.y + 1];
			vue[p.x - 1][p.y - 1] = ile.positions[p.x - 1][p.y - 1];
			vue[p.x + 1][p.y - 1] = ile.positions[p.x + 1][p.y - 1];
			vue[p.x - 1][p.y + 1] = ile.positions[p.x - 1][p.y + 1];
		}

	}

	// synchronisation avec positions
	public void vueCommune(Ile ile) {
		for (int i = 0; i < vue.length; i++)
			for (int j = 0; j < vue.length; j++)
				if (vue[i][j] != 16 && vue[i][j] != 15 && vue[i][j] != 14) {
					vue[i][j] = ile.positions[i][j];
				}
	}

	public void removeVisible(Parcelle p) {
		vue[p.x][p.y] = 1; // on met du sable
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
	boolean appartient(Personnage pers) {
		for (Personnage p : equipe)
			if (pers == p)
				return true;
		return false;
	}

	public boolean sontMorts() {
		ArrayList<Personnage> tempo = new ArrayList<>();
		// copie
		for (Personnage p : equipe) {
			tempo.add(p);
		}
		for (Personnage p : tempo) {
			if (p.estMort()) {
				removePerso(p);
			}
		}
		if (equipe.isEmpty()) {
			return true;
		} else
			return false;
	}

}
