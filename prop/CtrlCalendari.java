package prop;

import java.util.GregorianCalendar;
import java.util.TreeSet;

public class CtrlCalendari {
	
	private static TreeSet<Calendari> llcalendaris = new TreeSet<Calendari>(new compIdP());
	
	//-------- CALENDARIS EN GENERAL -------
	
	//Pre: La plantilla plt no té calendari associat
	//Post: llcalendaris ara té un nou calendari que pertany a la plantilla plt
	public static Calendari CrearIAfegirCalendari(String plt, int any) {
		Calendari c = new Calendari(plt,any,any);
		llcalendaris.add(c);
		return c;
	}
	
	//Pre: La plantilla plt no té calendari associat
	//Post: llcalendaris ara té un nou calendari que pertany a la plantilla plt
	public static void afegirCalendari(String plt, int any) {
		Calendari c = new Calendari(plt,any,any);
		llcalendaris.add(c);
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
	
	//Pre: -
	//Post: Retorna tots els calendaris existents
	public static TreeSet<Calendari> getLlcalendaris() {
		return llcalendaris;
	}
	
	
	//Pre: -
	//Post: Retorna el calendari de la plantilla plt
	public static Calendari consultarCalendari(String plt) {
		Calendari c = new Calendari(plt,0,0);
		return llcalendaris.ceiling(c);
	}
	
	
	
	//--------- FUNCIONS PER UN CALENDARI CONCRET ----------
	
	//1. -----FUNCIONS SOBRE ELS TORNS-------
	public static void afegirTorn(Torn t, GregorianCalendar dia, String plt) {
		Calendari c = consultarCalendari(plt);
		c.afegirTornDia(t,dia);
	}
	
	public static void eliminarTorn(int tipustorn, GregorianCalendar dia, String plt) {
		Calendari c = consultarCalendari(plt);
		c.eliminarTornDia(tipustorn,dia);
	}
	
	public static Torn consultarTorn(int tipustorn, GregorianCalendar dia, String plt) {
		Calendari c = consultarCalendari(plt);
		return c.consultarTornDia(tipustorn,dia);
	}
	
	public static void modificarTorn(Torn t, GregorianCalendar dia, String plt) {
		Calendari c = consultarCalendari(plt);
		c.modificarTornDia(t,dia);
	}
	
	//2. ------FUNCIONS SOBRE DIES--------
	
}