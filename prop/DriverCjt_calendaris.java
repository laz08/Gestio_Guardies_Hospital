package prop;

import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DriverCjt_calendaris {
	
	static Cjt_calendaris c = new Cjt_calendaris();
	
	public static void main(String[] args) throws Error {
		
		@SuppressWarnings("resource")
		Scanner arg = new Scanner(System.in);
		int cas;
		System.out.println("Menu: \n"
				+ "1. Consultar llista calendaris\n");
		
		while(true) {
			
			try {
				cas = arg.nextInt();
			} catch (NumberFormatException e) {
					throw new Error ("Has d'introduir un numero.");
			}
			Scanner aux = new Scanner(System.in);
			
			switch(cas) {
				case 1: consultar_llista_calendaris(aux); break;
				case 2: afegir_calendari(aux); break;
				case 3: eliminar_calendari(aux); break;
				case 4: consultar_calendari(aux); break;
				default: break;
			}
		}
	}
	
	public static void consultar_llista_calendaris(Scanner s) {
		ArrayList<Calendari> llc = c.get_llista_calendaris();
		for(int i=0; i<llc.size(); i++) {
			System.out.println("Calendari: " + llc.get(i).getId());
			System.out.println("Plantilla: " + llc.get(i).getId_plantilla());
		}
	}
	
	public static void afegir_calendari(Scanner s) {
		Calendari cl = crear_calendari(s);
		c.afegir_calendari(cl);
	}
	
	public static void eliminar_calendari(Scanner s) {
		int id = s.nextInt();
		c.eliminar_calendari(id);
	}
	
	public static void consultar_calendari(Scanner s) {
		int id = s.nextInt();
		Calendari cal = c.consultar_calendari(id);
		ArrayList<Torn> llt = cal.getTorns();
		for(int j=0; j<llt.size(); j++) escriure_torn(llt.get(j));
	}
	
	public static Calendari crear_calendari(Scanner s) {
		int id = s.nextInt();
		int id_plt = s.nextInt();
		int n = s.nextInt();
		ArrayList<Torn> lltorns = new ArrayList<Torn>();
		for(int i=0; i<n; i++) {
			lltorns.add(crear_torn(s));
		}
		Calendari cl = new Calendari(id,id_plt,lltorns);
		return cl;
		
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