package prop;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DriverCalendari {
	
	static Calendari c = new Calendari();
    static Scanner arg = new Scanner(System.in);

	public static void main(String[] args) {

        int cas;
		boolean sortir = false;
		escriureMenu();

		while(!sortir && arg.hasNext()) {
            try {
                cas = arg.nextInt();
            } catch (Exception e){
                System.out.println("S'esperava la introduccio d'un numero.");
                System.out.print(">> ");
                arg.next();
                continue;
            }
			
			switch(cas) {
				case 0: sortir=true; break;
				case 1: crear_calendari(); break;
				case 2: consultar_id(); break;
				case 3: modificar_id(); break;
				case 4: consultar_id_plantilla(); break;
				case 5: modificar_id_plantilla(); break;
				case 6: consultar_torns(); break;
				case 7: afegir_torn(); break;
				case 8: eliminar_torn(); break;
				default: System.out.println("El número ha d'estar entre 0 i 8");
				break;
				
			}

            if(cas != 0) escriureMenu();
		}
	}

    public static void escriureMenu(){
        System.out.println("Menu: \n"
                + "1. Crear Calendari(id calendari: int, id plantilla: int, num torns: int, torn1...tornN\n"
                + "2. Consultar id calendari\n"
                + "3. Modificar id calendari(id nou: int)\n"
                + "4. Consultar id plantilla\n"
                + "5. Modificar id plantilla(id plantilla: int)\n"
                + "6. Consultar torns del calendari\n"
                + "7. Afegir torn al calendari(Torn(data inici:(yyyy: int, mm: int, dd:int, hh:int), data fi:(yyyy: int, mm: int, dd:int, hh:int), numero minim doctors: int, num doctors assignats: int, doctors assignats: String String .. String, percentatge sou: float))\n"
                + "8. Eliminar torn del calendari(Torn(data inici:(yyyy: int, mm: int, dd:int, hh:int), data fi:(yyyy: int, mm: int, dd:int, hh:int), numero minim doctors: int, num doctors assignats: int, doctors assignats: String String .. String, percentatge sou: float))\n"
                + "0. Sortir");
        System.out.print(">> ");
    }


	public static void crear_calendari(){
        boolean valid = false;

        int id = -1;
        while(!valid){
            try{
                id = arg.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("ID calendari ha de ser un numero.");
                System.out.println("Torna a introduir ID calendari.");
                arg.next();
                continue;
            }
        }


        String id_plt = arg.next();

		int n = -1;
        valid = false;
        while(!valid){
            try{
                n = arg.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("n ha de ser un numero.");
                System.out.println("Torna a introduir n.");
                arg.next();
                continue;
            }
        }

        ArrayList<Torn> lltorns = new ArrayList<Torn>();
		c = new Calendari(id,id_plt,lltorns);
		for(int i=0; i<n; i++) {
			afegir_torn();
		}
		
	}
	
	public static void consultar_id() {
		System.out.println("El identificador del calendari es: " + c.getId());
	}
	
	public static void modificar_id() {
		c.setId(arg.nextInt());
	}
	
	public static void consultar_id_plantilla() {
		System.out.println("El identificador de la plantilla associada al calendari es: " + c.getId_plantilla());
	}
	
	public static void modificar_id_plantilla() {
		c.setId_plantilla(arg.next());
	}
	
	public static void consultar_torns() {
		ArrayList<Torn> l = c.getTorns();
		for(int i=0; i<l.size(); i++) {
			escriure_torn(l.get(i));
			System.out.println("\n");
		}
	}
	
	public static void afegir_torn()  {
		Torn t = crear_torn();
		c.afegir_torn(t);
	}
	
	public static void eliminar_torn()  {
		Torn t = crear_torn();
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

    public static Torn crear_torn() {
        Torn t;
        boolean valid = false;


        // DATA INICI
        int any = -1;
        while(!valid){
            try{
                any = arg.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("Any ha de ser un numero.");
                System.out.println("Torna a introduir l'any.");
            }
        }

        valid = false;
        int mes = -1;
        while(!valid){
            try{
                mes = arg.nextInt() - 1;
                valid = true;
            } catch (Exception e){
                System.out.println("Mes ha de ser un numero.");
                System.out.println("Torna a introduir el mes.");
            }
        }

        valid = false;
        int dia = -1;
        while(!valid){
            try{
                dia = arg.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("Dia ha de ser un numero.");
                System.out.println("Torna a introduir el dia.");
            }
        }


        valid = false;
        int hora = -1;
        while(!valid){
            try{
                hora = arg.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("Hora ha de ser un numero.");
                System.out.println("Torna a introduir l'hora.");
                
            }
        }
        GregorianCalendar data_inici = new GregorianCalendar(any,mes,dia,hora,0);

        //DATA FI
        while(!valid){
            try{
                any = arg.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("Any ha de ser un numero.");
                System.out.println("Torna a introduir l'any.");
            }
        }

        valid = false;
        while(!valid){
            try{
                mes = arg.nextInt() - 1;
                valid = true;
            } catch (Exception e){
                System.out.println("Mes ha de ser un numero.");
                System.out.println("Torna a introduir el mes.");
            }
        }

        valid = false;
        while(!valid){
            try{
                dia = arg.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("Dia ha de ser un numero.");
                System.out.println("Torna a introduir el dia.");
            }
        }


        valid = false;
        while(!valid){
            try{
                hora = arg.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("Hora ha de ser un numero.");
                System.out.println("Torna a introduir l'hora.");
            }
        }
        GregorianCalendar data_fi = new GregorianCalendar(any,mes,dia,hora,0);

        valid = false;
        int min_doc = -1;
        while(!valid){
            try{
                min_doc = arg.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("Min_doc ha de ser un numero.");
                System.out.println("Torna a introduir el minim de doctors.");
            }
        }

        valid = false;
        int n = -1;
        while(!valid){
            try{
                n = arg.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("Min_doc ha de ser un numero.");
                System.out.println("Torna a introduir el minim de doctors.");
            }
        }

        String doc;
        ArrayList<String> doctors_assig =  new ArrayList<String>();
        for(int i=0; i<n; i++) {
            doc = arg.next();
            if(doctors_assig.isEmpty()) doctors_assig.add(doc);
            else {
                int j=0;
                while(j<doctors_assig.size() && doctors_assig.get(j).compareTo(doc)<0) ++j;
                doctors_assig.add(j,doc);
            }
        }
        float p_sou = arg.nextFloat();
        t = new Torn(data_inici,data_fi,min_doc,doctors_assig,p_sou);
        return t;
    }
}