package fr.lille.iutinfo.personnages;

import fr.lille.iutinfo.affichage.Composition;

public class Jouer {

	public Jouer() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Composition f = new Composition();
		while(!f.stop){
			Thread.yield();
		}
		new Partie(f.slide.getValue(), f.slideRochers.getValue(), f.nomEquipe1.getText(), f.nomEquipe2.getText(), f.compoEquipe1(), f.compoEquipe2());
	}

}
