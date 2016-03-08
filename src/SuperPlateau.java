import java.util.Random;


public class SuperPlateau {
	Plateau jeu;
	
	public SuperPlateau(String[]gifs,int i){
		jeu=new Plateau(gifs,i);
	}
	
	public void affichage(){
		jeu.affichage();
	}
	
	public void setJeu(int[][]t){
		jeu.setJeu(t);
	}
	
	public int[][]getJeu(){
		return jeu.getJeu();
	}
	
	public void paintComponent(java.awt.Graphics g){
		jeu.paintComponent(g);
	}
	
	public boolean deplacer(int x, int y,int a,int b){
		if(this.getJeu()[a][b]==0){
			int[][]j=this.getJeu();
			j[a][b]=j[x][y];
			j[x][y]=0;
			this.setJeu(j);
			this.affichage();
			return true;
		}else{
			return false;
		}
	}
		
	public static void main(String[]args){
		Random r=new Random();
		String[] gifs={"/home/infoetu/lassallr/ap/image/un.gif","/home/infoetu/lassallr/ap/image/deux.gif","/home/infoetu/lassallr/ap/image/trois.gif","/home/infoetu/lassallr/ap/image/quatre.gif"};
		int taille=10;
		SuperPlateau grille=new SuperPlateau(gifs,taille);
		int[][] jeu=new int[taille][taille];
		
		// Remplissage aléatoire du tableau
		for (int i=0;i<taille;i++){
			for (int j=0;j<taille;j++){
				jeu[i][j]=r.nextInt(gifs.length+1);
			}
		}
		jeu[0][1]=0;
		grille.setJeu(jeu);
		grille.affichage();
		
		
		try{Thread.sleep(500);}catch(Exception ie){}
		grille.deplacer(0, 0, 0, 1);
		try{Thread.sleep(500);}catch(Exception ie){}
		grille.affichage();
	}
}
