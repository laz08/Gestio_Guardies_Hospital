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
				case 3: modificarTorn(); break;
				case 4: consultarDiaFestiu(); break;
				case 5: consultarTorns(); break;
				case 6: consultarTornConcret(); break;
				case 7: borrarTornConcret(); break;
				case 8: borrarDia(); break;
				default:
					System.out.println("El numero ha d'estar entre 0 i 6.");
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
				+ "3.- Modificar torn (quin: int(mati=0, tarda=1, nit=2), nou torn)\n"
				+ "--------------------------\n"
				+ "4.- Consultar si es festiu el dia\n"
				+ "5.- Consultar torns\n"
				+ "6.- Consultar torn concret(quin: int(mati=0, tarda=1, nit=2))\n"
				+ "---------------------------\n"
				+ "7.- Borrar torn concret(quin: int(mati=0, tarda=1, nit=2))\n"
				+ "8.- Borrar tot el dia\n"
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
			d.setTorn(t);
		}
		d.setFestiu(b);
	}
	
	public static void modificarDiaFestiu() {
		boolean b = arg.nextBoolean();
		d.setFestiu(b);
	}
	
	public static void modificarTorn() {
		consultarTorns();
		int i = arg.nextInt();
		int hora_i = arg.nextInt();
		int hora_f = arg.nextInt();
		float percent = arg.nextFloat();
		int minim = arg.nextInt();
		Torn t = new Torn(hora_i,hora_f,percent,minim);
		d.setTornConcret(t,i);
	}
	
	public static void consultarDiaFestiu() {
		System.out.println(d.getFestiu());
	}
	
	public static void consultarTorns() {
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
	
	public static void consultarTornConcret() {
		int i = arg.nextInt();
		String s;
		Torn t=d.getTorn_concret(i);
		if(i==0) s="Mati";
		else if(i==1) s="Tarda";
		else s="Nit";
		System.out.println("Torn de "+s+":\n"
				+ "Hora inici: "+t.getHora_inici()+"\n"
				+ "Hora fi: "+t.getHora_fi()+"\n"
				+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
				+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
	}
	
	public static void borrarTornConcret() {
		int i = arg.nextInt();
		d.borrarTorn(i);
	}
	
	public static void borrarDia() {
		d.borrar();
	}
}