import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TestIle {

	public static void main(String[] args) {

		// JFrame constitution = new JFrame("Constituer equipe"); // demande
		// constitution equipe
		// constitution.setVisible(true);
		final int TAILLE = 8;
		String[] n = new String[] { "premier", "deuxième", "troisième" };
		Equipe[] e = new Equipe[2];
		e[0] = new Equipe(JOptionPane.showInputDialog("Le nom de la première équipe:", "Skittles"));
		e[1] = new Equipe(JOptionPane.showInputDialog("Le nom de la seconde équipe:", "Smarties"));
		Ile ile = new Ile(TAILLE, 10, e[0], e[1]);
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 3; i++) {
				String perso = (String) JOptionPane.showInputDialog(null,
						"Equipe " + e[j] + ", choisissez votre " + n[i] + " personnage: ", "Choix du personnage",
						JOptionPane.QUESTION_MESSAGE, null, new String[] { "Explorateur", "Voleur" }, "Voleur");
				System.out.println(perso);
				if (perso.equals("Explorateur"))
					new Explorateur(e[j]);
				else if (perso.equals("Voleur"))
					new Voleur(e[j]);
				else
					i--;
			}
		}

		InputEvent event;

		boolean tresorTrouve = false;
		boolean vivants = true;
		int x = 0, y = 0;
		while (vivants && !tresorTrouve) {
			for (int equipe = 0; equipe < 2; equipe++) {
				e[equipe].vueCommune(ile);
				ile.afficher(e[equipe].vue);
				for (Personnage p : e[equipe].getEquipe()) {
					if (e[equipe].getNavire().estPresent(p)) {
						System.out.println(e[equipe].getNavire());
						Personnage sortir = (Personnage) JOptionPane.showInputDialog(null,
								"Quel personnage voulez vous faire sortir?", "Choix du personnage",
								JOptionPane.QUESTION_MESSAGE, null, e[equipe].getNavire().getPersos().toArray(), null);
						if (sortir != null)
							e[equipe].getNavire().sortirPerso(sortir, ile);
					} else {
						ile.afficher(p.surbrillance(ile));
						do {
							event = ile.plateau.waitEvent(10000);
							if (event instanceof MouseEvent) {
								x = ile.plateau.getX((MouseEvent) event);
								y = ile.plateau.getY((MouseEvent) event);
							} else
								event = null;
						} while (event == null);
						p.action(y, x, ile);
					}
					ile.afficher(e[equipe].vue);
					System.out.println(p);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					if (p.estMort()) {
						System.out.println("Le personnage " + p + " n'a plus d'énergie, par conséquence il est mort");
						e[equipe].removeVisible(p);
						ile.removeObjet(p);
						ile.removePosition(p);
					}
				}

				if (e[equipe].sontMorts()) {
					vivants = false;
				}
			}
		}
		ile.afficher();
		System.out.println("C'est fini");
	}

}
