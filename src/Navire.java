import java.util.ArrayList;

public class Navire extends Parcelle{
	private ArrayList<Personnage> persos;
	int xN;
	int yN;
	
	public Navire(int x, int y){
		xN = x;
		yN = y;
	}
	public void addPerso(Personnage pers) {
		persos.add(pers);
	}

	public void removePerso(Personnage pers) {
		persos.remove(pers);
	}

	public ArrayList<Personnage> getPerso() {
		return persos;
	}
}
