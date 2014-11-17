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
		boolean sortir = false;
		System.out.println("Menu: \n"
				+ "0. Sortir\n"
				+ "1. Crear Calendari(id calendari: int, id plantilla: int, num torns: int, torn1...tornN\n"
				+ "2. Consultar id calendari\n"
				+ "3. Modificar id calendari(id nou: int)\n"
				+ "4. Consultar id plantilla\n"
				+ "5. Modificar id plantilla(id plantilla: int)\n"
				+ "6. Consultar torns del calendari\n"
				+ "7. Afegir torn al calendari(Torn(data inici:(yyyy: int, mm: int, dd:int, hh:int), data fi:(yyyy: int, mm: int, dd:int, hh:int), numero minim doctors: int, num doctors assignats: int, doctors assignats: String String .. String, percentatge sou: float))\n"
				+ "8. Eliminar torn del calendari(Torn(data inici:(yyyy: int, mm: int, dd:int, hh:int), data fi:(yyyy: int, mm: int, dd:int, hh:int), numero minim doctors: int, num doctors assignats: int, doctors assignats: String String .. String, percentatge sou: float))\n");
		
		while(!sortir) {
			
			try {
				cas = arg.nextInt();
			} catch (NumberFormatException e) {
					throw new Error ("Has d'introduir un numero.");
			}
			Scanner aux = new Scanner(System.in);
			
			switch(cas) {
				case 0: sortir=true; break;
				case 1: crear_calendari(aux); break;
				case 2: consultar_id(aux); break;
				case 3: modificar_id(aux); break;
				case 4: consultar_id_plantilla(aux); break;
				case 5: modificar_id_plantilla(aux); break;
				case 6: consultar_torns(aux); break;
				case 7: afegir_torn(aux); break;
				case 8: eliminar_torn(aux); break;
				default: System.out.println("El número ha d'estar entre 0 i 8");
				break;
				
			}
		}
	}
	
	public static void crear_calendari(Scanner s) {
		int id = s.nextInt();
		int id_plt = s.nextInt();
		int n = s.nextInt();
		ArrayList<Torn> lltorns = new ArrayList<Torn>();
		c = new Calendari(id,id_plt,lltorns);
		for(int i=0; i<n; i++) {
			afegir_torn(s);
		}
		
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
	
	public static void afegir_torn(Scanner s)  {
		Torn t = crear_torn(s);
		c.afegir_torn(t);
	}
	
	public static void eliminar_torn(Scanner s)  {
		Torn t = crear_torn(s);
		c.eliminar_torn(t);
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
		Torn t;
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

		int n = s.nextInt();
		String doc; 
		ArrayList<String> doctors_assig =  new ArrayList<String>();
		for(int i=0; i<n; i++) {
			doc = s.next(); 
			if(doctors_assig.isEmpty()) doctors_assig.add(doc);
			else {
				int j=0;
				while(j<doctors_assig.size() && doctors_assig.get(j).compareTo(doc)<0) ++j;
				doctors_assig.add(j,doc);
			}
		}
		float p_sou = s.nextFloat();
		t = new Torn(data_inici,data_fi,min_doc,doctors_assig,p_sou);
		return t;
	}
}