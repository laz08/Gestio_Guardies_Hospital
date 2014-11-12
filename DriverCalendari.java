package prop;

import java.lang.NumberFormatException;
import java.util.GregorianCalendar;
import java.util.Scanner;

import prop.*;

public class DriverCalendari {
	
	public static void main(String[] args) throws Error {
		
		Calendari c;
		Scanner arg = new Scanner(System.in);
		int cas;
		while(true) {
			System.out.println("Menu");
			System.out.println("1.Afegir Torn");
			System.out.println("2.Modificar Torn");
			System.out.println("3.Eliminar Torn");
			System.out.println("4.Consultar Calendari");
			
	
		
			try {
				cas = arg.nextInt();
			} catch (NumberFormatException e) {
					throw new Error ("Has d'introduir un numero.");
			}
			
			switch(cas) {
				case 1: 
					String d_i = new Scanner(System.in);
					String d_f = new Scanner(System.in);
					String min_d = new Scanner(System.in);
					String p_sou = new Scanner(System.in);
					
					GregorianCalendar data_i 
					GregorianCalendar data_f 
					int min_doct = min_d.nextInt();
					int pcnt_sou = p_sou.nextInt();
					Torn t = new torn(data_i, data_f, min_doct, pcnt_sou);
					
					
				       					
					break;
				
				case 2:
					break;
					
				case 3: 
					break;
					
				default: 
					System.out.println("El número ha d'estar entre 1 i 5");
					break;
					
			}
        }	
		
		
	}
}
