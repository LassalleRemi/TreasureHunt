import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class TestIle {

	public static void main(String[] args) {
		Ile ile = new Ile(15, 20);
		// Equipe team = new Equipe();
		// team.addPerso(new Explorateur(3, 3));
		// ile.surbrillance(ile.getCoord(3)[0], ile.getCoord(3)[1], 1);
		Personnage perso = new Voleur();
		Personnage perso2 = new Voleur();
		int[] coords = ile.getNavire(4);
		int xPerso = coords[0] + 1, yPerso = coords[1];
		int xPerso2 = coords[0], yPerso2 = coords[1] + 1;
		perso.placer(xPerso, yPerso, perso, ile);
		perso2.placer(xPerso2, yPerso2, perso2, ile);
		ile.afficher();
		int x = 0, y = 0;

		InputEvent event;
		while (true) {
			event = ile.plateau.waitEvent(10000);
			if (event instanceof MouseEvent) {
				System.out.println("on est ici");
				x = ile.plateau.getX((MouseEvent) event);
				y = ile.plateau.getY((MouseEvent) event);
				System.out.println("ligne " + x + " colonne : " + y);
			}
			if (perso.deplacer(xPerso, yPerso, y, x, ile)) {
				xPerso = y;
				yPerso = x;
			}
			ile.afficher();
		}
	}

}
