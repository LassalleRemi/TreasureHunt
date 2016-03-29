
public class Voleur extends Personnage {
	boolean cle = false;
	boolean tresor = false;

	public void placer(int x, int y, Personnage p, Ile ile) {
		super.placer(x, y, p, ile);
		ile.positions[x][y] = 9;
	}

	boolean deplacer(int x, int y, int dX, int dY, Ile ile) {
		if ((dX == x + 1 && dY == y) ^ (dY == y + 1 && dX == x) ^ (dX == x - 1 && dY == y) ^ (dY == y - 1 && dX == x)
				^ (dY == y - 1 && dX == x - 1) ^ (dY == y + 1 && dX == x + 1) ^ (dY == y + 1 && dX == x - 1)
				^ (dY == y - 1 && dX == x + 1)) {
			return super.deplacer(x, y, dX, dY, ile);
		}
		return false;
	}
}
