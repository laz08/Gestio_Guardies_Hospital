package prop;

import java.util.Scanner;

public class DriverCalendari {
	
	private static Calendari c;
	
	public static void main(String[] args) throws Error {
		
		Scanner arg;
		int cas;
		boolean sortir = false;
		
		while(!sortir) {
			try {
				
				escriureMenu();
				
				arg = new Scanner(System.in);
				cas = arg.nextInt();
				
				switch(cas) {
				
					case 0:
						sortir = true;
						break;
						
					case 1:
						crearCalendari(arg);
						break;
						
					case 2:
						modificarPlantilla(arg);
						break;
						
					case 3:
						consultarCalendari();
						break;
						
					case 4:
						consultarPlantilla();
						break;
						
					default:
						System.out.println("El numero ha d'estar entre 0 i 2.");
						break;
				
				}
			} catch(Exception e) {
				System.err.println("Has d'introduir un numero\n"+e);
			}
		}
	}
	
	public static void escriureMenu() {
		System.out.println("\n\n----------Menú----------"
				+ "1.- Crear Calendari(identificador plantilla: String, any inici: int, any fi: int)\n"
				+ "--------------------------\n"
				+ "2.- Modificar Calendari(id: int)"
				+ "2.- Modificar Plantilla associada(identificador plantilla nova: String)\n"
				+ "--------------------------\n"
				+ "3.- Consultar Calendari\n"
				+ "4.- Consultar Plantilla associada"
				+ "--------------------------\n"
				+ "0.- Exit\n\n");
	}
	
	public static void crearCalendari(Scanner s) {
		String plt = s.next();
		int any_i = s.nextInt();
		int any_fi = s.nextInt();
		c = new Calendari(plt,any_i,any_fi);
	}
	
	public static void modificarIdentificador(Scanner s) {
		int id = s.nextInt();
		c.setIdentificador(id);
	}
	
	public static void modificarPlantilla(Scanner s) {
		String pltilla = s.next();
		c.setPlantillaAssociada(pltilla);
	}
	
	public static void consultarCalendari() {
		
	}
	
	public static void consultarPlantilla() {
		System.out.println(c.getPlantillaAssociada());
	}
	
}