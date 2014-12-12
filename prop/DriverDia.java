package prop;

import java.util.Scanner;

public class DriverDia {
	
	private static Dia d = new Dia(false);
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
				case 1: crearDia(); break;
				case 2: modificarDiaFestiu(); break;
				case 3: modificarTorns(); break;
				case 4: modificarTornMati(); break;
				case 5: modificarTornTarda(); break;
				case 6: modificarTornNit(); break;
				case 7: consultarDiaFestiu(); break;
				case 8: consultarTorns(); break;
				case 9: consultarTornMati(); break;
				case 10: consultarTornTarda(); break;
				case 11: consultarTornNit(); break;
				default:
					System.out.println("El numero ha d'estar entre 0 i 11.");
					break;
			}
			escriureMenu();
		}
	}
	
	
	public static void escriureMenu() {
		System.out.println("\n\n----------Menú----------\n"
				+ "1.- Crear Dia(festiu: boolean, numero torns a crear: int(de 0 a 3))\n"
				+ "Per cada torn: (hora inici: int, hora fi: int, percentatge sou: float, numero minim doctors: int)"
				+ "--------------------------\n"
				+ "2.- Modificar dia festiu o no(festiu: boolean)\n"
				+ "3.- Modificar torns (Introduir tres torns)\n"
				+ "4.- Modificar torn mati(nou torn)\n"
				+ "5.- Modificar torn tarda(nou torn)\n"
				+ "6.- Modificar torn nit(nou torn)\n"
				+ "--------------------------\n"
				+ "7.- Consultar si es festiu el dia\n"
				+ "8.- Consultar torns\n"
				+ "9.- Consultar torn mati\n"
				+ "10.- Consultar torn tarda\n"
				+ "11.- Consultar torn nit\n"
				+ "---------------------------\n"
				+ "0.- Exit\n\n");
	}
	
	public static void crearDia() {
		boolean b = arg.nextBoolean();
		int n = arg.nextInt();
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
		d.setFestiu(b);
	}
	
	public static void modificarDiaFestiu() {
		boolean b = arg.nextBoolean();
		d.setFestiu(b);
	}
	
	public static void modificarTorns() {
		Torn[] ts = new Torn[3];
		for(int i=0; i<3; ++i) {
			int hora_i = arg.nextInt();
			int hora_f = arg.nextInt();
			float percent = arg.nextFloat();
			int minim = arg.nextInt();
			Torn t = new Torn(hora_i,hora_f,percent,minim);
			ts[i] = t;
		}
		d.setTorns(ts);
	}
	
	public static void modificarTornMati() {
		int hora_i = arg.nextInt();
		int hora_f = arg.nextInt();
		float percent = arg.nextFloat();
		int minim = arg.nextInt();
		Torn t = new Torn(hora_i,hora_f,percent,minim);
		d.setTornMati(t);
	}
	
	public static void modificarTornTarda() {
		int hora_i = arg.nextInt();
		int hora_f = arg.nextInt();
		float percent = arg.nextFloat();
		int minim = arg.nextInt();
		Torn t = new Torn(hora_i,hora_f,percent,minim);
		d.setTornTarda(t);
	}
	
	public static void modificarTornNit() {
		int hora_i = arg.nextInt();
		int hora_f = arg.nextInt();
		float percent = arg.nextFloat();
		int minim = arg.nextInt();
		Torn t = new Torn(hora_i,hora_f,percent,minim);
		d.setTornNit(t);
	}
	
	public static void consultarDiaFestiu() {
		System.out.println(d.getFestiu());
	}
	
	public static void consultarTorns() {
		Torn t;
		String s;
		for(int i=0; i<3; ++i) {
			if(i==0) {
				s="Mati"; 
				t= d.getTornMati();
			}
			else if(i==1) {
				s="Tarda"; 
				t = d.getTornTarda();
			}
			else {
				s="Nit"; 
				t=d.getTornNit();
			}
			System.out.println("Torn de "+s+":\n"
					+ "Hora inici: "+t.getHora_inici()+"\n"
					+ "Hora fi: "+t.getHora_fi()+"\n"
					+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
					+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
		}
	}
	
	public static void consultarTornMati() {
		Torn t = d.getTornMati();
		System.out.println("Torn de Mati:\n"
				+ "Hora inici: "+t.getHora_inici()+"\n"
				+ "Hora fi: "+t.getHora_fi()+"\n"
				+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
				+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
	}
	
	public static void consultarTornTarda() {
		Torn t = d.getTornTarda();
		System.out.println("Torn de Tarda:\n"
				+ "Hora inici: "+t.getHora_inici()+"\n"
				+ "Hora fi: "+t.getHora_fi()+"\n"
				+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
				+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
	}
	
	public static void consultarTornNit() {
		Torn t = d.getTornNit();
		System.out.println("Torn de Nit:\n"
				+ "Hora inici: "+t.getHora_inici()+"\n"
				+ "Hora fi: "+t.getHora_fi()+"\n"
				+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
				+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
	}

	

}