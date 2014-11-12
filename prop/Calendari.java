/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;

public class Calendari {
	
	private int id;
	private int id_plantilla;
	private ArrayList<Torn> c;
	
	/** 
	 * Creació d'un nou calendari
	 */
	public Calendari(int ident, int ident_plantilla) {
		id = ident;
		id_plantilla = ident_plantilla;
		c = new ArrayList<Torn>();
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
    
}
