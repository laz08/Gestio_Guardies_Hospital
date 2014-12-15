package prop;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DriverCalendari {
	
	private static Calendari c = new Calendari(null,0,0);
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
				case 1: crearCalendari(); break;
				case 2: modificarPlantilla(); break;
				case 3: modificarDia(); break;
				case 4: modificarTornMati(); break;
				case 5: modificarTornTarda(); break;
				case 6: modificarTornNit(); break;
				case 7: consultarAny(); break;
				case 8: consultarCalendari(); break;
				case 9: consultarPlantilla(); break;
				case 10: consultarDiaConcret(); break;
				case 11: consultarTornMati(); break;
				case 12: consultarTornTarda(); break;
				case 13: consultarTornNit(); break;
				case 14: posicio(); break;
				default:
					System.out.println("El numero ha d'estar entre 0 i 7.");
					break;
				
			}
			escriureMenu();
		}
	}
	
	public static void escriureMenu() {
		System.out.println("\n\n----------Menú----------\n"
				+ "1.- Crear Calendari(identificador plantilla: String, any inici: int, any fi: int)\n"
				+ "--------------------------\n"
				+ "2.- Modificar Plantilla associada(identificador plantilla nova: String)\n"
				+ "3.- Modificar Dia(data(any,mes,dia) nou dia: festiu: boolea, tres torns)\n"
				+ "4.- Modificar Torn Mati(data(any,mes,dia))\n"
				+ "5.- Modificar Torn Tarda(data(any,mes,dia))\n"
				+ "6.- Modificar Torn Nit(data(any,mes,dia))\n"
				+ "--------------------------\n"
				+ "7.- Consultar any\n"
				+ "8.- Consultar Calendari\n"
				+ "9.- Consultar Plantilla associada\n"
				+ "10.- Consultar dia concret (any, mes dia)\n"
				+ "11.- Consultar torn mati(any, mes, dia)\n"
				+ "12.- Consultar torn tarda(any, mes, dia)\n"
				+ "13.- Consultar torn nit(any, mes, dia)\n"
				+ "14.- Posicio\n"
				+ "--------------------------\n"
				+ "0.- Exit\n\n");
	}
	
	
    public static boolean esAnyDeTraspas(int any){
        //Retorna true si l'any és múltiple de 4
        //i no és multiple de cent pero si que es multiple de 400

        return (any % 4 == 0) && !(any % 100 == 0 && any % 400 != 0);
    }
    public static int lecturaAny(){
        boolean valid = false;
        int any = 0;
        while(!valid){
            try{
                any = arg.nextInt();
                int tam = c.getCalendari().length/365;
                int anyf = c.getAny()+tam -1;
                if (any > 0 && any >= c.getAny() && any <= anyf) valid = true;
                else {
                    System.out.println("Any ha de ser un dels anys que té el calendari "+c.getAny()+" fins "+anyf);
                    System.out.print("Torna a introduir any: ");
                }
            } catch (Exception e){
                System.out.println("Any ha de ser un enter.");
                System.out.print("Torna a introduir any: ");
                arg.next();
                continue;
            }
        }
        return any;
    }
    public static int lecturaMes(){
        boolean valid = false;
        int mes = 0;
        while(!valid){
            try{
                mes = arg.nextInt();
                if(mes > 0 && mes < 13) valid = true;
                else{
                    System.out.println("Mes ha de tenir un valor entre 1 i 12.");
                    System.out.print("Torna a introduir mes: ");
                }
            } catch(Exception e){
                System.out.println("Mes ha de ser un enter.");
                System.out.print("Torna a introduir mes: ");
                arg.next();
                continue;
            }
        }
        return mes;
    }
    public static int lecturaDia(int any, int mes){
        boolean valid = false;
        int dia = 0;
        while(!valid){
            try{
                dia = arg.nextInt();
                //Mesos de 31 dies
                if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
                    if (dia > 0 && dia < 32) valid = true;
                    else{
                        System.out.println("El mes "+mes+" té un rang de dies de l'1 fins al 31");
                        System.out.print("Torna a introduir dia: ");
                    }
                }
                //Mes de febrer
                else if (mes == 2){
                    if(esAnyDeTraspas(any)){
                        // 1 =< Dia =< 29
                        if(dia > 0 && dia < 30) valid = true;
                        else {
                            System.out.println("El mes "+mes+" en l'any "+any+" té un rang de dies de l'1 fins al 29");
                            System.out.print("Torna a introduir dia: ");
                        }
                    }
                    //No és any de traspàs
                    else{
                        //28 dies
                        if(dia > 0 && dia < 29) valid = true;
                        else {
                            System.out.println("El mes "+mes+" en l'any "+any+" té un rang de dies de l'1 fins al 28");
                            System.out.print("Torna a introduir dia: ");
                        }
                    }
                }
                //Mesos de 30 dies
                else{
                    if (dia > 0 && dia < 31) valid = true;
                    else{
                        System.out.println("El mes "+mes+" té un rang de dies de l'1 fins al 30");
                        System.out.print("Torna a introduir dia: ");
                    }
                }
            } catch (Exception e){
                System.out.println("Dia ha de ser un enter.");
                System.out.print("Torna a introduir dia: ");
                arg.next();
                continue;
            }
        }
        return dia;
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

    public static void crearCalendari(){
        String nom_p = arg.next();
        
        boolean valid = false;
        int any_i = 0;
        while(!valid){
            try {
                any_i = arg.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("Any d'inici ha de ser un enter.");
                System.out.print("Torna a introduir any d'inici: ");
                arg.next();
                continue;
            }
        }
        int any_f = 0;
        valid = false;
        while(!valid){
            try {
                any_f = arg.nextInt();
                if (any_f>=any_i && any_f >= 0) valid = true;
                else{
                    System.out.println("Any final ha de ser un enter i major que any d'inici.");
                    System.out.print("Torna a introduir any final: ");
                    //arg.next();
                }
            } catch (Exception e){
                System.out.println("Any final ha de ser un enter i major que any d'inici.");
                System.out.print("Torna a introduir any final: ");
                arg.next();
                continue;
            }
        }

        c = new Calendari(nom_p,any_i,any_f);
        if(primer) primer=false;

    }
	
    public static void posicio() {
    	int pos = arg.nextInt();
    	Dia d = c.getCalendari()[pos];
		GregorianCalendar data = quinDia(pos,c.getAny());
		System.out.println("El dia "+data.getTime().toString());
		if(d.getFestiu())System.out.println("Es festiu");
		else System.out.println("No es festiu");
		Torn t;
		String s;
		for(int j=0; j<3; ++j) {
			if(j==0) {
				s="Mati";
				t=d.getTornMati();
			}
			else if(j==1)  {
				s="Tarda";
				t=d.getTornTarda();
			}
			else {
				s="Nit";
				t = d.getTornNit();
			}
			System.out.println("Torn de "+s+":\n"
					+ "Hora inici: "+t.getHora_inici()+"\n"
					+ "Hora fi: "+t.getHora_fi()+"\n"
					+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
					+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
		}
    }
	
	/**public static void modificarIdentificador() {
		int id = arg.nextInt();
		c.setIdentificador(id);
	}*/
	
	public static void modificarPlantilla() {
		if(primer) System.out.println("Has de crear primer el calendari(opció1)");
		else {
			String pltilla = arg.next();
			c.setPlantillaAssociada(pltilla);
		}
	}
	
	public static void modificarDia() {
		if(primer) System.out.println("Has de crear primer el calendari(opció1)");
		else {
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			boolean b = lecturaFestiu();
			Dia d = new Dia(b);
			for(int i=0; i<3; ++i) {
				float percent = lecturaPercentatge();
				int minim = lecturaNumMinimDocs();
				Torn t = new Torn(i,percent,minim);
				if(i==0) d.setTornMati(t);
				else if(i==1) d.setTornTarda(t);
				else d.setTornNit(t);
			}		
			int pos = calcularPosicioDia(data,c.getAny());
			c.getCalendari()[pos]= d;
		}
	}
	
	public static void modificarTornMati() {
		if(primer) System.out.println("Has de crear primer el calendari(opció1)");
		else {
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			float percent = lecturaPercentatge();
			int minim = lecturaNumMinimDocs();
			Torn t = new Torn(0,percent,minim);
			int pos = calcularPosicioDia(data,c.getAny());
			c.getCalendari()[pos].setTornMati(t);
		}
	}
	
	public static void modificarTornTarda() {
		if(primer) System.out.println("Has de crear primer el calendari(opció1)");
		else {
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			float percent = lecturaPercentatge();
			int minim = lecturaNumMinimDocs();
			Torn t = new Torn(1,percent,minim);
			int pos = calcularPosicioDia(data,c.getAny());
			c.getCalendari()[pos].setTornTarda(t);
		}
	}
	
	public static void modificarTornNit() {
		if(primer) System.out.println("Has de crear primer el calendari(opció1)");
		else {
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			float percent = lecturaPercentatge();
			int minim = lecturaNumMinimDocs();
			Torn t = new Torn(2,percent,minim);
			int pos = calcularPosicioDia(data,c.getAny());
			c.getCalendari()[pos].setTornNit(t);
		}
	}
	
	public static void consultarAny() {
		if(primer) System.out.println("Has de crear primer el calendari(opció1)");
		else {
			System.out.println(c.getAny());
		}
	}
	
	public static void consultarCalendari() {
		if(primer) System.out.println("Has de crear primer el calendari(opció1)");
		else {
			for(int i=0; i<c.getCalendari().length; ++i){
				Dia d = c.getCalendari()[i];
				GregorianCalendar data = quinDia(i,c.getAny());
				System.out.println("El dia "+data.getTime().toString());
				if(d.getFestiu())System.out.println("Es festiu");
				else System.out.println("No es festiu");
				Torn t;
				String s;
				for(int j=0; j<3; ++j) {
					if(j==0) {
						s="Mati";
						t=d.getTornMati();
					}
					else if(j==1)  {
						s="Tarda";
						t=d.getTornTarda();
					}
					else {
						s="Nit";
						t = d.getTornNit();
					}
					System.out.println("Torn de "+s+":\n"
							+ "Hora inici: "+t.getHora_inici()+"\n"
							+ "Hora fi: "+t.getHora_fi()+"\n"
							+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
							+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
				}
			}
		}
	}
	
	public static void consultarPlantilla() {
		if(primer) System.out.println("Has de crear primer el calendari(opció1)");
		else {
			System.out.println(c.getPlantillaAssociada());
		}
	}
	
	public static void consultarDiaConcret() {
		if(primer) System.out.println("Has de crear primer el calendari(opció1)");
		else {
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			int pos = calcularPosicioDia(data,c.getAny());
			Dia d = c.getCalendari()[pos];
			System.out.println("El dia "+dia+"/"+mes+"/"+any);
			if(d.getFestiu())System.out.println("Es festiu"+pos);
			else System.out.println("No es festiu"+pos);
			Torn t;
			String s;
			for(int i=0; i<3; ++i) {
				if(i==0) {
					s="Mati";
					t = d.getTornMati();
				}
				else if(i==1) {
					s="Tarda";
					t = d.getTornTarda();
				}
				else {
					s="Nit";
					t = d.getTornNit();
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
		if(primer) System.out.println("Has de crear primer el calendari(opció1)");
		else {
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			int pos = calcularPosicioDia(data,c.getAny());
			Torn t = c.getCalendari()[pos].getTornMati();
			System.out.println("Torn de mati:\n"
					+ "Hora inici: "+t.getHora_inici()+"\n"
					+ "Hora fi: "+t.getHora_fi()+"\n"
					+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
					+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
		}
	}
	
	public static void consultarTornTarda() {
		if(primer) System.out.println("Has de crear primer el calendari(opció1)");
		else {
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			int pos = calcularPosicioDia(data,c.getAny());
			Torn t = c.getCalendari()[pos].getTornTarda();
			System.out.println("Torn de tarda:\n"
					+ "Hora inici: "+t.getHora_inici()+"\n"
					+ "Hora fi: "+t.getHora_fi()+"\n"
					+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
					+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
		}
	}
	
	public static void consultarTornNit() {
		if(primer) System.out.println("Has de crear primer el calendari(opció1)");
		else {
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			int pos = calcularPosicioDia(data,c.getAny());
			Torn t = c.getCalendari()[pos].getTornNit();
			System.out.println("Torn de mati:\n"
					+ "Hora inici: "+t.getHora_inici()+"\n"
					+ "Hora fi: "+t.getHora_fi()+"\n"
					+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
					+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
		}
	}
	
	//Pre: dia pertany a l'any
	//Post: Ens retorna la posició on es troba el dia en qüestió en el nostre calendari
	public static int calcularPosicioDia(GregorianCalendar dia, int any) {
		GregorianCalendar primerdia = new GregorianCalendar(any,0,1);
		long dif = dia.getTimeInMillis() - primerdia.getTimeInMillis();
		dif = dif/1000/60/60/24;
		return (int) dif;
		
	}
	
	//Pre: i pertany a una posicio del vector
	//Post: Retorna la data que te la posicio i al vector.
	public static GregorianCalendar quinDia(int i, int any) {
		GregorianCalendar primerdia = new GregorianCalendar(any,0,1);
		primerdia.add(Calendar.DAY_OF_YEAR,i);
		return primerdia;		
	}
	
}