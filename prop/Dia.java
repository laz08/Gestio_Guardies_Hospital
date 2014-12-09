package prop;

public class Dia {
	private boolean festiu;
	private Torn[] torns;
	
	
	/**
	 * Creadora de dia
	 * @param f
	 */
	public Dia(boolean f) {
		festiu = f;
		torns = new Torn[3];
	}
	
	/**
	 * Consultora de si el dia es festiu
	 * @return
	 */
	public boolean getFestiu() {
		return festiu;
	}
	
	/**
	 * Modifica si el dia es festiu
	 * @param b
	 */
	public void setFestiu(boolean b) {
		festiu = b;
	}
	
	
	public void setTorn(Torn t) {
		if(t.getHora_inici()<9) torns[0]=t;
		else if(t.getHora_inici()<17) torns[1]=t;
		else if(t.getHora_inici()<24) torns[2]=t;
	}
	
	public void setTornConcret(Torn t, int i) {
		torns[i]=t;
	}
	
	/**
	 * Consultora dels torns del dia
	 * @return
	 */
	public Torn[] getTorns(){
		return torns;
	}
	
	/**
	 * Consultora d'un torn del dia
	 * @param i
	 * @return
	 */
	public Torn getTorn_concret(int i) {
		return torns[i];
	}
	
	
	
}