
public class Parcelle {
	boolean estTraversable = true; // si cette case est accessible

	/**
	 * Constructeur par d�faut de la classe Parcelle.
	 */
	public Parcelle() {
	}

	/**
	 * Constructeur de la classe Parcelle, un param�tre demand�.
	 * 
	 * @param estTraversable
	 *            Boolean qui d�termine si l'objet cr�� est traversable ou non.
	 */
	public Parcelle(boolean estTraversable) {
		this.estTraversable = estTraversable;
	}

}
