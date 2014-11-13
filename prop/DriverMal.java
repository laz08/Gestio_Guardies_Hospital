import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

System.out.println("Escull un dels calendaris existents amb la seva plantilla");
		ctr.escriure_plantilles();
		System.out.println("Introdueix un nou torn: Data inici, Data fi(yyyy mm dd hh), numero mínim de doctors, tant per cent del sou a afegir");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH");
		aux = new Scanner(System.in);
		int any = aux.nextInt();
		aux = new Scanner(System.in);
		int mes = aux.nextInt();
		aux = new Scanner(System.in);
		int dia = aux.nextInt();
		aux = new Scanner(System.in);
		int hora = aux.nextInt();
		GregorianCalendar d_i = new GregorianCalendar(any,mes,dia,hora,0);
		aux = new Scanner(System.in);
		any = aux.nextInt();
		aux = new Scanner(System.in);
		mes = aux.nextInt();
		aux = new Scanner(System.in);
		dia = aux.nextInt();
		aux = new Scanner(System.in);
		hora = aux.nextInt();
		GregorianCalendar d_f = new GregorianCalendar(any,mes,dia,hora,0);

		aux = new Scanner(System.in);
		int min_doct = aux.nextInt();
		aux = new Scanner(System.in);
		int p_sou = aux.nextInt();
	
		Torn t = new Torn(d_i, d_f, min_doct, p_sou);