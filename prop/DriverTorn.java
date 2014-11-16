package prop;

import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class DriverTorn {
	
	static Torn t = new Torn();
	
	public static void main (String[] args) throws Error {
		
		@SuppressWarnings("resource")
		Scanner arg = new Scanner(System.in);
		int cas;
		boolean sortir=false;
		System.out.println("----------Menu:----------\n"
				+ "0. Sortir\n"
				+ "1. Crear Torn(data inici:(yyyy: int, mm: int, dd:int, hh:int), data fi:(yyyy: int, mm: int, dd:int, hh:int), numero minim doctors: int, num doctors assignats: int, doctors assignats: String String .. String, percentatge sou: float)\n"
				+ "2. Consultar hora inici\n"
				+ "3. Consultar hora fi\n"
				+ "4. Consultar dia setmana inici\n"
				+ "5. Consultar dia setmana fi\n"
				+ "6. Consultar data inici\n"
				+ "7. Modificar data incici(nova data inici:(yyyy: int, mm: int, dd:int, hh:int)) \n"
				+ "8. Consultar data fi\n"
				+ "9. Modificar data fi(nova data fi:(yyyy: int, mm: int, dd:int, hh:int)) \n"
				+ "10. Consultar numero minim doctors \n"
				+ "11. Modificar numero minim doctors(nou numero minim doctors: int)\n"
				+ "12. Consultar doctors assignats\n"
				+ "13. Afegir un doctor a la llista de doctors assignats(Doctor: String)\n"
				+ "14. Eliminar un doctor de la llista de doctors assignats(Doctor: String)\n"
				+ "15. Consultar percentatge sou"
				+ "16. Modificar percentatge sou(nou percentatge sou: float)\n");
		
		while(!sortir) {
			try {
				cas = arg.nextInt();
			} catch (NumberFormatException e) {
					throw new Error ("Has d'introduir un numero.");
			}
			Scanner aux = new Scanner(System.in);
			
			switch(cas) {
				case 0: sortir =true; break;
				case 1: crear_torn(aux); break;
				case 2: consultar_hora_inici(aux); break;
				case 3: consultar_hora_fi(aux); break;
				case 4: consultar_dia_setmana_inici(aux); break;
				case 5: consultar_dia_setmana_fi(aux); break;
				case 6: consultar_data_inici(aux); break;
				case 7: modificar_data_inici(aux); break;
				case 8: consultar_data_fi(aux); break;
				case 9: modificar_data_fi(aux); break;
				case 10: consultar_num_min_doctors(aux); break;
				case 11: modificar_num_min_doctors(aux); break;
				case 12: consultar_doctors_assignats(aux); break;
				case 13: afegir_doctor_assignats(aux); break;
				case 14: eliminar_doctor_assignats(aux); break;
				case 15: consultar_percentatge_sou(aux); break;
				case 16: modificar_percentatge_sou(aux); break;
				default: System.out.println("S'ha d'introduir un numero entre 1 i 16");
				break;
			}
		}
		
	}
	
	public static void crear_torn(Scanner s) {
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
	}
	
	public static void consultar_hora_inici(Scanner s) {
		System.out.println("Hora inici del torn: " + t.getHora_inici());
	}
	
	public static void consultar_hora_fi(Scanner s) {
		System.out.println("Hora final del torn: " + t.getHora_fi());
	}
	
	public static void consultar_dia_setmana_inici(Scanner s) {
		System.out.println("Dia de la setmana inici torn: " + t.getDia_setmana_inici());
	}
	
	public static void consultar_dia_setmana_fi(Scanner s) {
		System.out.println("Dia de la setmana final del torn: " + t.getDia_setmana_fi());
	}
	
	public static void consultar_data_inici(Scanner s) {
		GregorianCalendar dia = t.getData_inici();
		System.out.println("Data inici: " + dia.getTime().toString() );
	}
	
	public static void modificar_data_inici(Scanner s) {
		int any = s.nextInt();
		int mes = s.nextInt();
		int dia = s.nextInt();
		int hora = s.nextInt();
		GregorianCalendar data_inici = new GregorianCalendar(any,mes,dia,hora,0);
		t.setData_inici(data_inici);
	}
	
	public static void consultar_data_fi(Scanner s) {
		GregorianCalendar dia = t.getData_fi();
		System.out.println("Data fi: " + dia.getTime().toString());
	}
	
	public static void modificar_data_fi(Scanner s) {
		int any = s.nextInt();
		int mes = s.nextInt();
		int dia = s.nextInt();
		int hora = s.nextInt();
		GregorianCalendar data_fi = new GregorianCalendar(any,mes,dia,hora,0);
		t.setData_fi(data_fi);
	}
	
	public static void consultar_num_min_doctors(Scanner s) {
		System.out.println("Numero minim de doctors que han d'assistir: " + t.getN_min_doc());
	}
	
	public static void modificar_num_min_doctors(Scanner s) {
		t.setN_min_doc(s.nextInt());
	}
	
	public static void consultar_doctors_assignats(Scanner s) {
		ArrayList<String> l = t.getDoc_assignats();
		System.out.println("Els doctors assignats a aquest torn son: ");
		for(int i=0; i<l.size(); i++) System.out.println(l.get(i));
	}
	
	public static void afegir_doctor_assignats(Scanner s) {
		String doc = s.next();
		t.afegir_doctor(doc);
	}
	
	public static void eliminar_doctor_assignats(Scanner s) {
		String doc = s.next();
		t.eliminar_doctor(doc);
	}
	
	public static void consultar_percentatge_sou(Scanner s) {
		System.out.println("El percentatge de sou d'aques torn es: " + t.getPercent_sou());
	}
	
	public static void modificar_percentatge_sou(Scanner s) {
		t.setPercent_sou(s.nextFloat());
	}
}