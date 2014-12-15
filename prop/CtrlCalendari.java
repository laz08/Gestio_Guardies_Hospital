package prop;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeSet;

public class CtrlCalendari {
	
	private static TreeSet<Calendari> llcalendaris = new TreeSet<Calendari>(new compIdP());
	
	//-------- CALENDARIS EN GENERAL -------
	
	//Pre: La plantilla plt no té calendari associat
	//Post: llcalendaris ara té un nou calendari que pertany a la plantilla plt
	public static Calendari CrearIAfegirCalendari(String plt, int anyi, int anyf) {
		Calendari c = new Calendari(plt,anyi,anyf);
		llcalendaris.add(c);
		return c;
	}
	
	//Pre: La plantilla plt no té calendari associat
	//Post: llcalendaris ara té un nou calendari que pertany a la plantilla plt
	public static void crearIafegirCalendari(String plt, int any, int anyf) {
		Calendari c = new Calendari(plt,any,anyf);
		llcalendaris.add(c);
	}
	
	//Pre: La plantilla plt no té calendari associat
	//Post: llcalendaris ara té un nou calendari que pertany a la plantilla plt
	public static void afegirCalendari(String plt, int anyi, int anyf) {
		Calendari c = new Calendari(plt,anyi,anyf);
		llcalendaris.add(c);
	}
	
	//Pre:-
	//Post: s'afegeix calendari al treeset llcalendaris
	public static void afegirCalendarif(Calendari c) {
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
	
	//Pre: -
	//Post: Guardem llcalendaris calendari
	public static void guardar() {
		String content = "";
		String fi = "Fi";
		for(Calendari c: llcalendaris) {
			content += c.getPlantillaAssociada() + "\n" + c.getAny() + "\n";
			for (Dia d: c.getCalendari()) {
				content += d.getFestiu() + "\n";
				for (Torn t: d.getTorns()) {
					content += t.getHora_inici() + " " + t.getHora_fi() + " " 
					+ t.getPercent_sou() + " " + t.getMin_num_doctors() + "\n";
				}
			}
			content += (fi + "\n");
		}
		CtrlPersistencia.guardar(content, "Calendaris");
	}
	
	//Pre: -
	//Post: Carreguem la llcalendaris
	public static void carregar() {
		String content = CtrlPersistencia.carregar("Calendaris");
		String separadors = "[ \n]";
    	String[] separat = content.split(separadors);
    	int i = 0;
    	while (i < separat.length && separat[i].compareTo("Fi")!= 0) {
    		System.out.println("SEPARAT "+ i);
    		if(!CtrlPlantilla.existeixPlantilla(separat[i])) CtrlPlantilla.creariAfegirPlantilla(separat[i]);
    		Calendari c = new Calendari(separat[i], Integer.parseInt(separat[i+1]), Integer.parseInt(separat[i+1]));
    		i += 2;
    		Dia[] any = c.getCalendari();
    		int j = 0;
    		for (j = 0; j < any.length && separat[i].compareTo("Fi")!= 0; j++) {
    			System.out.println("BOOL " +j +" "+ separat[i]);
    			any[j].setFestiu(Boolean.parseBoolean(separat[i]));
    			i++;
                for(int e=0; e<3; e++){
                	System.out.println(j + " " + e +" "+ separat[i]);
                	System.out.println(j + " " + e +" "+ separat[i+1]);
                	System.out.println(j + " " + e +" "+ separat[i+2]);
                	System.out.println(j + " " + e +" "+ separat[i+3]);
                	Torn t;
                    if(Integer.parseInt(separat[i])==0) {
                    	t = new Torn(0,Float.parseFloat(separat[i+2]), Integer.parseInt(separat[i+3]));
                    }
                    else if(Integer.parseInt(separat[i])==1){
                    	t = new Torn(1,Float.parseFloat(separat[i+2]), Integer.parseInt(separat[i+3]));
                    }
                    else {
                    	t = new Torn(2,Float.parseFloat(separat[i+2]), Integer.parseInt(separat[i+3]));
                    }
                        i+=4;
                    	switch(e){
                            case 0:
                                any[j].setTornMati(t);
                                break;
                            case 1:
                                any[j].setTornTarda(t);
                                break;
                            case 2:
                                any[j].setTornNit(t);
                                break;
                        }
                }
            }
    		c.setCalendari(any);
            CtrlCalendari.afegirCalendarif(c);
    	}
    	
	}
	
	//------------ FUNCIONS PER UN CALENDARI CONCRET ----------
	
	//Pre: -
	//Post: Es canvia la plantilla associada al calendari de la plantilla plt per la plantilla pltnova
	public static void modificarPlt(String plt, String pltnova) {
		if(! existeixCalendari(pltnova)) consultarCalendari(plt).setPlantillaAssociada(pltnova);	
	}
	
	//---------- FUNCIONS PER UN DIA CONCRET D'UN CALENDARI -----------
	
	//Pre: Dia pertany a una data del calendari de la plantilla plt
	//Post: Borra la informació que hi ha al dia (dia)
	public static void borrarDia(String plt, GregorianCalendar dia) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		consultarCalendari(plt).getCalendari()[pos] = null;
	}
	
