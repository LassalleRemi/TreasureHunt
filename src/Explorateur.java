
public class Explorateur extends Personnage {
	boolean cle = false;
	// si l explorateur meurt lcher le cle ou le coffre
	/**
	 * @param equipe
	 */
	public Explorateur(Equipe equipe){
		super(equipe);
	}

	/* (non-Javadoc)
	 * @see Personnage#placer(int, int, Personnage, Ile)
	 */
	public void placer(int x, int y, Personnage p, Ile ile) {
		super.placer(x, y, p, ile);
		ile.positions[x][y] = 10;
	}

	/* (non-Javadoc)
	 * @see Personnage#deplacer(int, int, int, int, Ile)
	 */
	boolean action(int dX, int dY, Ile ile) {
		if (Math.abs(dX - x) + Math.abs(dY - y) == 1) {
			if (!super.action(dX, dY, ile)) {
				if (ile.getObjet(dX, dY) instanceof Rocher) {
					Explorateur p = (Explorateur) ile.getObjet(x, y);
					p.soulever(dX, dY, ile);
					energie = energie -5;
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
			equipe.vue[x][y] = 14;
			if (cle) {
				r.tresor = false;
				this.tresor = true;
				System.out.println("trésor!");
				equipe.vue[x][y] = 15;
			}
		}else if (r.coffre && !r.tresor) {
			System.out.println("Coffre découvert sans trésor!");
			equipe.vue[x][y] = 15;
		} else
			System.out.println("Il n'y a rien dedans");
	}

}
