
public class Explorateur extends Personnage {
	boolean cle = false;
	boolean tresor = false;
	
	/**
	 * @param team
	 */
	public Explorateur(Equipe team){
		super(team);
	}

	/* (non-Javadoc)
	 * @see Personnage#placer(int, int, Personnage, Ile)
	 */
	public void placer(int x, int y, Personnage p, Ile ile) {
		super.placer(x, y, p, ile);
		ile.positions[x][y] = 8;
	}

	/* (non-Javadoc)
	 * @see Personnage#deplacer(int, int, int, int, Ile)
	 */
	boolean deplacer(int x, int y, int dX, int dY, Ile ile) {
		if (Math.abs(dX - x) + Math.abs(dY - y) == 1) {
			if (!super.deplacer(x, y, dX, dY, ile)) {
				if (ile.getObjet(dX, dY) instanceof Rocher) {
					Explorateur p = (Explorateur) ile.getObjet(x, y);
					p.soulever(dX, dY, ile);
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Souleve un Rocher
	 * @param x
	 * @param y
	 * @param ile
	 */
	void soulever(int x, int y, Ile ile) {
		Rocher r = (Rocher) ile.getObjet(x, y);
		if (r.cle) {
			cle = true;
			r.cle = false;
			System.out.println("Clé découvete!");
		} else if (r.coffre && r.tresor) {
			System.out.println("Coffre découvert avec trésor!");
			ile.positions[x][y] = 12;
			if (cle) {
				r.tresor = false;
				tresor = true;
				System.out.println("trésor!");
				ile.positions[x][y] = 13;
			}
		}else if (r.coffre && !r.tresor) {
			System.out.println("Coffre découvert sans trésor!");
			ile.positions[x][y] = 13;
		} else
			System.out.println("Il n'y a rien dedans");
	}

}