	//Pre: Dia pertany a una data del calendari de la plantilla plt 
	//Post: Retorna tota la informació del dia (dia) del calendari associat a la plantilla plt
	public static Dia consultarDia(String plt, GregorianCalendar dia) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		return consultarCalendari(plt).getCalendari()[pos];
	}
	
	//Pre: Dia pertany a una data del calendari de la plantilla plt
	//Post: Retorna un boolea dient si el dia(dia) és festiu o no
	public static boolean consultarDiaFestiu(String plt, GregorianCalendar dia) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		return(consultarCalendari(plt).getCalendari()[pos].getFestiu());
	}
	
	//Pre: Dia pertany a una data del calendari de la plantilla plt
	//Post: Retorna els tres torns que hi ha al dia(dia) del calendari associat a la plantilla plt
	public static Torn[] consultarTornsDia(String plt, GregorianCalendar dia) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		return consultarCalendari(plt).getCalendari()[pos].getTorns();
	}
	
	//Pre: Dia pertany a una data del calendari de la plantilla plt
	//Post: Es modifica tota la informació del dia(dia) del calendari de plt per el nou dia d
	public static void modificarDia(String plt, GregorianCalendar dia, Dia d) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		consultarCalendari(plt).getCalendari()[pos]=d;
	}
	
	//Pre: Dia pertany a una data del calendari de la plantilla plt
	//Post: Es modifica el boolea de festiu del dia(dia) del calendari de plt pel boolea que li arriba
	public static void modificarDiaFestiu(String plt, GregorianCalendar dia, boolean b) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		consultarCalendari(plt).getCalendari()[pos].setFestiu(b);
	}
	
	//Pre: Dia pertany a una data del calendari de la plantilla plt, ts vector de 3 torns
	//Post: Modificar els torns del dia (dia) del calendari de la plantilla plt pels torns ts
	public static void modificarTornsDia(String plt, GregorianCalendar dia, Torn[] ts) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		consultarCalendari(plt).getCalendari()[pos].setTorns(ts);
	}

	
	//-------- FUNCIONS PER UN TORN EN CONCRET D'UN DIA I UN CALENDARI -----
	
	/*public static void borrarTorn(String plt, GregorianCalendar dia, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		if(horari==0) consultarCalendari(plt).getCalendari()[pos].setTornMati(null);
		else if(horari==1) consultarCalendari(plt).getCalendari()[pos].setTornTarda(null);
		else if(horari==2) consultarCalendari(plt).getCalendari()[pos].setTornNit(null);

	}*/
	
	//Pre: Dia pertany a una data del calendari i horari es un enter entre 0 i 2
	//Post: Borra la informació del torn d'horari(mati=0, tarda=1 o nit=2) del dia (dia) del calendari de la plantilla plt
	public static void borrarTornHorari(String plt, GregorianCalendar dia, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		consultarCalendari(plt).getCalendari()[pos].getTorns()[horari]=null;
	}
	
	//Pre: tipustorn es un enter entre 0 i 2, i dia es una data que pertany al calendari associat de plt
	//Post: Retorna tota la informació del torn d'horari(mati=0, tarda=1 o nit=2) del dia (dia) del calendari de la plantilla plt
	public static Torn consultarTorn(GregorianCalendar dia, String plt, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		if(horari==0) return consultarCalendari(plt).getCalendari()[pos].getTornMati();
		else if(horari==1) return consultarCalendari(plt).getCalendari()[pos].getTornTarda();
		else return consultarCalendari(plt).getCalendari()[pos].getTornNit();
	}

	//Pre: tipustorn es un enter entre 0 i 2, i dia es una data que pertany al calendari associat de plt
	//Post: Retorna l'hora d'inici del torn d'horari(mati=0, tarda=1 o nit=2) del dia (dia) del calendari de la plantilla plt
	public static int consultarHoraIniciTorn(GregorianCalendar dia, String plt, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		if(horari==0) return consultarCalendari(plt).getCalendari()[pos].getTornMati().getHora_inici();
		else if(horari==1) return consultarCalendari(plt).getCalendari()[pos].getTornTarda().getHora_inici();
		else return consultarCalendari(plt).getCalendari()[pos].getTornNit().getHora_inici();
	}
	
	//Pre: tipustorn es un enter entre 0 i 2, i dia es una data que pertany al calendari associat de plt
	//Post: Retorna l'hora d'inici del torn d'horari(mati=0, tarda=1 o nit=2) del dia (dia) del calendari de la plantilla plt
	public static int consultarHoraFiTorn(GregorianCalendar dia, String plt, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		if(horari==0) return consultarCalendari(plt).getCalendari()[pos].getTornMati().getHora_fi();
		else if(horari==1) return consultarCalendari(plt).getCalendari()[pos].getTornTarda().getHora_fi();
		else return consultarCalendari(plt).getCalendari()[pos].getTornNit().getHora_fi();
	}
	
	//Pre: tipustorn es un enter entre 0 i 2, i dia es una data que pertany al calendari associat de plt
	//Post: Retorna el percentatge de sou del torn d'horari(mati=0, tarda=1 o nit=2) del dia (dia) del calendari de la plantilla plt
	public static float consultarPercentatgeTorn(GregorianCalendar dia, String plt, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		if(horari==0) return consultarCalendari(plt).getCalendari()[pos].getTornMati().getPercent_sou();
		else if(horari==1) return consultarCalendari(plt).getCalendari()[pos].getTornTarda().getPercent_sou();
		else return consultarCalendari(plt).getCalendari()[pos].getTornNit().getPercent_sou();
	}
	
	//Pre: tipustorn es un enter entre 0 i 2, i dia es una data que pertany al calendari associat de plt
	//Post: Retorna el numero minim de doctors del torn d'horari(mati=0, tarda=1 o nit=2) del dia (dia) del calendari de la plantilla plt
	public static int consultarMinimTorn(GregorianCalendar dia, String plt, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		if(horari==0) return consultarCalendari(plt).getCalendari()[pos].getTornMati().getMin_num_doctors();
		else if(horari==1) return consultarCalendari(plt).getCalendari()[pos].getTornTarda().getMin_num_doctors();
		else return consultarCalendari(plt).getCalendari()[pos].getTornNit().getMin_num_doctors();
	}
	
	//Pre: Dia pertany a una data del calendari i horari es un enter entre 0 i 2
	//Post: Modifica tota la informació del torn d'horari(mati=0, tarda=1 o nit=2) del dia (dia) del calendari de la plantilla plt pel torn t
	public static void modificarTorn(Torn t, GregorianCalendar dia, String plt, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		if(horari==0) consultarCalendari(plt).getCalendari()[pos].setTornMati(t);
		else if(horari==1) consultarCalendari(plt).getCalendari()[pos].setTornTarda(t);
		else if(horari==2) consultarCalendari(plt).getCalendari()[pos].setTornNit(t);

	}

	//Pre: Dia pertany a una data del calendari i horari es un enter entre 0 i 2
	//Post: Modifica el percentatge de sou del torn d'horari(mati=0, tarda=1 o nit=2) del dia (dia) del calendari de la plantilla plt pel percentatge p
	public static void modificarPercentatgeTorn(float p, GregorianCalendar dia, String plt, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		if(horari==0) consultarCalendari(plt).getCalendari()[pos].getTornMati().setPercent_sou(p);
		else if(horari==1) consultarCalendari(plt).getCalendari()[pos].getTornTarda().setPercent_sou(p);
		else if(horari==2) consultarCalendari(plt).getCalendari()[pos].getTornNit().setPercent_sou(p);

	}
	
	//Pre: Dia pertany a una data del calendari i horari es un enter entre 0 i 2
	//Post: Modifica el numero minim de doctors del torn d'horari(mati=0, tarda=1 o nit=2) del dia (dia) del calendari de la plantilla plt pel nou minim m
	public static void modificarMinimTorn(int m, GregorianCalendar dia, String plt, int horari) {
		int pos = calcularPosicioDia(dia,consultarCalendari(plt).getAny());
		if(horari==0) consultarCalendari(plt).getCalendari()[pos].getTornMati().setMin_num_doctors(m);
		else if(horari==1) consultarCalendari(plt).getCalendari()[pos].getTornTarda().setMin_num_doctors(m);
		else if(horari==2) consultarCalendari(plt).getCalendari()[pos].getTornNit().setMin_num_doctors(m);

	}

	

	
	// ----------- FUNCIONS AUXILIARS -------------

	
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