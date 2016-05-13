package fr.lille.iutinfo.affichage;

public enum Image {
	SABLE("sand.png"), SURBRILLANCE_SABLE("surbrillance_sand.png"), 
	ROCHER("rocher.png"), SURBRILLANCE_ROCHER("surbrillance_rocher.png"), 
	NAVIRE1("ship.png"), SURBRILLANCE_NAVIRE1("surbrillance_navire1.jpg"), 
	NAVIRE2("ship2.png"), SURBRILLANCE_NAVIRE2("surbrillance_navire2.jpg"), 
	COFFRE("chest.png"), SURBRILLANCE_COFFRE("surbrillance_chest.png"), 
	COFFRE_OUVERT("chest_open.png"), SURBRILLANCE_COFFRE_OUVERT("chest_open.png"), 
	EXPLORATEUR("explorateur.png"), SURBRILLANCE_EXPLORATEUR("surbrillance_explorateur.png"),
	EXPLORATEUR2("explorateur2.png"), SURBRILLANCE_EXPLORATEUR2("surbrillance_explorateur2.png"),
	VOLEUR("voleur.png"), SURBRILLANCE_VOLEUR("surbrillance_voleur.png"), 
	VOLEUR2("voleur2.png"), SURBRILLANCE_VOLEUR2("surbrillance_voleur2.png"),
	PIEGEUR("piegeur.png"), SURBRILLANCE_PIEGEUR("surbrillance_piegeur.png"),
	PIEGEUR2("piegeur2.png"), SURBRILLANCE_PIEGEUR2("surbrillance_piegeur2.png"),
	GUERRIER("guerrier.jpg"), SURBRILLANCE_GUERRIER("surbrillance_guerrier.png"),
	GUERRIER2("guerrier2.png"), SURBRILLANCE_GUERRIER2("surbrillance_guerrier2.png"),
	TRESOR("tresor.png"), SURBRILLANCE_TRESOR("surbrillance_tresor.png"),
	CLE("cle.png"), SURBRILLANCE_CLE("surbrillance_cle.png"),
	BUISSON("buisson.png"), SURBRILLANCE_BUISSON("surbrillance_buisson.png"),
	SOMBRE("sombre.jpg"), EAU("water.png"), PIEGE("piege.png"), NOURRITURE("nourriture.png"),
	BOUTEILLE("bouteille.png");
	
	private String chemin;
	
	private Image(String chemin){
		this.chemin = chemin;
	}
	
	public String getChemin(){
		return chemin;
	}
	
	public static String[] tousChemins(){ 
		String[] chemins = new String[Image.values().length];
		for (int i=0; i<Image.values().length; i++) {
			chemins[i] = Image.values()[i].getChemin(); 
		}
		return chemins;
	}
	
	public int monOrdinal(){
		return ordinal()+1;
	}
}
