package prop;

public class Dia {
	
	private boolean festiu;
	private Torn[] torns;
	
	
	//Pre: -
	//Post: creem un dia amb tres torns buits i el boolea de festiu com ens indiqui el parametre
	public Dia(boolean f) {
		festiu = f;
		torns = new Torn[3];
	}
	

	//Pre: -
	//Post: Retornem un boolea indicant si el dia es festiu o no
	public boolean getFestiu() {
		return festiu;
	}
	
	//Pre: -
	//Post: Modifiquem el boolea de si el dia es festiu pel que ens passen per parametres
	public void setFestiu(boolean b) {
		festiu = b;
	}
	
	//Pre: -
	//Post: Retornem els torns del dia
	public Torn[] getTorns(){
		return torns;
	}
	
	//Pre: ts tres torns
	//Post: Modifica els torns pels torns que li arriben
	public void setTorns(Torn[] ts) {
		torns = ts;
	}
	
	//Pre: -
	//Post: retorna el primer torn del dia
	public Torn getTornMati() {
		return torns[0];
	}
	
	//Pre: -
	//Post: Modifica el torn de mati
	public void setTornMati(Torn tm) {
		torns[0]=tm;
	}
	
	//Pre: -
	//Post: retorna el segon torn del dia
	public Torn getTornTarda() {
		return torns[1];
	}
	
	//Pre: -
	//Post: Modifica el torn de mati
	public void setTornTarda(Torn tm) {
		torns[1]=tm;
	}
	
	//Pre: -
	//Post: retorna el tercer torn del dia
	public Torn getTornNit() {
		return torns[2];
	}
	
	//Pre: -
	//Post: Modifica el torn de mati
	public void setTornNit(Torn tm) {
		torns[2]=tm;
	}
	
	/*//Pre: -
	//Post: Modifiquem un dels torns pel torn nou que ens arriba, segons els parametres modifiquem un o altre
	public void setTorn(Torn t) {
		if(t.getHora_inici()<9) torns[0]=t;
		else if(t.getHora_inici()<17) torns[1]=t;
		else if(t.getHora_inici()<24) torns[2]=t;
	}
	
	
	//Pre: El parametre i ha d'estar entre 0 i 2, i el Torn t ha de cumplir les condicions de les hores segons i.
	//Post: Modifiquem el torn de mati, tarda o nit(segons la i) pel nou torn que ens arriba.
	public void setTornConcret(Torn t, int i) {
		torns[i]=t;
	}
	

	
	//Pre: El parametre i ha d'estar entre 0 i 2.
	//Post: Retornem el torn que es troba a la posició i.
	public Torn getTorn_concret(int i) {
		return torns[i];
	}*/
	
	/*//Pre: -
	//Post: Borrem tota la informacio del torn i
	public void borrarTorn(int i) {
		//torns[i].borrar();
		torns = null;
	}*/
	
	/*//Pre: -
	//Post: Borrem tota la informació del dia
	public void borrar() {
		festiu = false;
		torns = null;
		//for(int i=0; i<3; ++i) torns[i].borrar();
	}*/
	
}