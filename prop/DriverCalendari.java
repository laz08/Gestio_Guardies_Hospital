package prop;

import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DriverCalendari {
	
	static Calendari c = new Calendari();
	
	public static void main(String[] args) throws Error {
		
		@SuppressWarnings("resource")
		Scanner arg = new Scanner(System.in);
		int cas;
		System.out.println("Menu: \n"
				+ "1. Crear Calendari:"
				+ "int id calendari, int id plantilla, int N_torns, torn1...tornN\n"
				+ "2. Consultar id calendari\n"
				+ "3. Modificar id calendari\n"
				+ "int id nou\n"
				+ "4. Consultar id plantilla\n"
				+ "5. Modificar id plantilla\n"
				+ "int id plantilla\n"
				+ "6. Consultar torns del calendari\n");
		
		while(true) {
			
			try {
				cas = arg.nextInt();
			} catch (NumberFormatException e) {
					throw new Error ("Has d'introduir un numero.");
			}
			Scanner aux = new Scanner(System.in);
			
			switch(cas) {
				case 1: crear_calendari(aux); break;
				case 2: consultar_id(aux); break;
				case 3: modificar_id(aux); break;
				case 4: consultar_id_plantilla(aux); break;
				case 5: modificar_id_plantilla(aux); break;
				case 6: consultar_torns(aux); break;
				default: System.out.println("El número ha d'estar entre 1 i 6");
				break;
				
			}
		}
	}
	
	public static void crear_calendari(Scanner s) {
		int id = s.nextInt();
		int id_plt = s.nextInt();
		int n = s.nextInt();
		ArrayList<Torn> lltorns = new ArrayList<Torn>();
		Torn t;
		System.out.println("fora\n");

		for(int i=0; i<n; i++) {
			t = crear_torn(s);
			lltorns.add(t);
		}
		c = new Calendari(id,id_plt,lltorns);
		
	}
	
	public static void consultar_id(Scanner s) {
		System.out.println("El identificador del calendari es: " + c.getId());
	}
	
	public static void modificar_id(Scanner s) {
		c.setId(s.nextInt());
	}
	
	public static void consultar_id_plantilla(Scanner s) {
		System.out.println("El identificador de la plantilla associada al calendari es: " + c.getId_plantilla());
	}
	
	public static void modificar_id_plantilla(Scanner s) {
		c.setId_plantilla(s.nextInt());
	}
	
	public static void consultar_torns(Scanner s) {
		ArrayList<Torn> l = c.getTorns();
		for(int i=0; i<l.size(); i++) {
			escriure_torn(l.get(i));
			System.out.println("\n");
		}
	}
	
	public static void escriure_torn(Torn t) {
		GregorianCalendar dia = t.getData_inici();
		GregorianCalendar diaf = t.getData_fi();
		System.out.println("Data inici: " + dia.getTime().toString()+
				"\nData fi: "+diaf.getTime().toString() +
				"\nNumero minim doctors: " + t.getN_min_doc() +
				"\nPercentatge de sou: " + t.getPercent_sou() +
				"\nDoctors: " );
		ArrayList<String> l = t.getDoc_assignats();
		for(int i=0; i<l.size(); i++) {
			System.out.println(l.get(i));
		}
	}
	
	public static Torn crear_torn(Scanner s) {
		int any = s.nextInt();
		int mes = s.nextInt();
		int dia = s.nextInt();
		int hora = s.nextInt();
		GregorianCalendar data_inici = new GregorianCalendar(any,mes,dia,hora,0);
		any = s.nextInt();
		mes = s.nextInt();
		dia = s.nextInt();
		hora = s.nextInt();
		GregorianCalendar data_fi = new GregorianCalendar(any,mes,dia,hora,0);
		int min_doc = s.nextInt();
		int p_sou = s.nextInt();

		int n = s.nextInt();
		String doc; 
		ArrayList<String> doctors_assig =  new ArrayList<String>();
		for(int i=0; i<n; i++) {
			doc = s.next(); 
			doctors_assig.add(doc);
		}

		Torn t = new Torn(data_inici,data_fi,min_doc,p_sou,doctors_assig);
		return t;
	}
}