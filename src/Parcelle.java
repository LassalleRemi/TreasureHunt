
public class Parcelle {
	boolean estTraversable = true; // si cette case est accessible

	/**
	 * Constructeur par défaut de la classe Parcelle.
	 */
	public Parcelle() {
	}

	/**
	 * Constructeur de la classe Parcelle, un paramètre demandé.
	 * 
	 * @param estTraversable
	 *            Boolean qui détermine si l'objet créé est traversable ou non.
	 */
	public Parcelle(boolean estTraversable) {
		this.estTraversable = estTraversable;
	}

}
