package prop;

//import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Calendari {
	
	private Dia[] cal;
	//private int id;
	private int any;
	private Plantilla p;
	
	
	//Pre: No existeix un calendari per a la plantilla plt
	//Post: Creem un calendari per la plantilla plt des d'any_inici fins any_fi, buit, només amb els diumenges marcats com festius.
	public Calendari(String plt, int any_inici, int any_fi) {
		GregorianCalendar dia = new GregorianCalendar(any_inici,0,0);
		GregorianCalendar diaf = new GregorianCalendar(any_fi+1,0,0);
		long dies = diaf.getTimeInMillis() - dia.getTimeInMillis();
		dies = dies/1000/60/60/24;
		cal = new Dia[(int) dies];
		any =  any_inici;
		afegirFestius(); //Afegim a tots els diumenges de l'any el boolea festiu true
		setPlantillaAssociada(plt);
		//id = Integer.parseInt(plt);
	}
	
	//Pre: -
	//Post: Retornem el calendari en el que ens trobem
	public Dia[] getCalendari() {
		return cal;
	}
	
	//Pre: -
	//Post: Modifiquem el calendari pel calendari que ens arriba
	public void setCalendari(Dia[] c) {
		cal = c;
	}
	
	//Pre: -
	//Post: Retornem el nom de la plantilla associada a aquest calendari
	public String getPlantillaAssociada() {
		return p.getNomPlantilla();
	}
	
	//Pre: La plantilla nom existeix
	//Post: Associem la plantilla nom amb el calendari que estem
	public void setPlantillaAssociada(String nom) {
		//p = CtrlPlantilla.getPlantillaNom(nom);
		p =  CtrlPlantilla.consultarPlantilla(nom);
	}
	
	//Pre: -
	//Post: Borrem tota la informació del calendari
	public void borrar() {
		p = null;
		cal = null;
		any = 0;
	}
	

	//--------FUNCIONS PER DIES--------
	
	public Dia consultarDiaConcret(GregorianCalendar dia) {
		int i = calcularPosicioDia(dia);
		return cal[i];
	}
	
	public void borrarDiaConcret(GregorianCalendar dia) {
		int i = calcularPosicioDia(dia);
		cal[i].borrar();
	}
	
	//--------FUNCIONS PER TORNS CONCRETS------
	public void afegirTornDia(Torn t, GregorianCalendar dia) {
		int posicio = calcularPosicioDia(dia);
		cal[posicio].setTorn(t);
	}
	
	public void eliminarTornDia(int tipus, GregorianCalendar dia) {
		int posicio = calcularPosicioDia(dia);
		cal[posicio].borrarTorn(tipus);
	}
	
	public Torn consultarTornDia(int tipus, GregorianCalendar dia) {
		int posicio = calcularPosicioDia(dia);
		return cal[posicio].getTorn_concret(tipus);
	}
	
	public void modificarTornDia(Torn t, GregorianCalendar dia) {
		int posicio = calcularPosicioDia(dia);
		cal[posicio].setTorn(t);
	}
	
	/**public void setIdentificador(int ident) {
		id = ident;
	}*/
	


	

	//------ FUNCIONS AUXILIARS ------
	
	//Pre: -
	//Post: Afegeix el boolea festiu=true a tots els diumenges de l'any
	public void afegirFestius() {
		GregorianCalendar dia1 = new GregorianCalendar(any,1,1);
		int diaset = dia1.get(Calendar.DAY_OF_WEEK);
		if(diaset==2) diaset=6;
		else if(diaset==3) diaset=5;
		else if(diaset==4) diaset=4;
		else if(diaset==5) diaset=3;
		else if(diaset==6) diaset=2;
		else if(diaset==7) diaset=1;
		else --diaset;
		for(int i=diaset; i<cal.length; i=i+7){
			cal[i].getFestiu();
		}
	}
	
	//Pre: dia pertany a l'any
	//Post: Ens retorna la posició on es troba el dia en qüestió en el nostre calendari
	public int calcularPosicioDia(GregorianCalendar dia) {
		GregorianCalendar primerdia = new GregorianCalendar(any,1,1);
		long dif = primerdia.getTimeInMillis() - dia.getTimeInMillis();
		dif = dif/1000/60/60/24;
		return (int) dif;
		
	}
	
	//Pre: i pertany a una posicio del vector
	//Post: Retorna la data que te la posicio i al vector.
	public GregorianCalendar quinDia(int i) {
		GregorianCalendar primerdia = new GregorianCalendar(any,1,1);
		primerdia.add(Calendar.DAY_OF_YEAR,i);
		return primerdia;		
	}
	
}