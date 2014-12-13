package prop;

import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.TreeSet;

public class DriverCtrlCalendari {
	
	//private static CtrlCalendari ctr = new CtrlCalendari();
	static Scanner arg = new Scanner(System.in);
	
	public static void main(String[] args) throws Error {
		
		int cas;
		boolean sortir = false;
		escriureMenu();
		
		while(!sortir && arg.hasNext()) {
			try {
				cas = arg.nextInt();
			} catch (Exception e) {
				System.out.println("s'esperava la introducció d'un número");
				System.out.print(">>");
				arg.next();
				continue;
			}				
				
			switch(cas) {
				case 0: sortir = true; break;
				
				case 1: consultarLlistaCalendaris(); break;
				
				case 2:	crearCalendari(); break;
				case 3: eliminarCalendari(); break;
				case 4: existeixCalendari(); break;
				case 5: consultarCalendari(); break;
				case 6: consultarAnyCalendari(); break;
				case 7: modificarPltCalendari(); break;
				
				case 8: borrarDia(); break;
				case 9: borrarTornMati(); break;
				case 10: borrarTornTarda(); break;
				case 11: borrarTornNit(); break;
				case 12: consultarDia(); break;
				case 13: consultarDiaFestiu(); break;
				case 14: consultarTornsDia(); break;
				case 15: consultarTornMati(); break;
				case 16: consultarTornTarda(); break;
				case 17: consultarTornNit(); break;
				case 18: modificarDia(); break;
				case 19: modificarDiaFestiu(); break;
				
				case 20: modificarTornsDia(); break;
				case 21: borrarTorn(); break;
				case 22: consultarTorn(); break;
				case 23: consultarHoraIniciTorn(); break;
				case 24: consultarHoraFiTorn(); break;
				case 25: consultarPercentatgeTorn(); break;
				case 26: consultarMinimTorn(); break;
				case 27: modificarTorn(); break;
				case 28: modificarPercentatgeTorn(); break;
				case 29: modificarMinimTorn(); break;
				case 30: guardar(); break;
				case 31: carregar(); break;
				default:
					System.out.println("El numero ha d'estar entre 0 i 31.");
					break;
			}
			escriureMenu();
		}
	}
	
	public static void escriureMenu() {
		System.out.println("\n\n----------Menú----------\n"
				+ "CONJUNT CALENDARIS\n"
				+ "1.- Consultar llista de plantilles que tenen calendari\n"
				+ "CALENDARI\n"
				+ "2.- Crear Calendari(identificador plantilla: String, any inici: int, any fi: int)\n"
				+ "3.- Eliminar Calendari(id plantilla: String)\n"
				+ "4.- Existeix Calendari?(id plantilla: String)\n"
				+ "5.- Consultar Calendari(id plantilla: String)\n"
				+ "6.- Consultar any inici Calendari(id plantilla: String)"
				+ "7.- Modificar Calendari-> canviem plantilla associada(id plantilla: String, id plantilla nova: String)\n"
				+ "DIA\n"
				+ "8.- Borrar(informació) Dia(id plantilla: String, data(any,mes,dia))\n"
				+ "9.- Borrar(informació) torn mati(id plantilla: String, data(any,mes,dia))\n"
				+ "10.- Borrar(informació) torn tarda(id plantilla: String, data(any,mes,dia))\n"
				+ "11.- Borrar(informació) torn nit(id plantilla: String, data(any,mes,dia))\n"
				+ "12.- Consultar Dia(id plantilla: String, data(any,mes,dia))\n"
				+ "13.- Consultar Dia Festiu?(id plantilla: String, data(any,mes,dia))\n"
				+ "14.- Consultar torns Dia(id plantilla: String, data(any,mes,dia))\n"
				+ "15.- Consultar torn mati(id plantilla: String, data(any,mes,dia))\n"
				+ "16.- Consultar torn tarda(id plantilla: String, data(any,mes,dia))\n"
				+ "17.- Consultar torn nit(id plantilla: String, data(any,mes,dia))\n"
				+ "18.- Modificar Dia(id plantilla: String, data(any,mes,dia), nou Dia(festiu? i 3 torns)\n"
				+ "19.- Modificar Dia festiu(id plantilla: String, data(any,mes,dia), boolea festiu)\n"
				+ "20.- Modificar torns del Dia(id plantilla: String, data(any,mes,dia), 3 Torns)\n"
				+ "TORN\n"
				+ "21.- Borrar(informació) Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2))\n"
				+ "22.- Consultar Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2))\n"
				+ "23.- Consultar hora inici Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2))\n"
				+ "24.- Consultar hora fi Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2))\n"
				+ "25.- Consultar percentatge sou del Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2))\n"
				+ "26.- Consultar numero minim de doctors del Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2))\n"
				+ "27.- Modificar Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2), nou Torn)\n"
				+ "28.- Modificar percentatge sou del Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2), nou percentatge)\n"
				+ "29.- Modificar numero mínim de doctors(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2), nou mínim de doctors)\n"
				+ "--------------------------\n"
				+ "30.- Guardar\n"
				+ "31.- Carregar\n"
				+ "0.- Exit\n\n");		
	}
	
