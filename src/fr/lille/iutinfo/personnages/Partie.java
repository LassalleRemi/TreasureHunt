package fr.lille.iutinfo.personnages;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

import fr.lille.iutinfo.terrain.Ile;

public class Partie {
	int taille;
	int pourcentageRochers;
	Equipe[] e = new Equipe[2];
	Ile ile;

	public Partie(int taille, int pourcentageRochers, String equipe1, String equipe2, String[] compoEquipe1,
			String[] compoEquipe2) {
		this.taille = taille;
		this.pourcentageRochers = pourcentageRochers;
		e[0] = new Equipe(equipe1);
		e[1] = new Equipe(equipe2);
		ile = new Ile(taille, pourcentageRochers, e[0], e[1]);
		constituerEquipe(e[0], compoEquipe1);
		constituerEquipe(e[1], compoEquipe2);
		jouer();
	}

	public void constituerEquipe(Equipe equipe, String[] persos) {
		for (int i = 0; i < persos.length; i++) {
			if (persos[i].equals("Explorateur"))
				new Explorateur(equipe);
			else if (persos[i].equals("Voleur"))
				new Voleur(equipe);
			else if (persos[i].equals("Piegeur"))
				new Piegeur(equipe);
			else if (persos[i].equals("Guerrier"))
				new Guerrier(equipe);
		}
	}

	public void jouer() {
		InputEvent event;

		boolean tresorTrouve = false;
		boolean vivants = true;
		int x = 0, y = 0;
		while (vivants && !tresorTrouve) {
			for (int equipe = 0; equipe < 2; equipe++) {
				e[equipe].vueCommune(ile);
				ile.afficher(e[equipe].vue);
				ile.afficherInfo("Au tour de l'équipe " + e[equipe]);
				for (Personnage p : e[equipe].getEquipe()) {
					if (!tresorTrouve) {
						if (e[equipe].getNavire().estPresent(p)) {
							System.out.println(e[equipe].getNavire());
							Personnage sortir = (Personnage) JOptionPane.showInputDialog(null,
									"Quel personnage voulez vous faire sortir?", "Choix du personnage",
									JOptionPane.QUESTION_MESSAGE, null, e[equipe].getNavire().getPersos().toArray(),
									null);
							if (sortir != null)
								e[equipe].getNavire().sortirPerso(sortir, ile);
						} else {
							ile.afficher(p.surbrillance(ile));
							do {
								event = ile.plateau.waitEvent(5000);
								if (event instanceof MouseEvent) {
									x = ile.plateau.getX((MouseEvent) event);
									y = ile.plateau.getY((MouseEvent) event);
								} else
									event = null;
							} while (event == null);
							p.action(y, x, ile, event);
						}
						ile.afficher(e[equipe].vue);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ex) {
							Thread.currentThread().interrupt();
						}
						if (p.estMort(ile)) {
							ile.afficherInfo("Le personnage " + p + " n'a plus d'énergie, par conséquence il est mort");
							ile.afficher(e[equipe].vue);
							try {
								Thread.sleep(2000);
							} catch (InterruptedException ex) {
								Thread.currentThread().interrupt();
							}
						}
						if (e[equipe].getNavire().tresor) {
							tresorTrouve = true;
						}
					} else
						ile.afficherInfo("Le trésor est sur le navire, l'équipe "+e[equipe].nom+" gagne");
				}

				if (e[equipe].sontMorts(ile)) {
					vivants = false;
				}

			}
		}
		ile.afficher();
		ile.afficherInfo("C'est fini");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

}
