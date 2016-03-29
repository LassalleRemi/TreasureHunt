
public class Explorateur extends Personnage {
	final int ID = 7;
	boolean cle = false;
	boolean tresor = false;

	public void placer(int x, int y, Personnage p, Ile ile) {
		super.placer(x, y, p, ile);
		ile.positions[x][y] = 8;
	}

	boolean deplacer(int x, int y, int dX, int dY, Ile ile) {
		if (Math.abs(dX - x) + Math.abs(dY - y) == 1) {
			return super.deplacer(x, y, dX, dY, ile);
		}
		return false;
	}

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
