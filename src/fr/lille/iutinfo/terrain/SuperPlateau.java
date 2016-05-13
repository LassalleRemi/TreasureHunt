package fr.lille.iutinfo.terrain;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

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

	public int getX(MouseEvent event) {
		return jeu.getX(event);
	}
	
	public int getY(MouseEvent event) {
		return jeu.getY(event);
	}
	
	public InputEvent waitEvent(int timeout){
		return jeu.waitEvent(timeout);
	}
	
	public void println(String message){
		jeu.println(message);
	}
	
	public void afficherInfo(String texte){
		jeu.afficherInfo(texte);
	}
	
	public void cacherInfo(){
		jeu.cacherInfo();
	}

}
