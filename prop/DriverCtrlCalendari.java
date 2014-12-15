package prop;

import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.TreeSet;

public class DriverCtrlCalendari {
	
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
				
				case 1: consultarLlistaCalendaris(); break;
				
				case 2:	crearCalendari(); break;
				case 3: eliminarCalendari(); break;
				case 4: existeixCalendari(); break;
				case 5: consultarCalendari(); break;
				case 6: consultarAnyCalendari(); break;
				case 7: modificarPltCalendari(); break;
				
				case 8: borrarDia(); break;
				case 9: borrarTornMati(); break;
				case 10: borrarTornTarda(); break;
				case 11: borrarTornNit(); break;
				case 12: consultarDia(); break;
				case 13: consultarDiaFestiu(); break;
				case 14: consultarTornsDia(); break;
				case 15: consultarTornMati(); break;
				case 16: consultarTornTarda(); break;
				case 17: consultarTornNit(); break;
				case 18: modificarDia(); break;
				case 19: modificarDiaFestiu(); break;
				
				case 20: modificarTornsDia(); break;
				case 21: borrarTorn(); break;
				case 22: consultarTorn(); break;
				case 23: consultarHoraIniciTorn(); break;
				case 24: consultarHoraFiTorn(); break;
				case 25: consultarPercentatgeTorn(); break;
				case 26: consultarMinimTorn(); break;
				case 27: modificarTorn(); break;
				case 28: modificarPercentatgeTorn(); break;
				case 29: modificarMinimTorn(); break;
				case 30: guardar(); break;
				case 31: carregar(); break;
				default:
					System.out.println("El numero ha d'estar entre 0 i 31.");
					break;
			}
			escriureMenu();
		}
	}
	
	public static void escriureMenu() {
		System.out.println("\n\n----------Menú----------\n"
				+ "--------------CONJUNT CALENDARIS-----------\n"
				+ "1.- Consultar llista de plantilles que tenen calendari\n"
				+ "------------------CALENDARI----------------\n"
				+ "2.- Crear Calendari(identificador plantilla: String, any inici: int, any fi: int)\n"
				+ "3.- Eliminar Calendari(id plantilla: String)\n"
				+ "4.- Existeix Calendari?(id plantilla: String)\n"
				+ "5.- Consultar Calendari(id plantilla: String)\n"
				+ "6.- Consultar any inici Calendari(id plantilla: String)"
				+ "7.- Modificar Calendari-> canviem plantilla associada(id plantilla: String, id plantilla nova: String)\n"
				+ "---------------------DIA---------------------\n"
				+ "8.- Borrar(informació) Dia(id plantilla: String, data(any,mes,dia))\n"
				+ "9.- Borrar(informació) torn mati(id plantilla: String, data(any,mes,dia))\n"
				+ "10.- Borrar(informació) torn tarda(id plantilla: String, data(any,mes,dia))\n"
				+ "11.- Borrar(informació) torn nit(id plantilla: String, data(any,mes,dia))\n"
				+ "12.- Consultar Dia(id plantilla: String, data(any,mes,dia))\n"
				+ "13.- Consultar Dia Festiu?(id plantilla: String, data(any,mes,dia))\n"
				+ "14.- Consultar torns Dia(id plantilla: String, data(any,mes,dia))\n"
				+ "15.- Consultar torn mati(id plantilla: String, data(any,mes,dia))\n"
				+ "16.- Consultar torn tarda(id plantilla: String, data(any,mes,dia))\n"
				+ "17.- Consultar torn nit(id plantilla: String, data(any,mes,dia))\n"
				+ "18.- Modificar Dia(id plantilla: String, data(any,mes,dia), nou Dia(festiu? i 3 torns)\n"
				+ "19.- Modificar Dia festiu(id plantilla: String, data(any,mes,dia), boolea festiu)\n"
				+ "20.- Modificar torns del Dia(id plantilla: String, data(any,mes,dia), 3 Torns)\n"
				+ "----------------------TORN-------------------\n"
				+ "21.- Borrar(informació) Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2))\n"
				+ "22.- Consultar Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2))\n"
				+ "23.- Consultar hora inici Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2))\n"
				+ "24.- Consultar hora fi Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2))\n"
				+ "25.- Consultar percentatge sou del Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2))\n"
				+ "26.- Consultar numero minim de doctors del Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2))\n"
				+ "27.- Modificar Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2), nou Torn)\n"
				+ "28.- Modificar percentatge sou del Torn(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2), nou percentatge)\n"
				+ "29.- Modificar numero mínim de doctors(id plantilla: String, data(any,mes,dia), horari(mati=0, tarda=1, nit=2), nou mínim de doctors)\n"
				+ "--------------------------\n"
				+ "30.- Guardar\n"
				+ "31.- Carregar\n"
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
                if (any > 0) valid = true;
                else {
                    System.out.println("Any ha de tenir un valor positiu.");
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

    public static int lecturaNumTorns() {
        boolean valid = false;
        int h = 0;
        while(!valid){
            try{
                h = arg.nextInt();
                if(h < 3 && h >= 0) valid = true;
                else{
                    System.out.println("Només pots introduir de 0 a 3 torns");
                    System.out.println("Torna a introduir quants torns vols introduir: ");
                }
                valid = true;
            } catch (Exception e){
                System.out.println("Número de torns ha de ser un enter.");
                System.out.print("Torna a introduir quants torns vols: ");
                arg.next();
                continue;

            }
        }
        return h;
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
                }
            } catch (Exception e){
                System.out.println("Any final ha de ser un enter y superior a any inici.");
                System.out.print("Torna a introduir any final: ");
                arg.next();
                continue;
            }
        }

        if(!CtrlPlantilla.existeixPlantilla(nom_p)) CtrlPlantilla.creariAfegirPlantilla(nom_p);
        if(!CtrlCalendari.existeixCalendari(nom_p)){
            CtrlCalendari.crearIafegirCalendari(nom_p,any_i,any_f);
        }
        else System.out.println("Ja existeix calendari per a la plantilla " + nom_p);
        
        if(primer) primer=false;

    }
	
	public static void consultarLlistaCalendaris() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
	        TreeSet<Calendari> Cjt = CtrlCalendari.getLlcalendaris();
	        for(Calendari c:Cjt)
	            System.out.println("La plantilla: "+c.getPlantillaAssociada()+" té un calendari de l'any "+c.getAny());
		}
	}
	
	
	public static void eliminarCalendari() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			CtrlCalendari.eliminarCalendari(plt);
		}
	}
	
	public static void existeixCalendari() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			if(CtrlCalendari.existeixCalendari(plt)) System.out.println("La plantilla plt té calendari");
			else System.out.println("La plantilla plt no té calendari associat");
		}
	}
	
	public static void consultarCalendari() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			Calendari c = CtrlCalendari.consultarCalendari(plt);
			for(int i=0; i<c.getCalendari().length; ++i){
				Dia d = c.getCalendari()[i];
				escriureDia(d,CtrlCalendari.quinDia(i,c.getAny()));
			}
		}
	}
	
	public static void consultarAnyCalendari() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			System.out.println(CtrlCalendari.consultarCalendari(plt).getAny());
		}
	}
	
	public static void modificarPltCalendari() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			String pltnova = arg.next();
			if(!CtrlCalendari.existeixCalendari(pltnova)) CtrlCalendari.modificarPlt(plt,pltnova);
			else System.out.println("La plantilla nova ja té calendari associat");
		}
	}
	
	public static void borrarDia() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			CtrlCalendari.borrarDia(plt,data);
		}
	}
	
	public static void borrarTornMati() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			CtrlCalendari.borrarTornHorari(plt,data,0);
		}
	}
	
	public static void borrarTornTarda() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			CtrlCalendari.borrarTornHorari(plt,data,1);
		}
	}
	
	public static void borrarTornNit() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			CtrlCalendari.borrarTornHorari(plt,data,2);
		}
	}
	
	public static void consultarDia() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			Dia d = CtrlCalendari.consultarDia(plt,data);
			escriureDia(d,data);
		}
	}
	
	public static void consultarDiaFestiu() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			if(CtrlCalendari.consultarDiaFestiu(plt,data)) System.out.println("El dia "+data.getTime().toString()+"és festiu");
			else System.out.println("El dia "+data.getTime().toString()+" no és festiu");
		}
	}
	
	public static void consultarTornsDia() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			Torn[] ts = CtrlCalendari.consultarTornsDia(plt,data);
			for(int i=0; i<3; ++i) escriureTorn(ts[i],i);
		}
	}
	
	public static void consultarTornMati() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			Torn t = CtrlCalendari.consultarTorn(data, plt, 0);
			escriureTorn(t,0);
		}
	}
	
	public static void consultarTornTarda() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			Torn t = CtrlCalendari.consultarTorn(data, plt, 1);
			escriureTorn(t,1);
		}
	}
	
	public static void consultarTornNit() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			Torn t = CtrlCalendari.consultarTorn(data, plt, 2);
			escriureTorn(t,2);
		}
	}
	
	public static void modificarDia() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			boolean b = lecturaFestiu();
			int n = lecturaNumTorns();
			Dia d = new Dia(b);
			for(int i=0; i<n; ++i) {
				int horari = lecturaHorari();
				float percent = lecturaPercentatge();
				int minim = lecturaNumMinimDocs();
				Torn t = new Torn(horari,percent,minim);
				if(horari==0) d.setTornMati(t);
				else if(horari==1) d.setTornTarda(t);
				else d.setTornNit(t);
			}
			CtrlCalendari.modificarDia(plt,data,d);
		}
	}
	
	public static void modificarDiaFestiu() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			boolean b = lecturaFestiu();
			CtrlCalendari.modificarDiaFestiu(plt,data,b);
		}
	}
	
	public static void modificarTornsDia() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			Torn[] ts = new Torn[3];
			for(int i=0; i<3; ++i) {
				float percent = arg.nextFloat();
				int minim = arg.nextInt();
				Torn t = new Torn(i,percent,minim);
				ts[i] = t;
			}
			CtrlCalendari.modificarTornsDia(plt,data,ts);
		}
	}
	
	public static void borrarTorn() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			int horari = lecturaHorari();
			CtrlCalendari.borrarTornHorari(plt,data,horari);
		}
	}
	
	public static void consultarTorn() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			int horari = lecturaHorari();
			Torn t = CtrlCalendari.consultarTorn(data, plt, horari);
			escriureTorn(t,horari);
		}
	}
	
	public static void consultarHoraIniciTorn() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			int horari = lecturaHorari();
			int hora = CtrlCalendari.consultarHoraIniciTorn(data, plt, horari);
			System.out.println(hora);
		}
	}
	
	public static void consultarHoraFiTorn() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			int horari = lecturaHorari();
			int hora = CtrlCalendari.consultarHoraFiTorn(data, plt, horari);
			System.out.println(hora);
		}
	}
	
	public static void consultarPercentatgeTorn() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			int horari = lecturaHorari();
			float percent = CtrlCalendari.consultarPercentatgeTorn(data, plt, horari);
			System.out.println(percent);
		}
	}
	
	public static void consultarMinimTorn() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			int horari = lecturaHorari();
			int minim = CtrlCalendari.consultarMinimTorn(data, plt, horari);
			System.out.println(minim);
		}
	}
	
	public static void modificarTorn() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			int horari = lecturaHorari();
			float percent = lecturaPercentatge();
			int minim = lecturaNumMinimDocs();
			Torn t = new Torn(horari,percent,minim);
			CtrlCalendari.modificarTorn(t, data, plt, horari);
		}
	}

	public static void modificarPercentatgeTorn() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {	
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			int horari = lecturaHorari();
			float percent = lecturaPercentatge();
			CtrlCalendari.modificarPercentatgeTorn(percent, data, plt, horari);
		}
	}
	
	public static void modificarMinimTorn() {
		if(primer) System.out.println("Has de crear primer algun calendari(opció2)");
		else {
			String plt = arg.next();
			int any = lecturaAny();
			int mes = lecturaMes();
			int dia = lecturaDia(any,mes);
			GregorianCalendar data = new GregorianCalendar(any,mes,dia);
			int horari = lecturaHorari();
			int minim = lecturaNumMinimDocs();
			CtrlCalendari.modificarMinimTorn(minim, data, plt, horari);
		}
	}
	
	public static void guardar() {
		CtrlCalendari.guardar();
	}
	
	public static void carregar() {
		CtrlCalendari.carregar();
	}
	
	public static void escriureTorn(Torn t, int i) {
		String s;
		if(i==0) s="Mati";
		else if(i==1) s="Tarda";
		else s="Nit";
	
		System.out.println("Torn de "+s+":\n"
				+ "Hora inici: "+t.getHora_inici()+"\n"
				+ "Hora fi: "+t.getHora_fi()+"\n"
				+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
				+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
	
	}
	
	public static void escriureDia(Dia d, GregorianCalendar data) {
		String da = data.getTime().toString();
		System.out.println("El dia "+da);
		if(d.getFestiu())System.out.println("Es festiu");
		else System.out.println("No es festiu");
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