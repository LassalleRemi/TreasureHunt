
public class Voleur extends Personnage {
	boolean cle = false;
	boolean tresor = false;

	/**
	 * @param team
	 */
	public Voleur(Equipe team) {
		super(team);
	}

	/* (non-Javadoc)
	 * @see Personnage#placer(int, int, Personnage, Ile)
	 */
	public void placer(int x, int y, Personnage p, Ile ile) {
		super.placer(x, y, p, ile);
		ile.positions[x][y] = 9;
	}

	/* (non-Javadoc)
	 * @see Personnage#deplacer(int, int, int, int, Ile)
	 */
	boolean deplacer(int x, int y, int dX, int dY, Ile ile) {
		if ((dX == x + 1 && dY == y) ^ (dY == y + 1 && dX == x) ^ (dX == x - 1 && dY == y) ^ (dY == y - 1 && dX == x)
				^ (dY == y - 1 && dX == x - 1) ^ (dY == y + 1 && dX == x + 1) ^ (dY == y + 1 && dX == x - 1)
				^ (dY == y - 1 && dX == x + 1)) {
			if (super.deplacer(x, y, dX, dY, ile)) {
				return true;
			} else {
				if (ile.getObjet(dX, dY) instanceof Personnage) {
					System.out.println("volé");
					return false;
				}
				return false;
			}
		}
		return false;
	}
}