	public static void consultarLlistaCalendaris() {
        TreeSet<Calendari> Cjt = CtrlCalendari.getLlcalendaris();
        for(Calendari c:Cjt)
            System.out.println("La plantilla: "+c.getPlantillaAssociada()+" té un calendari de l'any "+c.getAny());
	}
	
	public static void crearCalendari() {
		String plt = arg.next();
		int any_i = arg.nextInt();
		CtrlCalendari.afegirCalendari(plt,any_i);
	}
	
	public static void eliminarCalendari() {
		String plt = arg.next();
		CtrlCalendari.eliminarCalendari(plt);
	}
	
	public static void existeixCalendari() {
		String plt = arg.next();
		if(CtrlCalendari.existeixCalendari(plt)) System.out.println("La plantilla plt té calendari");
		else System.out.println("La plantilla plt no té calendari associat");
	}
	
	public static void consultarCalendari() {
		String plt = arg.next();
		Calendari c = CtrlCalendari.consultarCalendari(plt);
		for(int i=0; i<c.getCalendari().length; ++i){
			Dia d = c.getCalendari()[i];
			escriureDia(d,CtrlCalendari.quinDia(i,c.getAny()));
		}
	}
	
	public static void consultarAnyCalendari() {
		String plt = arg.next();
		System.out.println(CtrlCalendari.consultarCalendari(plt).getAny());
	}
	
	public static void modificarPltCalendari() {
		String plt = arg.next();
		String pltnova = arg.next();
		CtrlCalendari.modificarPlt(plt,pltnova);
	}
	
