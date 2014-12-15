package prop;

import java.util.Scanner;

public class DriverDia {
	
	private static Dia d = new Dia(false);
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
				
			switch(cas) {
				case 0: sortir = true; break;
				case 1: crearDia(); break;
				case 2: modificarDiaFestiu(); break;
				case 3: modificarTorns(); break;
				case 4: modificarTornMati(); break;
				case 5: modificarTornTarda(); break;
				case 6: modificarTornNit(); break;
				case 7: consultarDiaFestiu(); break;
				case 8: consultarTorns(); break;
				case 9: consultarTornMati(); break;
				case 10: consultarTornTarda(); break;
				case 11: consultarTornNit(); break;
				default:
					System.out.println("El numero ha d'estar entre 0 i 11.");
					break;
			}
			escriureMenu();
		}
	}
	
	
	public static boolean lecturaFestiu(){
        boolean valid = false;
        boolean b = false;
        while(!valid){
            try{
                b = arg.nextBoolean();
                valid = true;
            } catch (Exception e){
                System.out.println("Festiu ha de ser un boolea.");
                System.out.print("Torna a introduir festiu (true o false)");
                arg.next();
                continue;
            }
        }
        return b;
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
                    arg.next();
                }
                valid = true;
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
                    arg.next();
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
                    arg.next();
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

    public static int lecturaNumTorns() {
        boolean valid = false;
        int h = 0;
        while(!valid){
            try{
                h = arg.nextInt();
                if(h <= 3 && h >= 0) valid = true;
                else{
                    System.out.println("Només pots introduir de 0 a 3 torns");
                    System.out.println("Torna a introduir quants torns vols introduir: ");
                    //arg.next();
                }
            } catch (Exception e){
                System.out.println("Número de torns ha de ser un enter.");
                System.out.print("Torna a introduir quants torns vols: ");
                arg.next();
                continue;

            }
        }
        return h;
    }
	
	public static void escriureMenu() {
		System.out.println("\n\n----------Menú----------\n"
				+ "1.- Crear Dia(festiu: boolean, numero torns a crear: int(de 0 a 3))\n"
				+ "Per cada torn: (horari: int(mati=0, tarda=1, nit=2), percentatge sou: float, numero minim doctors: int)\n"
				+ "--------------------------\n"
				+ "2.- Modificar dia festiu o no(festiu: boolean)\n"
				+ "3.- Modificar torns (Introduir tres torns)\n"
				+ "4.- Modificar torn mati(nou torn)\n"
				+ "5.- Modificar torn tarda(nou torn)\n"
				+ "6.- Modificar torn nit(nou torn)\n"
				+ "--------------------------\n"
				+ "7.- Consultar si es festiu el dia\n"
				+ "8.- Consultar torns\n"
				+ "9.- Consultar torn mati\n"
				+ "10.- Consultar torn tarda\n"
				+ "11.- Consultar torn nit\n"
				+ "---------------------------\n"
				+ "0.- Exit\n\n");
	}
	
	public static void crearDia() {
		boolean b = lecturaFestiu();
		int n = lecturaNumTorns();
		for(int i=0; i<n; ++i) {
			int horari = lecturaHorari();
			float percent = lecturaPercentatge();
			int minim = lecturaNumMinimDocs();
			Torn t = new Torn(horari,percent,minim);
			if(i==0) d.setTornMati(t);
			else if(i==1) d.setTornTarda(t);
			else d.setTornNit(t);
		}
		d.setFestiu(b);
		if(primer) primer=false;
	}
	
	public static void modificarDiaFestiu() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			boolean b = lecturaFestiu();
			d.setFestiu(b);
		}
	}
	
	public static void modificarTorns() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			Torn[] ts = new Torn[3];
			for(int i=0; i<3; ++i) {
				float percent = lecturaPercentatge();
				int minim = lecturaNumMinimDocs();
				Torn t = new Torn(i,percent,minim);
				ts[i] = t;
			}
			d.setTorns(ts);
		}
	}
	
	public static void modificarTornMati() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			float percent = lecturaPercentatge();
			int minim = lecturaNumMinimDocs();
			Torn t = new Torn(0,percent,minim);
			d.setTornMati(t);
		}
	}
	
	public static void modificarTornTarda() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			float percent = lecturaPercentatge();
			int minim = lecturaNumMinimDocs();
			Torn t = new Torn(1,percent,minim);
			d.setTornTarda(t);
		}
	}
	
	public static void modificarTornNit() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			float percent = lecturaPercentatge();
			int minim = lecturaNumMinimDocs();
			Torn t = new Torn(2,percent,minim);
			d.setTornNit(t);
		}
	}
	
	public static void consultarDiaFestiu() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			System.out.println(d.getFestiu());
		}
	}
	
	public static void consultarTorns() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			Torn t;
			String s;
			for(int i=0; i<3; ++i) {
				if(i==0) {
					s="Mati"; 
					t= d.getTornMati();
				}
				else if(i==1) {
					s="Tarda"; 
					t = d.getTornTarda();
				}
				else {
					s="Nit"; 
					t=d.getTornNit();
				}
				System.out.println("Torn de "+s+":\n"
						+ "Hora inici: "+t.getHora_inici()+"\n"
						+ "Hora fi: "+t.getHora_fi()+"\n"
						+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
						+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
			}
		}
	}
	
	public static void consultarTornMati() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			Torn t = d.getTornMati();
			System.out.println("Torn de Mati:\n"
					+ "Hora inici: "+t.getHora_inici()+"\n"
					+ "Hora fi: "+t.getHora_fi()+"\n"
					+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
					+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
		}
	}
	
	public static void consultarTornTarda() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			Torn t = d.getTornTarda();
			System.out.println("Torn de Tarda:\n"
				+ "Hora inici: "+t.getHora_inici()+"\n"
				+ "Hora fi: "+t.getHora_fi()+"\n"
				+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
				+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
		}
	}
	
	public static void consultarTornNit() {
		if(primer) System.out.println("Primer hem de crear un torn(opció 1)");
		else {
			Torn t = d.getTornNit();
			System.out.println("Torn de Nit:\n"
				+ "Hora inici: "+t.getHora_inici()+"\n"
				+ "Hora fi: "+t.getHora_fi()+"\n"
				+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
				+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
		}
	}

	

}