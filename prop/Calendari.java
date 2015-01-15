package prop;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Calendari {
	
	private Dia[] cal;
	private String pl;
	private int any;
	private int anyfi;
	private Plantilla p;
	

    /**
     * Pre: No existeix un calendari per a la plantilla plt
     * Post: Creem un calendari per la plantilla plt des d'any_inici fins any_fi, buit, només amb els diumenges marcats com festius.
     * @param plt
     * @param any_inici
     * @param any_fi
     */
    public Calendari(String plt, int any_inici, int any_fi) {
		GregorianCalendar dia = new GregorianCalendar(any_inici,0,1);
		GregorianCalendar diaf = new GregorianCalendar(any_fi+1,0,1);
		long dies = diaf.getTimeInMillis() - dia.getTimeInMillis();
		dies = dies/1000/60/60/24;
		cal = new Dia[(int) dies];
		any =  any_inici;
		anyfi = any_fi;
		afegirPosicio();
		//afegirFestius(); //Afegim a tots els diumenges de l'any el boolea festiu true
		setPlantillaAssociada(plt);
		pl = plt;
	}

    /**
     * Retorna l'any d'inici del calendari seleccionat
     * @return
     */
	public int getAny() {
		return any;
	}

    /**
     * Retorna l'any final del calendari seleccionat
     * @return
     */
	public int getAnyFi() {
		return anyfi;
	}

    /**
     * Retorna el calendari seleccionat
     * @return
     */
	public Dia[] getCalendari() {
		return cal;
	}

    /**
     * Modifica el calendari pel calendari c
     * @param c
     */
    public void setCalendari(Dia[] c) {
		cal = c;
	}

    /**
     * Retorna el nom de la plantilla associada a aquest calendari
     * @return
     */
    public String getPlantillaAssociada() {
		return pl;
	}

    /**
     * Pre: La plantilla nom existeix
     * Post: Associem la plantilla nom amb el calendari que estem
     * @param nom
     */
    public void setPlantillaAssociada(String nom) {
		p =  CtrlPlantilla.consultarPlantilla(nom);
		pl = nom;
	}
	

	

	//------ FUNCIONS AUXILIARS ------

    /**
     * Afegeix el boolea festiu=true a tots els diumenges de l'any
     */
	public void afegirFestius() {
			GregorianCalendar dia1 = new GregorianCalendar(any,0,1);
			int diaset = dia1.get(Calendar.DAY_OF_WEEK);
			if(diaset==2) diaset=6;
			else if(diaset==3) diaset=5;
			else if(diaset==4) diaset=4;
			else if(diaset==5) diaset=3;
			else if(diaset==6) diaset=2;
			else if(diaset==7) diaset=1;
			else diaset=0;
			for(int i=diaset; i<cal.length; i+=7){
				cal[i].setFestiu(true);
			}
		
	}

    /**
     * Afegeix la posicio de calendari a la qual pertany el torn
     */
	public void afegirPosicio() {
		for(int i=0; i<cal.length; ++i){
			cal[i] = new Dia(false);
			for(int j=0; j<3; ++j) cal[i].getTorns()[j].setPosicio(i);
		}
	}
	
}