
public class Voleur extends Personnage {
	boolean cle = false;
	boolean tresor = false;

	/**
	 * @param team
	 */
	public Voleur(Equipe team) {
		super(team);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Personnage#placer(int, int, Personnage, Ile)
	 */
	public void placer(int x, int y, Personnage p, Ile ile) {
		super.placer(x, y, p, ile);
		ile.positions[x][y] = 12;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Personnage#deplacer(int, int, int, int, Ile)
	 */
	boolean action(int dX, int dY, Ile ile) {
		if ((dX == x + 1 && dY == y) ^ (dY == y + 1 && dX == x) ^ (dX == x - 1 && dY == y) ^ (dY == y - 1 && dX == x)
				^ (dY == y - 1 && dX == x - 1) ^ (dY == y + 1 && dX == x + 1) ^ (dY == y + 1 && dX == x - 1)
				^ (dY == y - 1 && dX == x + 1)) {
			if (super.action(dX, dY, ile)) {
				return true;
			} else {
				if (ile.getObjet(dX, dY) instanceof Personnage) {
					Personnage p = (Personnage) ile.getObjet(dX, dY);
					if (!equipe.appartient(p) && p instanceof Explorateur){ // il n'est pas de mon équipe et est un explorateur
						voler((Explorateur)p);
						energie = energie -10;
						ile.plateau.println("Vous avez volé ce perso explorateur");
					}else{
						ile.plateau.println("Ce perso est de votre équipe ou ce n'est pas un explorateur");
					}
					return false;
				}
				return false;
			}
		}
		return false;
	}
	
	void voler(Explorateur p){
		if(p.cle){
			this.cle = true;
			p.cle = false;
			System.out.println(p +" n'a plus sa clé, c'est " + this +" qui l'as mtn");
		}else
			System.out.println(p +" n'a pas de clé sur lui");
		if(p.tresor){
			this.tresor = true;
			p.tresor = false;
			System.out.println(p +" n'a plus son tresor, c'est " + this +" qui l'as mtn");
		}else
			System.out.println(p +" n'a pas de tresor sur lui");
	}
}
