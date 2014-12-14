package prop;

import java.util.Scanner;


public class DriverTorn {
	
	private static Torn t =  new Torn(0,(float)0,0);
	static Scanner arg = new Scanner(System.in);
	static boolean primer = true;
	
	public static void main(String[] args) throws Error {
		
		int cas;
		boolean sortir = false;
		escriureMenu();
		
		while(!sortir && arg.hasNext()) {
			try {
				cas = arg.nextInt();
			} catch (Exception e) {
				System.out.println("s'esperava la introducció d'un número");
				System.out.print(">>");
				arg.next();
				continue;
			}
				
			switch (cas) {
				case 0: sortir = true; break;
				case 1: crearTorn(); break;
				case 2: modificarPercentatge(); break;
				case 3: modificarMinimDoctors(); break;
				case 4: consultarHorari(); break;
				case 5: consultarHoraInici(); break;
				case 6: consultarHoraFi(); break;
				case 7: consultarPercentatge(); break;
				case 8: consultarMinimDoctors(); break;
				case 9: borrarTorn(); break;
				default: 
					System.out.println("El numero ha d'estar entre 0 i 9.");
					break;
			
			}
			escriureMenu();
		}
	}
	
	public static void escriureMenu() {
		System.out.println("\n\n-----------Menú-----------\n"
				+ "1.- Crear Torn:\n"
				+ "(horari(mati=0, tarda=1 i nit=2), percentatge sou: float, numero minim doctors: int)\n"
				+ "--------------------------\n"
				+ "2.- Modificar percentatge sou(nou percentatge sou: float)\n"
				+ "3.- Modificar numero minim de doctors(nou numero minim doctors: int(positiu))\n"
				+ "--------------------------\n"
				+ "4.- Consultar horari\n"
				+ "5.- Consultar hora inici\n"
				+ "6.- Consultar hora fi\n"
				+ "7.- Consultar percentatge sou\n"
				+ "8.- Consultar numero minim de doctors\n"
				+ "---------------------------\n"
				+ "9.- Borrar torn\n"
				+ "---------------------------\n"
				+ "0.- Exit\n\n"
				+ "---------------------------\n");
				System.out.print(">> ");
	}
	
    public static int lecturaHorari(){
        boolean valid = false;
        int h = 0;
        while(!valid){
            try{
                h = arg.nextInt();
                if(h < 3 && h >= 0) valid = true;
                else{
                    System.out.println("Horari ha de tenir un valor entre 0 i 2.");
                    System.out.println("Matí = 0  //  Tarda = 1  //  Nit = 2");
                    System.out.println("Torna a introduir horari: ");
                    
                }
                
            } catch (Exception e){
                System.out.println("Horari ha de ser un enter.");
                System.out.print("Torna a introduir horari: ");
                arg.next();
                continue;

            }
        }
        return h;
    }
    public static float lecturaPercentatge(){
        boolean valid = false;
        float per = 0;
        while(!valid){
            try{
                per = arg.nextFloat();
                if(per >= 0) valid = true;
                else{
                    System.out.println("Percentatge ha de ser positiu.");
                    System.out.print("Torna a introduir el percentatge: ");
                    
                }
            } catch (Exception e){
                System.out.println("Percentatge ha de ser un núm.");
                System.out.print("Torna a introduir el percentatge: ");
                arg.next();
                continue;
            }
        }
        return per;
    }
    public static int lecturaNumMinimDocs(){
        boolean valid = false;
        int num_min = 0;
        while(!valid){
            try{
                num_min = arg.nextInt();
                if (num_min >= 0) valid = true;
                else{
                    System.out.println("Número mínim de doctors ha de ser un nombre positiu.");
                    System.out.print("Torna a introduir el numero minim de docs: ");
                    
                }
            } catch (Exception e){
                System.out.println("Numero mínim de docs ha de ser un enter.");
                System.out.print("Torna a introduir el numero minim de docs: ");
                arg.next();
                continue;
            }
        }
        return num_min;
    }
	
	public static void crearTorn() {
		int horari = lecturaHorari();
		float percent = lecturaPercentatge();
		int minim = lecturaNumMinimDocs();
		t.setHorari(horari);
		t.setPercent_sou(percent);
		t.setMin_num_doctors(minim);
		if(primer) primer=false;
	}
	
	public static void modificarPercentatge() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			float percent = lecturaPercentatge();
			t.setPercent_sou(percent);
		}
	}
	
	public static void modificarMinimDoctors() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			int minim = lecturaNumMinimDocs();
			t.setMin_num_doctors(minim);
		}
	}
	
	public static void consultarHorari() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			if(t.getHorari()==0)System.out.println("Mati");
			else if(t.getHorari()==1)System.out.println("Tarda");
			else System.out.println("Nit");
		}
	}
	
	public static void consultarHoraInici() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else System.out.println(t.getHora_inici());
	}
	
	public static void consultarHoraFi() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else System.out.println(t.getHora_fi());
	}
	
	public static void consultarPercentatge() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else System.out.println(t.getPercent_sou());
	}
	
	public static void consultarMinimDoctors() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else System.out.println(t.getMin_num_doctors());
	}
	
	public static void borrarTorn() {
		t.borrar();
	}
	
}