
public class TestIle {

	public static void main(String[] args) {
		Ile ile= new Ile(20, 30);
		//Equipe team = new Equipe();
		//team.addPerso(new Explorateur(3, 3));
		ile.surbrillance(ile.getCoord(3)[0], ile.getCoord(3)[1], 1);
		Explorateur perso = new Explorateur();
		perso.placer(ile.getCoord(3)[0]+1, ile.getCoord(3)[1]);
		ile.afficher();
	}

}
