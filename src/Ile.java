import java.util.Random;


public class Ile {
	Parcelle[][]ile;
	int taille = 10;
	
	public Ile(){
		ile = new Parcelle[10][10];
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				ile[i][j]=new Parcelle();
				if (i==0 || i==taille-1 || j==0 || j==taille-1){
					ile[i][j].elem=0;
				} else if (i==1 || i==taille-2 || j==1 || j==taille-2){
					ile[i][j].elem=1;
				}
			}
		}
		ile[1][1].elem=3;
		ile[taille-2][taille-2].elem=3;
	}
	
	public Ile(int i){
		ile = new Parcelle[i][i];
	}
	
	
	public String toString(){
		String message = "";
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				message+= ile[i][j].gifs[ile[i][j].elem]+" ";
			}
			message+= "\n";
		}
		return message;
	}
	
}
