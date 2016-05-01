
public abstract class Personnage extends Parcelle {
	Equipe equipe;
	int energie = 1;
	boolean tresor = false;

	/**
	 * @param equipe
	 */
	public Personnage(Equipe equipe) { // a la creation d'un perso il est mit
										// dans son navire
		this.equipe = equipe;
		equipe.addPerso(this); // on ajoute le perso a l'équipe
		equipe.getNavire().addPerso(this); // on ajoute le perso a son navire
		estTraversable = false;
	}

	/**
	 * @param x
	 * @param y
	 * @param p
	 * @param ile
	 */
	public void placer(int x, int y, Personnage p, Ile ile) {
		ile.setObjet(x, y, (Parcelle) p);
		this.x = x;
		this.y = y;
		equipe.visible(this, ile);
	}

	/**
	 * Déplace un persoonage à des coordonnées données
	 * 
	 * @param x
	 * @param y
	 * @param dX
	 * @param dY
	 * @param ile
	 * @return
	 */
	boolean action(int dX, int dY, Ile ile) {
		if (ile.getObjet(dX, dY) instanceof Navire) {
			Navire bateau = (Navire) (ile.getObjet(dX, dY));
			Personnage perso = (Personnage) (ile.getObjet(x, y));
			if (bateau.team.appartient(perso)) {
				energie = energie - 1;
				bateau.addPerso(perso);
				equipe.removeVisible(this);
				ile.positions[x][y] = 1; // du sable
				ile.setObjet(x, y, new Parcelle(true));
				if (tresor == true) {
					System.out.println("Bravo, l'équipe " + equipe + " gagne la partie");
					equipe.getNavire().tresor = true;
				}
			} else
				System.out.println("Ce n'est pas votre bateau");
			return false;
		} else if (ile.getObjet(dX, dY).estTraversable) {
			energie = energie - 1;
			ile.setObjet(dX, dY, ile.getObjet(x, y));
			ile.setObjet(x, y, new Parcelle(true));
			ile.positions[dX][dY] = ile.positions[x][y];
			equipe.removeVisible(this);
			ile.positions[x][y] = 1; // du sable
			this.x = dX;
			this.y = dY;
			equipe.visible(this, ile);
			return true;
		} else
			return false;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public String toString() {
		return "Un personnage de type : " + this.getClass().getName() + ", de l'équipe " + equipe + ", avec " + energie
				+ " d'énergie";
	}

	public int[][] surbrillance(Ile ile) {
		int[][] surbrillanceVue = new int[ile.taille][ile.taille];
		// on copie la vue de l'équipe
		for (int i = 0; i < ile.taille; i++)
			for (int j = 0; j < ile.taille; j++)
				surbrillanceVue[i][j] = equipe.vue[i][j];
		if (this instanceof Explorateur) {
			surbrillance_explorateur(surbrillanceVue, ile);
		} else if (this instanceof Voleur) {
			surbrillance_voleur(surbrillanceVue, ile);
		}
		return surbrillanceVue;
	}

	private void surbrillance_explorateur(int[][] surbrillance_vue, Ile ile) {
		int[][] autour = { { x + 1, y }, { x - 1, y }, { x, y + 1 }, { x, y - 1 } };
		for (int i = 0; i < autour.length; i++) {
			if (ile.getObjet(autour[i][0], autour[i][1]) instanceof Rocher)
				surbrillance_vue[autour[i][0]][autour[i][1]] = 5;
			else if (ile.getObjet(autour[i][0], autour[i][1]) instanceof Navire) {
				Navire n = (Navire) (ile.getObjet(autour[i][0], autour[i][1]));
				if (n.team.appartient(this))
					surbrillance_vue[autour[i][0]][autour[i][1]] = ile.positions[autour[i][0]][autour[i][1]] + 1;
			} else if (ile.positions[autour[i][0]][autour[i][1]] == 1)
				surbrillance_vue[autour[i][0]][autour[i][1]] = 2;
		}
	}

	private void surbrillance_voleur(int[][] surbrillance_vue, Ile ile) {
		int[][] autour = { { x + 1, y }, { x - 1, y }, { x, y + 1 }, { x, y - 1 }, { x + 1, y + 1 }, { x - 1, y - 1 },
				{ x + 1, y - 1 }, { x - 1, y + 1 } };
		for (int i = 0; i < autour.length; i++) {
			if (ile.getObjet(autour[i][0], autour[i][1]) instanceof Navire) {
				Navire n = (Navire) (ile.getObjet(autour[i][0], autour[i][1]));
				if (n.team.appartient(this))
					surbrillance_vue[autour[i][0]][autour[i][1]] = ile.positions[autour[i][0]][autour[i][1]] + 1;
			} else if (ile.positions[autour[i][0]][autour[i][1]] == 1)
				surbrillance_vue[autour[i][0]][autour[i][1]] = 2;
			else if (ile.getObjet(autour[i][0], autour[i][1]) instanceof Explorateur) {
				Personnage p = (Personnage) (ile.getObjet(autour[i][0], autour[i][1]));
				if (!p.equipe.appartient(this))
					surbrillance_vue[autour[i][0]][autour[i][1]] = ile.positions[autour[i][0]][autour[i][1]] + 1;
			}
		}
	}

	public boolean estMort() {
		if (energie <= 0) {
			return true;

		} else
			return false;
	}

}
