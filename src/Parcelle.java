import java.util.Random;


public class Parcelle {
	int elem = 0;
	String[]gifs=new String[]{"O"," ","R","N","J"};
	String e;
	
	public Parcelle(){
		Random r = new Random();
		int nb=r.nextInt(10);
		if(nb==0){
			elem=2;
		}else{
			elem=1;
		}
		e=gifs[this.elem];
	}
	public Parcelle(int i){
		Random r = new Random();
		int nb=r.nextInt(i);
		if(nb==0){
			elem=2;
		}else{
			elem=1;
		}
		e=gifs[this.elem];
	}
}
