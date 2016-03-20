
public class SuperPlateau {
	Plateau jeu;

	public SuperPlateau(String[] gifs, int i) {
		jeu = new Plateau(gifs, i);
	}

	public void affichage() {
		jeu.affichage();
	}

	public void setJeu(int[][] t) {
		jeu.setJeu(t);
	}

	public int[][] getJeu() {
		return jeu.getJeu();
	}

	public void paintComponent(java.awt.Graphics g) {
		jeu.paintComponent(g);
	}

	public boolean deplacer(int x, int y, int a, int b) {
		if (this.getJeu()[a][b] == 0) {
			int[][] j = this.getJeu();
			j[a][b] = j[x][y];
			j[x][y] = 0;
			this.setJeu(j);
			this.affichage();
			return true;
		} else {
			return false;
		}
	}

}
