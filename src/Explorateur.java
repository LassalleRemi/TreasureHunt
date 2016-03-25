
public class Explorateur extends Personnage {
	boolean cle = false;
	boolean tresor = false;

	/*
	 public Explorateur(int x, int y){
	
		super(x,y);
	}
	*/
	void placer(int x, int y, int[][] positions) {
		positions[x][y] = 8;
	}

	void soulever(int x, int y, Parcelle[][] ile) {
		x = x/32;
		y = y/32;
		if(ile[x][y].cle)
			cle = true;
		else if(ile[x][y].coffre)
			tresor = true;
			
	}
	
	
}
