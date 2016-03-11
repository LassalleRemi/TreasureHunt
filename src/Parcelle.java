import java.util.Random;

public class Parcelle {
	boolean estTraversable; //si cette case est accessible
	int elem = 1; // 1=sable
	
	public Parcelle(){
		Random r = new Random();
		int nb = r.nextInt(10);
		if(nb==0){
			elem=2;
		}else{
			elem=1;
		}
		//e=gifs[this.elem];
	}
	public Parcelle(int i){
		Random r = new Random();
		int nb=r.nextInt(i);
		if(nb==0){
			elem=2;
		}else{
			elem=1;
		}
		//e=gifs[this.elem];
	}
}
