import java.util.ArrayList;

public class Navire extends Parcelle{
	private ArrayList<Personnage> persos = new ArrayList<>();
	int xN;
	int yN;
	
	public Navire(int x, int y){
		super.estTraversable = true;
		xN = x;
		yN = y;
	}
	public void addPerso(Personnage pers) {
		persos.add(pers);
		System.out.println(persos);
	}

	public void removePerso(Personnage pers) {
		persos.remove(pers);
	}

	public ArrayList<Personnage> getPerso() {
		return persos;
	}
}
