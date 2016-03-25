import java.util.ArrayList;

public class Equipe {
	private ArrayList<Personnage> equipe;

	public void addPerso(Personnage pers) {
		equipe.add(pers);
	}

	public void removePerso(Personnage pers) {
		equipe.remove(pers);
	}

	public ArrayList<Personnage> getEquipe() {
		return equipe;
	}
}
