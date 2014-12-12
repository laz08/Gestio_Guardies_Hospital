package prop;

import java.util.Calendar;
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
	public static void eliminarCalendari(String plt) {
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
	
	public static void modificarPlt(String plt, String pltnova) {
		if(! existeixCalendari(pltnova)) consultarCalendari(plt).setPlantillaAssociada(pltnova);	
	}
	
	public static void borrarDia(String plt, GregorianCalendar dia) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		consultarCalendari(plt).getCalendari()[pos] = null;
	}

	public static void borrarTornHorari(String plt, GregorianCalendar dia, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		consultarCalendari(plt).getCalendari()[pos].getTorns()[horari]=null;
	}
	
	public static Dia consultarDia(String plt, GregorianCalendar dia) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		return consultarCalendari(plt).getCalendari()[pos];
	}
	
	public static boolean consultarDiaFestiu(String plt, GregorianCalendar dia) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		return(consultarCalendari(plt).getCalendari()[pos].getFestiu());
	}
	
	public static Torn[] consultarTornsDia(String plt, GregorianCalendar dia) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		return consultarCalendari(plt).getCalendari()[pos].getTorns();
	}
	
	public static Torn consultarTorn(int tipustorn, GregorianCalendar dia, String plt) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		if(tipustorn==0) return consultarCalendari(plt).getCalendari()[pos].getTornMati();
		else if(tipustorn==1) return consultarCalendari(plt).getCalendari()[pos].getTornTarda();
		else return consultarCalendari(plt).getCalendari()[pos].getTornNit();
	}
	
	public static void modificarDia(String plt, GregorianCalendar dia, Dia d) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		consultarCalendari(plt).getCalendari()[pos]=d;
	}
	
	public static void modificarDiaFestiu(String plt, GregorianCalendar dia, boolean b) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		consultarCalendari(plt).getCalendari()[pos].setFestiu(b);
	}
	
	public static void modificarTornsDia(String plt, GregorianCalendar dia, Torn[] ts) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		consultarCalendari(plt).getCalendari()[pos].setTorns(ts);
	}
	
	public static void borrarTorn(String plt, GregorianCalendar dia, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		if(horari==0) consultarCalendari(plt).getCalendari()[pos].setTornMati(null);
		else if(horari==1) consultarCalendari(plt).getCalendari()[pos].setTornTarda(null);
		else if(horari==2) consultarCalendari(plt).getCalendari()[pos].setTornNit(null);

	}

	public static void modificarTorn(Torn t, GregorianCalendar dia, String plt, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		if(horari==0) consultarCalendari(plt).getCalendari()[pos].setTornMati(t);
		else if(horari==1) consultarCalendari(plt).getCalendari()[pos].setTornTarda(t);
		else if(horari==2) consultarCalendari(plt).getCalendari()[pos].setTornNit(t);

	}
	
	public static void modificarPercentatgeTorn(float p, GregorianCalendar dia, String plt, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		if(horari==0) consultarCalendari(plt).getCalendari()[pos].getTornMati().setPercent_sou(p);
		else if(horari==1) consultarCalendari(plt).getCalendari()[pos].getTornTarda().setPercent_sou(p);
		else if(horari==2) consultarCalendari(plt).getCalendari()[pos].getTornNit().setPercent_sou(p);

	}
	
	public static void modificarMinimTorn(int m, GregorianCalendar dia, String plt, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		if(horari==0) consultarCalendari(plt).getCalendari()[pos].getTornMati().setMin_num_doctors(m);
		else if(horari==1) consultarCalendari(plt).getCalendari()[pos].getTornTarda().setMin_num_doctors(m);
		else if(horari==2) consultarCalendari(plt).getCalendari()[pos].getTornNit().setMin_num_doctors(m);

	}
	


	

	
	//2. ------FUNCIONS SOBRE DIES--------

	
	//Pre: dia pertany a l'any
	//Post: Ens retorna la posició on es troba el dia en qüestió en el nostre calendari
	public static int calcularPosicioDia(GregorianCalendar dia, int any) {
		GregorianCalendar primerdia = new GregorianCalendar(any,1,1);
		long dif = primerdia.getTimeInMillis() - dia.getTimeInMillis();
		dif = dif/1000/60/60/24;
		return (int) dif;
		
	}
	
	//Pre: i pertany a una posicio del vector
	//Post: Retorna la data que te la posicio i al vector.
	public static GregorianCalendar quinDia(int i, int any) {
		GregorianCalendar primerdia = new GregorianCalendar(any,1,1);
		primerdia.add(Calendar.DAY_OF_YEAR,i);
		return primerdia;		
	}
	
	
}