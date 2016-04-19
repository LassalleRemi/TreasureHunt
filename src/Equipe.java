import java.util.ArrayList;

public class Equipe {
	private ArrayList<Personnage> equipe = new ArrayList<>();
	String nom;

	public Equipe(String nom) {
		this.nom = nom;
	}

	public String toString() {
		return nom;
	}

	public void addPerso(Personnage pers) {
		equipe.add(pers);
	}

	public void removePerso(Personnage pers) {
		equipe.remove(pers);
	}

	public ArrayList<Personnage> getEquipe() {
		return equipe;
	}

	/**
	 * Vérifie si un personnage donné appartient à l'équipe
	 * 
	 * @param pers
	 *            Le personnage à vérifier l'appartenance
	 * @return Si le personnage appartient ou non à l'équipe
	 */
	boolean appartient(Personnage pers) {
		for (Personnage p : equipe)
			if (pers == p)
				return true;
		return false;
	}
}
