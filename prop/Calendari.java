package prop;

import java.util.Arrays;
import java.util.GregorianCalendar;

public class Calendari {
	
	private Dia[] cal;
	private int id;
	private Plantilla p;
	
	
	/**
	 * Creadora de Calendari segons els anys
	 * @param any_inici
	 * @param any_fi
	 */
	public Calendari(String plt, int any_inici, int any_fi) {
		GregorianCalendar dia = new GregorianCalendar(any_inici,0,0);
		GregorianCalendar diaf = new GregorianCalendar(any_fi+1,0,0);
		long dies = diaf.getTimeInMillis() - dia.getTimeInMillis();
		dies = dies/1000/60/60/24;
		cal = new Dia[(int) dies];
		setPlantillaAssociada(plt);
		id = Integer.parseInt(plt);
	}
	
	public void setIdentificador(int ident) {
		id = ident;
	}
	
	/**
	 * Consultora del calendari
	 * @return
	 */
	public Dia[] getCalendari() {
		return cal;
	}
	
	public void setPlantillaAssociada(String nom) {
		//p = CtrlPlantilla.getPlantillaNom(nom);
		p =  CtrlPlantilla.consultarPlantilla(nom);
	}
	
	public String getPlantillaAssociada() {
		return p.getNomPlantilla();
	}
	
}