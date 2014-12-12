package prop;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class DriverCtrlCalendari {
	
	private static CtrlCalendari ctr = new CtrlCalendari();
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
				case 1: crearCalendari(); break;
				case 2: borrarCalendari(); break;
				case 3: existeixCalendari(); break;
				case 4: consultarCalendari(); break;
				default:
					System.out.println("El numero ha d'estar entre 0 i 6.");
					break;
			}
			escriureMenu();
		}
	}
	
	public static void escriureMenu() {
		System.out.println("\n\n----------Menú----------"
				+ "1.- Crear Calendari(identificador plantilla: String, any inici: int, any fi: int)\n"
				+ "--------------------------\n"
				+ "2.- Borrar calendari(Id plantilla: String)\n"
				+ "--------------------------\n"
				+ "3.- Existeix Calendari(id plantilla: String)\n"
				+ "4.- Consultar Calendari(id plantilla: String)\n"
				+ "--------------------------\n"
				+ "0.- Exit\n\n");		
	}
	
	public static void crearCalendari() {
		String plt = arg.next();
		int any_i = arg.nextInt();
		ctr.afegirCalendari(plt,any_i);
	}
	
	public static void borrarCalendari() {
		String plt = arg.next();
		ctr.EsborrarCalendari(plt);
	}
	
	public static void existeixCalendari() {
		String plt = arg.next();
		if(ctr.existeixCalendari(plt)) System.out.println("La plantilla plt té calendari");
		else System.out.println("La plantilla plt no té calendari associat");
	}
	
	public static void consultarCalendari() {
		String plt = arg.next();
		Calendari c = ctr.consultarCalendari(plt);
		for(int i=0; i<c.getCalendari().length; ++i){
			Dia d = c.getCalendari()[i];
			GregorianCalendar data = c.quinDia(i);
			System.out.println("El dia "+data.getTime().toString());
			if(d.getFestiu())System.out.println("Es festiu");
			else System.out.println("No es festiu");
			Torn t;
			String s;
			for(int j=0; j<3; ++j) {
				t = d.getTorn_concret(j);
				if(j==0) s="Mati";
				else if(j==1) s="Tarda";
				else s="Nit";
				System.out.println("Torn de "+s+":\n"
						+ "Hora inici: "+t.getHora_inici()+"\n"
						+ "Hora fi: "+t.getHora_fi()+"\n"
						+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
						+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
			}
		}
	}
	
	
	
}