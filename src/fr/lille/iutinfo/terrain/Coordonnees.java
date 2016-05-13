package fr.lille.iutinfo.terrain;

public class Coordonnees {

	private int x, y;

	/**
	 * Cr�e un couple de coordonn�es
	 * 
	 * @param x
	 *            l'abscisse
	 * @param y
	 *            l'ordonn�e
	 */
	public Coordonnees(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Retourne l'abscisse
	 * 
	 * @return l'abscisse
	 */
	public int getX() {
		return x;
	}

	/**
	 * Retourne l'ordonn�e
	 * 
	 * @return l'ordonn�e
	 */
	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean equals(Coordonnees coords) {
		if (coords.x == x && coords.y == y)
			return true;
		return false;
	}
	
	public String toString(){
		return "x = "+x+", y = "+y;
	}
}
