package prop;

import java.util.Scanner;


public class DriverTorn {
	
	private static Torn t =  new Torn(0,0,(float)0,0);
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
				
			switch (cas) {
				case 0: sortir = true; break;
				case 1: crearTorn(); break;
				case 2: modificarHoraInici(); break;
				case 3: modificarHoraFi(); break;
				case 4: modificarPercentatge(); break;
				case 5: modificarMinimDoctors(); break;
				case 6: consultarHoraInici(); break;
				case 7: consultarHoraFi(); break;
				case 8: consultarPercentatge(); break;
				case 9: consultarMinimDoctors(); break;
				default: 
					System.out.println("El numero ha d'estar entre 0 i 9.");
					break;
			
			}
			escriureMenu();
		}
	}
	
	public static void escriureMenu() {
		System.out.println("\n\n-----------Menú-----------\n"
				+ "1.- Crear Torn:\n"
				+ "(hora inici: int(entre 0 i 23), hora fi: int(entre 0 i 23), percentatge sou: float, numero minim doctors: int)\n"
				+ "--------------------------\n"
				+ "2.- Modificar hora inici(hora inici nova: int(entre 0 i 23))\n"
				+ "3.- Modificar hora fi(hora fi nova: int(entre 0 i 23))\n"
				+ "4.- Modificar percentatge sou(nou percentatge sou: float)\n"
				+ "5.- Modificar numero minim de doctors(nou numero minim doctors: int(positiu))\n"
				+ "--------------------------\n"
				+ "6.- Consultar hora inici\n"
				+ "7.- Consultar hora fi\n"
				+ "8.- Consultar percentatge sou\n"
				+ "9.- Consultar numero minim de doctors\n"
				+ "---------------------------\n"
				+ "0.- Exit\n\n"
				+ "---------------------------\n"
				+ ">>");
	}
	
	public static void crearTorn() {
		int hora_i = arg.nextInt();
		int hora_f = arg.nextInt();
		float percent = arg.nextFloat();
		int minim = arg.nextInt();
		if(hora_i<hora_f) {
			t.setHora_fi(hora_f);
			t.setHora_inici(hora_i);
			t.setPercent_sou(percent);
			t.setMin_num_doctors(minim);
			if(primer) primer=false;
		}
		else System.out.println("L'hora d'inici ha de ser inferior a la hora fi del torn");
	}
	
	public static void modificarHoraInici() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			int hora_i = arg.nextInt();
			if(hora_i<t.getHora_fi()) t.setHora_inici(hora_i);
			else System.out.println("L'hora d'inici ha de ser inferior a la hora fi del torn");
		}
	}
	
	public static void modificarHoraFi() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			int hora_f = arg.nextInt();
			if(hora_f>t.getHora_inici())t.setHora_fi(hora_f);
			else System.out.println("L'hora d'inici ha de ser inferior a la hora fi del torn");
		}
	}
	
	public static void modificarPercentatge() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			float percent = arg.nextFloat();
			t.setPercent_sou(percent);
		}
	}
	
	public static void modificarMinimDoctors() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			int minim = arg.nextInt();
			t.setMin_num_doctors(minim);
		}
	}
	
	public static void consultarHoraInici() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else System.out.println(t.getHora_inici());
	}
	
	public static void consultarHoraFi() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else System.out.println(t.getHora_fi());
	}
	
	public static void consultarPercentatge() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else System.out.println(t.getPercent_sou());
	}
	
	public static void consultarMinimDoctors() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else System.out.println(t.getMin_num_doctors());
	}
	
}