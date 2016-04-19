
public abstract class Personnage extends Parcelle {
	Equipe team;
	int x;
	int y;

	/**
	 * @param team
	 */
	public Personnage(Equipe team) {
		this.team = team;
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
	boolean deplacer(int x, int y, int dX, int dY, Ile ile) {
		if (ile.getObjet(dX, dY) instanceof Navire) {
			Navire bateau = (Navire) (ile.getObjet(dX, dY));
			Equipe team = bateau.team;
			Personnage perso = (Personnage) (ile.getObjet(x, y));
			if (team.appartient(perso)) {
				bateau.addPerso(perso);
				this.x = dX;
				this.y = dY;
				ile.setObjet(x, y, new Parcelle(true));
				ile.positions[x][y] = 1; // du sable
			} else
				System.out.println("Ce n'est pas votre bateau");
			return false;
		} else if (ile.getObjet(dX, dY).estTraversable) {
			ile.setObjet(dX, dY, ile.getObjet(x, y));
			ile.setObjet(x, y, new Parcelle(true));
			ile.positions[dX][dY] = ile.positions[x][y];
			ile.positions[x][y] = 1; // du sable
			this.x = dX;
			this.y = dY;
			return true;
		} else
			return false;
	}

	public String toString() {
		return "Un personnage de type : " + this.getClass().getName() + ", de l'équipe " + team;
	}

}
