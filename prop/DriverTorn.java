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
		System.out.println("Menu: \n"
				+ "1. Crear Torn: \n"
				+ "(int yyyy, int mm, int dd, int hh) data inici, < (int yyyy, int mm, int dd, int hh) data fi, (int) numero minim doctors, (int) percent sou, doctors assignats (int, string string..)\n"
				+ "\n"
				+ "2. Consultar hora inici\n"
				+ "\n"
				+ "3. Consultar hora fi\n"
				+ "\n"
				+ "4. Consultar dia setmana inici\n"
				+ "\n"
				+ "5. Consultar dia setmana fi\n"
				+ "\n"
				+ "6. Consultar data inici\n"
				+ "\n"
				+ "7. Modificar data incici\n"
				+ "int yyyy, int mm, int dd, int hh \n"
				+ "\n"
				+ "8. Consultar data fi\n"
				+ "\n"
				+ "9. Modificar data fi\n"
				+ "\n"
				+ "10. Consultar numero minim doctors \n"
				+ "\n"
				+ "11. Modificar numero minim doctors\n"
				+ "int\n"
				+ "\n"
				+ "12. Consultar doctors assignats\n"
				+ "\n"
				+ "13. Modificar doctors assignats\n"
				+ "int quants doctors\n"
				+ "String doc1 ... String docN"
				+ "\n"
				+ "14. Consultar percentatge sou\n"
				+ "\n"
				+ "15. Modificar percentatge sou\n"
				+ "int\n"
				+ "\n");
		
		while(true) {
			try {
				cas = arg.nextInt();
			} catch (NumberFormatException e) {
					throw new Error ("Has d'introduir un numero.");
			}
			Scanner aux = new Scanner(System.in);
			
			switch(cas) {
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
				case 13: modificar_doctors_assignats(aux); break;
				case 14: consultar_percentatge_sou(aux); break;
				case 15: modificar_percentatge_sou(aux); break;
				default: System.out.println("S'ha d'introduir un numero entre 1 i 15");
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
		int p_sou = s.nextInt();

		int n = s.nextInt();
		String doc; 
		ArrayList<String> doctors_assig =  new ArrayList<String>();
		for(int i=0; i<n; i++) {
			doc = s.next(); 
			doctors_assig.add(doc);
		}
		t = new Torn(data_inici,data_fi,min_doc,p_sou,doctors_assig);
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
	
	public static void modificar_doctors_assignats(Scanner s) {
		int n = s.nextInt();
		String doc; 
		ArrayList<String> doctors_assig =  new ArrayList<String>();
		for(int i=0; i<n; i++) {
			doc = s.next(); 
			doctors_assig.add(doc);
		}
		t.setDoc_assignats(doctors_assig);
	}
	
	public static void consultar_percentatge_sou(Scanner s) {
		System.out.println("El percentatge de sou d'aques torn es: " + t.getPercent_sou());
	}
	
	public static void modificar_percentatge_sou(Scanner s) {
		t.setPercent_sou(s.nextFloat());
	}
}