package prop;


public class Torn {
	
	private int hora_inici;
	private int hora_fi;
	private float percent_sou;
	private int min_num_doctors;
	

	//Pre: -
	//Post: Creem un torn amb tots els parametres que ens arriben
	public Torn(int h_i, int h_f, float percentatge, int minim) {
		if(h_i>=0 && h_i<24 && h_f>=0 && h_f<24 && h_i<h_f) {
			hora_inici = h_i;
			hora_fi = h_f;
			percent_sou = percentatge;
			min_num_doctors = minim;
		}
	}
	
	//Pre: -
	//Post: Reinicialitzem torn, posant tot a 0
	public void borrar() {
		hora_inici = 0;
		hora_fi = 0;
		percent_sou = 0;
		min_num_doctors = 0;
	}
	
	//Pre: -
	//Post: Retornem l'hora d'inici del torn
	public int getHora_inici() {
		return hora_inici;
	}
	
	//Pre: -
	//Post: modifiquem hora d'inici del torn per la que ens passen si compleix les condicions que sigui menor que la hora fi i que sigui un enter entre 0 i 23, sino no es modificat.
	public void setHora_inici(int h_i) {
		if(h_i<hora_fi && h_i>=0 && h_i<24) hora_inici = h_i;
	}
	
	//Pre: -
	//Post: Retornem l'hora fi del torn
	public int getHora_fi() {
		return hora_fi;
	}
	
	//Pre: -
	//Post: modifiquem hora d'inici del torn per la que ens passen si compleix les condicions que sigui major que la hora inici i que sigui un enter entre 0 i 23, sino no es modificat.
	public void setHora_fi(int h_f) {
		if(h_f>hora_inici && h_f>=0 && h_f<24) hora_fi = h_f;
	}
	
	//Pre: -
	//Post: Retornem el percentatge de sou del torn
	public float getPercent_sou() {
		return percent_sou;
	}
	
	//Pre: -
	//Post: Modifiquem el percentatge de sou del torn pel que ens arriba
	public void setPercent_sou(float percentatge) {
		percent_sou = percentatge;
	}
	
	//Pre: -
	//Post: Retornem el nombre minim de doctors que han d'assistir al torn
	public int getMin_num_doctors() {
		return min_num_doctors;
	}
	
	//Pre: -
	//Post: Modifiquem el numero minim de doctors si compleix la condició que el numero que arribi sigui positiu
	public void setMin_num_doctors(int minim) {
		if(minim>=0) min_num_doctors = minim;
	}
}