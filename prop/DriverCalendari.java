package prop;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class DriverCalendari {
	
	private static Calendari c = new Calendari(null,0,0);
	static Scanner arg = new Scanner(System.in);
	static boolean primer = true;	
	
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
				case 2: modificarPlantilla(); break;
				case 3: consultarCalendari(); break;
				case 4: consultarPlantilla(); break;
				case 5: consultarDiaConcret(); break;
				case 6: borrarDiaConcret(); break;
				case 7: borrar(); break;
				default:
					System.out.println("El numero ha d'estar entre 0 i 7.");
					break;
				
			}
			escriureMenu();
		}
	}
	
	public static void escriureMenu() {
		System.out.println("\n\n----------Menú----------"
				+ "1.- Crear Calendari(identificador plantilla: String, any inici: int, any fi: int)\n"
				+ "--------------------------\n"
				+ "2.- Modificar Plantilla associada(identificador plantilla nova: String)\n"
				+ "--------------------------\n"
				+ "3.- Consultar Calendari\n"
				+ "4.- Consultar Plantilla associada\n"
				+ "5.- Consultar dia concret (any, mes dia)\n"
				+ "--------------------------\n"
				+ "6.- Borrar dia concret (any, mes, dia)\n"
				+ "7.- Borrar calendari\n"
				+ "--------------------------\n"
				+ "0.- Exit\n\n");
	}
	
	public static void crearCalendari() {
		String plt = arg.next();
		int any_i = arg.nextInt();
		int any_fi = arg.nextInt();
		c = new Calendari(plt,any_i,any_fi);
	}
	
	/**public static void modificarIdentificador() {
		int id = arg.nextInt();
		c.setIdentificador(id);
	}*/
	
	public static void modificarPlantilla() {
		String pltilla = arg.next();
		c.setPlantillaAssociada(pltilla);
	}
	
	public static void consultarCalendari() {
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
	
	public static void consultarPlantilla() {
		System.out.println(c.getPlantillaAssociada());
	}
	
	public static void consultarDiaConcret() {
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		Dia d = c.consultarDiaConcret(data);
		System.out.println("El dia "+dia+"/"+mes+"/"+any);
		if(d.getFestiu())System.out.println("Es festiu");
		else System.out.println("No es festiu");
		Torn t;
		String s;
		for(int i=0; i<3; ++i) {
			t = d.getTorn_concret(i);
			if(i==0) s="Mati";
			else if(i==1) s="Tarda";
			else s="Nit";
			System.out.println("Torn de "+s+":\n"
					+ "Hora inici: "+t.getHora_inici()+"\n"
					+ "Hora fi: "+t.getHora_fi()+"\n"
					+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
					+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
		}
	}
	
	public static void borrarDiaConcret() {
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		c.borrarDiaConcret(data);		
	}
	
	public static void borrar() {
		c.borrar();
	}
	
}