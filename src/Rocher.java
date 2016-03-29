
public class Rocher extends Parcelle {
	boolean cle = false;
	boolean coffre = false;
	boolean tresor = false;

	/**
	 * Constructeur de la classe Rocher.
	 */
	public Rocher() {
		super.estTraversable = false;
	}

	public Rocher(boolean cle, boolean coffre, boolean tresor) {
		this();
		this.cle = cle;
		this.coffre = coffre;
		this.tresor = tresor;
	}
}
