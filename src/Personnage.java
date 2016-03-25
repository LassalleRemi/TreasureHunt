
public abstract class Personnage extends Parcelle {
	int x; // x et y de la position du personnage
	int y;

	public Personnage(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Personnage(){
		
	}

	abstract void deplacer(int x, int y);

}
