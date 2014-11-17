package prop;

import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DriverCtrlCalendari {
	
	static CtrlCalendari c = new CtrlCalendari();
	
	public static void main(String[] args) throws Error {
		
		@SuppressWarnings("resource")
		Scanner arg = new Scanner(System.in);
		int cas;
		boolean sortir=false;
		System.out.println("----------Menu:-----------\n"
				+ "0. Sortir\n"
				+ "1. Consultar calendari(id plantilla: int)\n"
				+ "2. Consultar calendari d'un doctor(dni doctor: String)\n"
				+ "3. Afegir torn a una plantilla(id plantilla: int, torn: (data inici (yyyy:int, mm:int, dd:int, hh:int), data fi (yyyy:int, mm:int, dd:int, hh:int), numero minim doctors: int, numero doctors assignats: int, doctors assignats: String .. String, percentatge sou: float\n"
				+ "4. Modificar torn(id plantilla: int, torn, torn_nou)\n"
				+ "5. Eliminar torn(id plantilla: int, torn)\n"
				+ "6. Afegir calendari(id: int, id plantilla: int, torn..torn\n"
				+ "7. Eliminar calendari(id: int)\n");
		
		while(!sortir) {
			
			try {
				cas = arg.nextInt();
			} catch (NumberFormatException e) {
					throw new Error ("Has d'introduir un numero.");
			}
			Scanner aux = new Scanner(System.in);
			
			switch(cas) {
				case 0: sortir=true; break;
				case 1: consultar_calendari(aux); break;
				case 2: consultar_calendari_doctor(aux); break;
				case 3: afegir_torn(aux); break;
				case 4: modificar_torn(aux);  break;
				case 5: eliminar_torn(aux); break;
				case 6: afegir_calendari(aux); break;
				case 7: eliminar_calendari(aux); break;
				default: 
					System.out.println("El número ha d'estar entre 0 i 7");
					break;
					
			}
        }	
	}
	
	public static void afegir_torn(Scanner s) throws Error {
		int id_plt = s.nextInt();
		Calendari cl = c.consultar_calendari(id_plt);
		if(cl!=null) {
			Torn t = crear_torn(s);
			c.afegir_torn_calendari(t, cl);		
		}
	}
	
	public static void modificar_torn(Scanner s) throws Error {
		int id_plt = s.nextInt();
		Calendari cl = c.consultar_calendari(id_plt);

		if(cl!=null) {
			ArrayList<Torn> llt = cl.getTorns();
			for(int i=0; i<llt.size(); i++) escriure_torn(llt.get(i));
			System.out.println("Introdueix la data inici del torn a modificar i tots els parametres d'un torn per modificarlo\n");
			int any = s.nextInt();
			int mes = s.nextInt()-1;
			int dia = s.nextInt();
			int hora = s.nextInt();
			GregorianCalendar data_inici = new GregorianCalendar(any,mes,dia,hora,0);
			Torn t = c.torn_data(data_inici,cl);
			if(t!=null) {
				Torn t_nou = crear_torn(s);
				c.modificar_torn(t,t_nou,cl);
			}
		}
	}
	
	public static void eliminar_torn(Scanner s) throws Error {
		Torn t = crear_torn(s);
		int id_plt = s.nextInt();
		c.eliminar_torn(t, id_plt);		
	}
	
	public static void consultar_calendari(Scanner s) throws Error {
		int id_plt = s.nextInt();
		Calendari cld = c.consultar_calendari(id_plt);	
		if(cld!=null) {
			System.out.println("El identificador es: " + cld.getId());
			System.out.println("Associat a la plantilla: " + cld.getId_plantilla());
			ArrayList<Torn> llt = cld.getTorns();
			for(int i=0; i<llt.size(); i++) escriure_torn(llt.get(i));
		}
	}
	
	public static void consultar_calendari_doctor(Scanner s) {
		int id_plt = s.nextInt();
		Calendari cld = c.consultar_calendari(id_plt);	
		if(cld!=null) {
			String doc = s.next();
			ArrayList<Torn> llt = cld.getTorns();
			for(int i=0; i<llt.size(); i++) {
				ArrayList<String> ldoc = llt.get(i).getDoc_assignats();
				int j=0;
				while(j<ldoc.size() && doc.compareTo(ldoc.get(j))>0) ++j;
				if(j<ldoc.size() && doc.compareTo(ldoc.get(j))==0) escriure_torn(llt.get(i));
			}
		}
	}
	public static int posicio_torn(ArrayList<Torn> lt, Torn t) {
		GregorianCalendar d_inici = t.getData_inici();
		GregorianCalendar d_fi = t.getData_fi();
		int i=0;
		while (i!=-1 && i<lt.size() && lt.get(i).getData_inici().before(d_fi)) {
			if (d_inici.before(lt.get(i).getData_inici()) || (d_inici.before(lt.get(i).getData_fi()))){
				System.out.println("El torn es solapa amb un altre torn ja existent");
				i=-1;
			}
			else ++i;
		}
		return i;
	}
	
	public static void afegir_t(ArrayList<Torn> lt, Torn t) {
		if(lt.isEmpty()) lt.add(t);
		else {
			int i = posicio_torn(lt,t);
			if(i!=-1) lt.add(i,t);
		}
	}
	
	public static void afegir_calendari(Scanner s) {
		int id = s.nextInt();
		int id_plt = s.nextInt();
		int n = s.nextInt();
		ArrayList<Torn> lt = new ArrayList<Torn>();
		Torn t = new Torn();
		for(int i=0; i<n; i++) {
			t = crear_torn(s);
			afegir_t(lt,t);
		}
		Calendari cld = new Calendari(id,id_plt,lt);
		c.afegir_calendari(cld);
	}
	
	public static void eliminar_calendari(Scanner s) {
		int id = s.nextInt();
		c.eliminar_calendari(id);
	}
	
	public static void escriure_torn(Torn t) {
		GregorianCalendar dia = t.getData_inici();
		GregorianCalendar diaf = t.getData_fi();
		System.out.println("\n----Torn----");
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
		int mes = s.nextInt()-1;
		int dia = s.nextInt();
		int hora = s.nextInt();
		GregorianCalendar data_inici = new GregorianCalendar(any,mes,dia,hora,0);
		any = s.nextInt();
		mes = s.nextInt()-1;
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
