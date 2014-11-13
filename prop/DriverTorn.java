package prop;

import java.lang.NumberFormatException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class DriverTorn {
	
	static Torn t = new Torn();
	
	public static void main (String[] args) {
		
		Scanner arg = new Scanner(System.in);
		int cas;
		System.out.println("Menu: \n"
				+ "1. Crear Torn: \n"
				+ "(yyyy mm dd hh) data inici, (yyyy mm dd hh) data fi, (int) numero minim doctors, (int) percent sou\n"
				+ "\n"
				+ "2. Consultar data inici\n"
				+ "\n"
				+ "2. Modificar data incici\n"
				+ "yyyy mm dd hh \n"
				+ "\n"
				+ "4. Consultar data fi\n"
				+ "\n"
				+ "5. Modificar data fi\n"
				+ "\n"
				+ "6. Consultar numero minim doctors \n"
				+ "\n"
				+ "7. Modificar numero minim doctors\n"
				+ "int\n"
				+ "\n"
				+ "8. Consultar doctors assignats\n"
				+ "\n"
				+ "9. Modificar doctors assignats\n"
				+ "int\n"
				+ "\n"
				+ "10. Consultar percentatge sou\n"
				+ "\n"
				+ "11. Modificar percentatge sou\n"
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
				case 2: consultar_data_inici(aux); break;
				case 3: modificar_data_inici(aux); break;
				case 4: consultar_data_fi(aux); break;
				case 5: modificar_data_fi(aux); break;
				case 6: consultar_num_min_doctors(aux); break;
				case 7: modificar_num_min_doctor(aux); break;
				case 8: consultar_doctors_assignats(aux); break;
				case 9: modificar_doctors_assignats(aux); break;
				case 10: consultar_percentatge_sou(aux); break;
				case 11: modificar_percentatge_sou(aux); break;
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
		t = new Torn(data_inici,data_fi,min_doc,p_sou);
	}
	
	public static void consultar_data_inici(Scanner s) {
		System.out.println("Data inici: " t.getData_inici());
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
		System.out.println("Data fi: " t.getData_fi());
	}
}