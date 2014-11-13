package prop;

import java.lang.NumberFormatException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DriverCalendari {
	
	public static void main(String[] args) throws Error {
		
		Scanner arg = new Scanner(System.in);
		int cas;
		System.out.println("Menu:\n"
				+ "1. Afegir Torn:\n"
				+ "crear torn (data inici (yyyy mm dd hh), data fi (yyyy mm dd hh), int numero minim doctors, int percentatge sou \n"
				+ "triar plantilla (int id_plantilla)\n"
				+ "\n"
				+ "2. Modificar Torn:\n"
				+ "entrar torn(data inici, data fi, int numero minim doctors, int doctors assignats, int percent sou\n"
				+ "entrar torn modificat (data inici, data fi, int numero minim doctors, int doctors assignats, int percent sou\n"
				+ "triar plantilla(int id plantilla)\n"
				+ "\n"
				+ "3. Eliminar Torn:\n"
				+ "torn (data inici (yyyy mm dd hh), data fi (yyyy mm dd hh), int numero minim doctors, int percentatge sou\n "
				+ "triar plantilla (int id_plantilla)\n"
				+ "\n"
				+ "4. Consultar Calendari:\n"
				+ "triar plantilla (int id plantilla)\n");
		
		while(true) {
			
			try {
				cas = arg.nextInt();
			} catch (NumberFormatException e) {
					throw new Error ("Has d'introduir un numero.");
			}
			Scanner aux = new Scanner(System.in);
			
			switch(cas) {
				case 1: afegir_torn(aux); break;
				
				case 2: modificar_torn(aux);  break;
					
				case 3: eliminar_torn(aux); break;
				
				case 4: consultar_calendari(aux); break;
					
				default: 
					System.out.println("El número ha d'estar entre 1 i 4");
					break;
					
			}
        }	
		
		
	}
	
	public static void afegir_torn(Scanner s) throws Error {
		CtrlCalendari ctr = new CtrlCalendari();
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
		
		Torn t = new Torn(data_inici, data_fi, min_doc, p_sou);
		int id_plt = s.nextInt();
		ctr.afegir_torn(t, id_plt);		
	}
	
	public static void modificar_torn(Scanner s) throws Error {
		CtrlCalendari ctr = new CtrlCalendari();
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
		
		Torn t = new Torn(data_inici, data_fi, min_doc, p_sou);
		
		any = s.nextInt();
		mes = s.nextInt();
		dia = s.nextInt();
		hora = s.nextInt();
		data_inici = new GregorianCalendar(any,mes,dia,hora,0);
		any = s.nextInt();
		mes = s.nextInt();
		dia = s.nextInt();
		hora = s.nextInt();
		data_fi = new GregorianCalendar(any,mes,dia,hora,0);
		min_doc = s.nextInt();
		p_sou = s.nextInt();
		
		Torn t_nou = new Torn(data_inici, data_fi, min_doc, p_sou);
		
		int id_plt = s.nextInt();
		
		ctr.modificar_torn(t,t_nou,id_plt);
	}
	
	public static void eliminar_torn(Scanner s) throws Error {
		CtrlCalendari ctr = new CtrlCalendari();
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
		
		Torn t = new Torn(data_inici, data_fi, min_doc, p_sou);
		int id_plt = s.nextInt();
		ctr.eliminar_torn(t, id_plt);		
	}
	
	public static void consultar_calendari(Scanner s) throws Error {
		CtrlCalendari ctr = new CtrlCalendari();
		int id_plt = s.nextInt();
		ctr.consultar_calendari(id_plt);		
	}
}