	public static void borrarDia() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		CtrlCalendari.borrarDia(plt,data);
	}
	
	public static void borrarTornMati() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		CtrlCalendari.borrarTornHorari(plt,data,0);
	}
	
	public static void borrarTornTarda() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		CtrlCalendari.borrarTornHorari(plt,data,1);
	}
	
	public static void borrarTornNit() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		CtrlCalendari.borrarTornHorari(plt,data,2);
	}
	
	public static void consultarDia() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		Dia d = CtrlCalendari.consultarDia(plt,data);
		escriureDia(d,data);
	}
	
	public static void consultarDiaFestiu() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		if(CtrlCalendari.consultarDiaFestiu(plt,data)) System.out.println("El dia "+data.getTime().toString()+"és festiu");
		else System.out.println("El dia "+data.getTime().toString()+" no és festiu");
	}
	
	public static void consultarTornsDia() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		Torn[] ts = CtrlCalendari.consultarTornsDia(plt,data);
		for(int i=0; i<3; ++i) escriureTorn(ts[i],i);
	}
	
	public static void consultarTornMati() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		Torn t = CtrlCalendari.consultarTorn(data, plt, 0);
		escriureTorn(t,0);
	}
	
	public static void consultarTornTarda() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		Torn t = CtrlCalendari.consultarTorn(data, plt, 1);
		escriureTorn(t,1);
	}
	
	public static void consultarTornNit() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		Torn t = CtrlCalendari.consultarTorn(data, plt, 2);
		escriureTorn(t,2);
	}
	
	public static void modificarDia() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		boolean b = arg.nextBoolean();
		int n = arg.nextInt();
		Dia d = new Dia(b);
		for(int i=0; i<n; ++i) {
			int hora_i = arg.nextInt();
			int hora_f = arg.nextInt();
			float percent = arg.nextFloat();
			int minim = arg.nextInt();
			Torn t = new Torn(hora_i,hora_f,percent,minim);
			if(i==0) d.setTornMati(t);
			else if(i==1) d.setTornTarda(t);
			else d.setTornNit(t);
		}
		CtrlCalendari.modificarDia(plt,data,d);
	}
	
	public static void modificarDiaFestiu() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		boolean b = arg.nextBoolean();
		CtrlCalendari.modificarDiaFestiu(plt,data,b);
	}
	
	public static void modificarTornsDia() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		Torn[] ts = new Torn[3];
		for(int i=0; i<3; ++i) {
			int hora_i = arg.nextInt();
			int hora_f = arg.nextInt();
			float percent = arg.nextFloat();
			int minim = arg.nextInt();
			Torn t = new Torn(hora_i,hora_f,percent,minim);
			ts[i] = t;
		}
		CtrlCalendari.modificarTornsDia(plt,data,ts);
	}
	
	public static void borrarTorn() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int horari = arg.nextInt();
		CtrlCalendari.borrarTornHorari(plt,data,horari);
	}
	
	public static void consultarTorn() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int horari = arg.nextInt();
		Torn t = CtrlCalendari.consultarTorn(data, plt, horari);
		escriureTorn(t,horari);
	}
	
	public static void consultarHoraIniciTorn() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int horari = arg.nextInt();
		int hora = CtrlCalendari.consultarHoraIniciTorn(data, plt, horari);
		System.out.println(hora);
	}
	
	public static void consultarHoraFiTorn() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int horari = arg.nextInt();
		int hora = CtrlCalendari.consultarHoraFiTorn(data, plt, horari);
		System.out.println(hora);
	}
	
	public static void consultarPercentatgeTorn() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int horari = arg.nextInt();
		float percent = CtrlCalendari.consultarPercentatgeTorn(data, plt, horari);
		System.out.println(percent);
	}
	
	public static void consultarMinimTorn() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int horari = arg.nextInt();
		int minim = CtrlCalendari.consultarMinimTorn(data, plt, horari);
		System.out.println(minim);
	}
	
	public static void modificarTorn() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int horari = arg.nextInt();
		int hora_i = arg.nextInt();
		int hora_f = arg.nextInt();
		float percent = arg.nextFloat();
		int minim = arg.nextInt();
		Torn t = new Torn(hora_i,hora_f,percent,minim);
		CtrlCalendari.modificarTorn(t, data, plt, horari);
	}

	public static void modificarPercentatgeTorn() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int horari = arg.nextInt();
		float percent = arg.nextFloat();
		CtrlCalendari.modificarPercentatgeTorn(percent, data, plt, horari);
	}
	
	public static void modificarMinimTorn() {
		String plt = arg.next();
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int horari = arg.nextInt();
		int minim = arg.nextInt();
		CtrlCalendari.modificarMinimTorn(minim, data, plt, horari);
	}
	
	public static void guardar() {
		CtrlCalendari.guardar();
	}
	
	public static void carregar() {
		CtrlCalendari.carregar();
	}
	
	public static void escriureTorn(Torn t, int i) {
		String s;
		if(i==0) s="Mati";
		else if(i==1) s="Tarda";
		else s="Nit";
	
		System.out.println("Torn de "+s+":\n"
				+ "Hora inici: "+t.getHora_inici()+"\n"
				+ "Hora fi: "+t.getHora_fi()+"\n"
				+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
				+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
	
	}
	
	public static void escriureDia(Dia d, GregorianCalendar data) {
		String da = data.getTime().toString();
		System.out.println("El dia "+da);
		if(d.getFestiu())System.out.println("Es festiu");
		else System.out.println("No es festiu");
		Torn t;
		String s;
		for(int i=0; i<3; ++i) {
			if(i==0) {
				s="Mati";
				t = d.getTornMati();
			}
			else if(i==1) {
				s="Tarda";
				t = d.getTornTarda();
			}
			else {
				s="Nit";
				t = d.getTornNit();
			}
			System.out.println("Torn de "+s+":\n"
					+ "Hora inici: "+t.getHora_inici()+"\n"
					+ "Hora fi: "+t.getHora_fi()+"\n"
					+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
					+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
		}
	}

	
}