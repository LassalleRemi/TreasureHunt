
public abstract class Personnage extends Parcelle {
	int x; // x et y de la position du personnage
	int y;

	public Personnage(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}

	public Personnage() {
		estTraversable = false;
	}

	public void placer(int x, int y, Personnage p, Ile ile) {
		ile.positions[x][y] = 9;
		ile.setObjet(x, y, (Parcelle) p);
	}

	boolean deplacer(int x, int y, int dX, int dY, Ile ile) {
		if (ile.getObjet(dX, dY) instanceof Navire) {
			Navire bateau = (Navire) (ile.getObjet(dX, dY));
			bateau.addPerso((Personnage) (ile.getObjet(x, y)));
			ile.setObjet(x, y, new Parcelle(true));
			ile.positions[x][y] = 1; // du sable
			return false;
		} else if (ile.getObjet(dX, dY).estTraversable) {
			ile.setObjet(dX, dY, ile.getObjet(x, y));
			ile.setObjet(x, y, new Parcelle(true));
			ile.positions[dX][dY] = ile.positions[x][y];
			ile.positions[x][y] = 1; // du sable
			return true;
		} else if (ile.getObjet(dX, dY) instanceof Rocher) {
			Explorateur p = (Explorateur) ile.getObjet(x, y);
			p.soulever(dX, dY, ile);
			return false;
		}
		return false;
	}

}
