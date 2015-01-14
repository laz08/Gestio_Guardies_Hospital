package prop;


public class Torn {

	private int hora_inici;
	private int hora_fi;
	private float percent_sou;
	private int min_num_doctors;
	private int posicio_dia;


	//Pre: -
	//Post: Creem un torn amb tots els parametres que ens arriben
	public Torn(int horari, float percentatge, int minim) {
		if(horari==0) {
			hora_inici = 0;
			hora_fi = 8;
		}
		else if(horari==1) {
			hora_inici =8;
			hora_fi=16;
		}
		else {
			hora_inici=16;
			hora_fi=24;
		}
		percent_sou = percentatge;
		min_num_doctors = minim;
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
		if(h_f>hora_inici && h_f>=0 && h_f<=24) hora_fi = h_f;
	}

	/**
	 * Retornem el percentatge de sou del torn.
	 * @return
	 */
	public float getPercent_sou() {
		return percent_sou;
	}

	/**
	 * Modifiquem el percentatge de sou del torn pel que ens arriba.
	 * @param percentatge
	 */
	
	public void setPercent_sou(float percentatge) {
		percent_sou = percentatge;
	}

	/**
	 * Retornem el nombre minim de doctors que han d'assistir al torn.
	 * @return
	 */
	
	public int getMin_num_doctors() {
		return min_num_doctors;
	}
	
	/**
	 * Modifiquem el numero minim de doctors si compleix la condició que el numero que arribi sigui positiu
	 * @param minim
	 */
	
	public void setMin_num_doctors(int minim) {
		if(minim>=0) min_num_doctors = minim;
	}

	/**
	 * Retornem la posició del dia a la que pertany el torn
	 * @return
	 */

	public int getPosicio() {
		return posicio_dia;
	}

	/**
	 * Modifiquem la posicio del torn
	 * en el dia
	 */

	public void setPosicio(int pos) {
		if(pos>=0) posicio_dia = pos;
	}

	/**
	 * Modifica l'horari del torn 
	 * segons el parametre.
	 * @param horari(0, 1 o 2)
	 */

	public void setHorari(int horari) {
		if(horari==0) {
			hora_inici = 0;
			hora_fi = 8;
		}
		else if(horari==1) {
			hora_inici =8;
			hora_fi=16;
		}
		else {
			hora_inici=16;
			hora_fi=24;
		}
	}

	/**
	 * Retorna identificador de torn (0,1 o 2) 
	 * segons hora inici del torn.
	 * @return
	 */

	public int getHorari() {
		if(hora_inici==0) return 0;
		else if(hora_inici==8) return 1;
		else return 2;
	}
}