package prop;


public class Torn {
	
	private int hora_inici;
	private int hora_fi;
	private float percent_sou;
	private int min_num_doctors;
	
	/**
	 * Creadora
	 */
	public Torn(int h_i, int h_f, float percentatge, int minim) {
		if(h_i>=0 && h_i<24 && h_f>=0 && h_f<24 && h_i<h_f) {
			hora_inici = h_i;
			hora_fi = h_f;
			percent_sou = percentatge;
			min_num_doctors = minim;
		}
	}
	
	/**
	 * Consultora de l'hora d'inici del torn
	 * @return
	 */
	public int getHora_inici() {
		return hora_inici;
	}
	
	/**
	 * Modifica la hora d'inici del torn
	 * @param h_i
	 */
	public void setHora_inici(int h_i) {
		if(h_i<hora_fi && h_i>=0 && h_i<24) hora_inici = h_i;
	}
	
	/**
	 * Consultora de l'hora fi del torn
	 * @return
	 */
	public int getHora_fi() {
		return hora_fi;
	}
	
	/**
	 * Modifica l'hora d'inici del torn
	 * @param h_f
	 */
	public void setHora_fi(int h_f) {
		if(h_f>hora_inici && h_f>=0 && h_f<24) hora_fi = h_f;
	}
	
	/**
	 * Consultora del percentatge de sou del torn
	 * @return
	 */
	public float getPercent_sou() {
		return percent_sou;
	}
	
	/**
	 * Modifica el percentatge de sou del torn
	 * @param percentatge
	 */
	public void setPercent_sou(float percentatge) {
		percent_sou = percentatge;
	}
	
	/**
	 * Consultora del numero minim de doctors del torn
	 * @return
	 */
	public int getMin_num_doctors() {
		return min_num_doctors;
	}
	
	/**
	 * Modifica el numero minim de doctors del torn
	 * @param minim
	 */
	public void setMin_num_doctors(int minim) {
		if(minim>=0) min_num_doctors = minim;
	}
}