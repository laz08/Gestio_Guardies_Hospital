package prop;

import java.util.TreeSet;

public class CtrlCalendari {
	
	private static TreeSet<Calendari> llcalendaris = new TreeSet<Calendari>(new compIdP());
	
	//Pre: La plantilla plt no té calendari associat
	//Post: llcalendaris ara té un nou calendari que pertany a la plantilla plt
	public static Calendari CrearIAfegirCalendari(String plt, int any) {
		Calendari c = new Calendari(plt,any,any);
		llcalendaris.add(c);
		return c;
	}
	
	//Pre: -
	//Post: S'ha esborrat el calendari associat 
	public static void EsborrarCalendari(String plt) {
		Calendari c = new Calendari(plt,0,0);
		llcalendaris.remove(c);
	}
	
	//Pre: -
	//Post: Retorna un boolea indicant si el calendari de la plantilla plt existeix o no
	public static boolean existeixCalendari(String plt) {
		Calendari aux = new Calendari(plt,0,0);
		return llcalendaris.contains(aux);
	}
	
	public static TreeSet<Calendari> getLlcalendaris() {
		return llcalendaris;
	}
	
	
	//Pre: -
	//Post: Retorna el calendari de la plantilla plt
	public static Calendari consultarCalendari(String plt) {
		Calendari c = new Calendari(plt,0,0);
		return llcalendaris.ceiling(c);
	}
}