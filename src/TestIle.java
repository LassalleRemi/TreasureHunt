import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class TestIle {

	public static void main(String[] args) {
		Equipe team = new Equipe("Skittles");
		Equipe team2 = new Equipe("Guinguette");

		Ile ile = new Ile(15, 20, team, team2);

		Personnage vol = new Voleur(team);
		Personnage exp = new Explorateur(team);
		team.addPerso(vol);
		team.addPerso(exp);
		ile.n1.addPerso(vol);
		ile.n1.addPerso(exp);
		System.out.println(ile.n1);

		Personnage vol2 = new Voleur(team2);
		Personnage exp2 = new Explorateur(team2);
		team2.addPerso(vol2);
		team2.addPerso(exp2);
		ile.n2.addPerso(vol2);
		ile.n2.addPerso(exp2);

		ile.afficher();
		InputEvent event;

		int x = 0, y = 0;
		while (true) { // le jeu n'est pas finit
			for (Personnage p : team.getEquipe()) {
				if (ile.n1.estPresent(p)) {
					ile.n1.sortirPerso(p, ile);
					System.out.println(ile.n1);

				} else {
					System.out.println(p);
					event = ile.plateau.waitEvent(10000);
					if (event instanceof MouseEvent) {
						x = ile.plateau.getX((MouseEvent) event);
						y = ile.plateau.getY((MouseEvent) event);
						System.out.println("ligne " + x + " colonne : " + y);
					}
					p.deplacer(p.x, p.y, y, x, ile);
				}
				ile.afficher();
			}
			for (Personnage p2 : team2.getEquipe()) {
				if (ile.n2.estPresent(p2)) {
					ile.n2.sortirPerso(p2, ile);
					System.out.println(ile.n2);

				} else {
					System.out.println(p2);
					event = ile.plateau.waitEvent(10000);
					if (event instanceof MouseEvent) {
						x = ile.plateau.getX((MouseEvent) event);
						y = ile.plateau.getY((MouseEvent) event);
						System.out.println("ligne " + x + " colonne : " + y);
					}
					p2.deplacer(p2.x, p2.y, y, x, ile);
				}
				ile.afficher();
			}

		}

	}

}
