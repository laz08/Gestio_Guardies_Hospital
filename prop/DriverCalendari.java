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
				case 3: modificarDia(); break;
				case 4: modificarTornMati(); break;
				case 5: modificarTornTarda(); break;
				case 6: modificarTornNit(); break;
				case 7: consultarCalendari(); break;
				case 8: consultarPlantilla(); break;
				case 9: consultarDiaConcret(); break;
				case 10: consultarTornMati(); break;
				case 11: consultarTornTarda(); break;
				case 12: consultarTornNit(); break;
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
				+ "3.- Modificar Dia(data(any,mes,dia) nou dia: festiu: boolea, tres torns)\n"
				+ "4.- Modificar Torn Mati(data(any,mes,dia))\n"
				+ "5.- Modificar Torn Tarda(data(any,mes,dia))\n"
				+ "6.- Modificar Torn Nit(data(any,mes,dia))\n"
				+ "--------------------------\n"
				+ "7.- Consultar Calendari\n"
				+ "8.- Consultar Plantilla associada\n"
				+ "9.- Consultar dia concret (any, mes dia)\n"
				+ "10.- Consultar torn mati(any, mes, dia)\n"
				+ "11.- Consultar torn tarda(any, mes, dia)\n"
				+ "12.- Consultar torn nit(any, mes, dia)\n"
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
	
	public static void modificarDia() {
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		boolean b = arg.nextBoolean();
		Dia d = new Dia(b);
		for(int i=0; i<3; ++i) {
			int hora_i = arg.nextInt();
			int hora_f = arg.nextInt();
			float percent = arg.nextFloat();
			int minim = arg.nextInt();
			Torn t = new Torn(hora_i,hora_f,percent,minim);
			if(i==0) d.setTornMati(t);
			else if(i==1) d.setTornTarda(t);
			else d.setTornNit(t);
		}		
		int pos = c.calcularPosicioDia(data);
		c.getCalendari()[pos]= d;
	}
	
	public static void modificarTornMati() {
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int hora_i = arg.nextInt();
		int hora_f = arg.nextInt();
		float percent = arg.nextFloat();
		int minim = arg.nextInt();
		Torn t = new Torn(hora_i,hora_f,percent,minim);
		int pos = c.calcularPosicioDia(data);
		c.getCalendari()[pos].setTornMati(t);
	}
	
	public static void modificarTornTarda() {
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int hora_i = arg.nextInt();
		int hora_f = arg.nextInt();
		float percent = arg.nextFloat();
		int minim = arg.nextInt();
		Torn t = new Torn(hora_i,hora_f,percent,minim);
		int pos = c.calcularPosicioDia(data);
		c.getCalendari()[pos].setTornTarda(t);
	}
	
	public static void modificarTornNit() {
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int hora_i = arg.nextInt();
		int hora_f = arg.nextInt();
		float percent = arg.nextFloat();
		int minim = arg.nextInt();
		Torn t = new Torn(hora_i,hora_f,percent,minim);
		int pos = c.calcularPosicioDia(data);
		c.getCalendari()[pos].setTornNit(t);
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
				if(j==0) {
					s="Mati";
					t=d.getTornMati();
				}
				else if(j==1)  {
					s="Tarda";
					t=d.getTornTarda();
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
	
	public static void consultarPlantilla() {
		System.out.println(c.getPlantillaAssociada());
	}
	
	public static void consultarDiaConcret() {
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int pos = c.calcularPosicioDia(data);
		Dia d = c.getCalendari()[pos];
		System.out.println("El dia "+dia+"/"+mes+"/"+any);
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
	
	public static void consultarTornMati() {
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int pos = c.calcularPosicioDia(data);
		Torn t = c.getCalendari()[pos].getTornMati();
		System.out.println("Torn de mati:\n"
				+ "Hora inici: "+t.getHora_inici()+"\n"
				+ "Hora fi: "+t.getHora_fi()+"\n"
				+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
				+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
	}
	
	public static void consultarTornTarda() {
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int pos = c.calcularPosicioDia(data);
		Torn t = c.getCalendari()[pos].getTornTarda();
		System.out.println("Torn de tarda:\n"
				+ "Hora inici: "+t.getHora_inici()+"\n"
				+ "Hora fi: "+t.getHora_fi()+"\n"
				+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
				+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
	}
	
	public static void consultarTornNit() {
		int any = arg.nextInt();
		int mes = arg.nextInt();
		int dia = arg.nextInt();
		GregorianCalendar data = new GregorianCalendar(any,mes,dia);
		int pos = c.calcularPosicioDia(data);
		Torn t = c.getCalendari()[pos].getTornNit();
		System.out.println("Torn de mati:\n"
				+ "Hora inici: "+t.getHora_inici()+"\n"
				+ "Hora fi: "+t.getHora_fi()+"\n"
				+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
				+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
	}
	
}