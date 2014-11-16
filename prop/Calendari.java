/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Calendari {
	
	private int id;
	private int id_plantilla;
	private ArrayList<Torn> c;
	
	/** 
	 * Creació d'un nou calendari
	 */
	public Calendari(){
	}
	
	@SuppressWarnings("unchecked")
	public Calendari(int ident, int ident_plantilla, ArrayList<Torn> llt) {
		id = ident;
		id_plantilla = ident_plantilla;
		c = (ArrayList<Torn>) llt.clone();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id_nou) {
		id = id_nou;
	}
	
	public int getId_plantilla() {
		return id_plantilla;
	}
	
	public void setId_plantilla(int id_plantilla_nou) {
		id_plantilla = id_plantilla_nou;
	}
	
	public ArrayList<Torn> getTorns() {
		return c;
	}
	
	public void afegir_torn(Torn t) {
		if(c.isEmpty()) c.add(t);
		else {
			int i = posicio_torn(t);
			if(i!=-1) c.add(i,t);
		}
	}
	
	public void eliminar_torn(Torn t)  {
		int i=0;
		while (c.get(i).getData_inici().compareTo(t.getData_inici())==1) ++i;
		if (mateix_torn(c.get(i),t)) c.remove(i);
		else System.out.println("No existeix el torn proporcionat");
	}
	
	public boolean mateix_torn(Torn t1, Torn t2) {
		if(t1.getData_inici().compareTo(t2.getData_inici())!=0 || t1.getData_fi().compareTo(t2.getData_fi())!=0 || t1.getN_min_doc()!=t2.getN_min_doc() || t1.getDoc_assignats()!=t2.getDoc_assignats() || t1.getPercent_sou()!=t2.getPercent_sou()) {
			return false;
		}
		return true;
	}
	
	public int posicio_torn(Torn t) {
		GregorianCalendar d_inici = t.getData_inici();
		GregorianCalendar d_fi = t.getData_fi();
		int i=0;
		while (i!=-1 && i<c.size() && c.get(i).getData_inici().before(d_fi)) {
			if (d_inici.before(c.get(i).getData_inici()) || (d_inici.before(c.get(i).getData_fi()))){
				System.out.println("El torn es solapa amb un altre torn ja existent");
				i=-1;
			}
			else ++i;
		}
		return i;
	}
    
}
