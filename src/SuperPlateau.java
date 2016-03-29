import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

import tps.*;

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

	int getX(MouseEvent event) {
		return jeu.getX(event);
	}
	
	int getY(MouseEvent event) {
		return jeu.getY(event);
	}
	
	public InputEvent waitEvent(int timeout){
		return jeu.waitEvent(timeout);
	}

}
