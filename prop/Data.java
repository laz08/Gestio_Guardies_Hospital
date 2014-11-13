
package prop;

import java.util.ArrayList;

public class Data {
    private int dia, mes;
    private ArrayList torns = new ArrayList<Torn>(); 
    
    /**
     * Crea un objecte data que fa referència a un dia i un mes concrets
     * @param dia Número del dia
     * @param mes Número del mes
     * @throws Error 
     */
    public Data(int dia, int mes) throws Error{
        if(dia <= 0) throw new Error("El dia no pot ser negatiu");
        if(mes <= 0) throw new Error("El mes no pot ser negatiu");
        if(mes > 12) throw new Error("El mes no pot ser major a 12");
        this.mes = mes;
        switch(mes){
            case 1:case 3:case 5:case 7:case 8:case 10:case 12:
                if(dia>31) throw new Error("El dia no pot ser major a 31");
                break;
            case 4:case 6:case 9:case 11:
                if(dia>30) throw new Error("El dia no pot ser major a 30");
                break;
            case 2:
                if(dia>29) throw new Error("El dia no pot ser major a 29");
                break;
        }
        this.dia = dia;
    }
    
    /**
     * Retorna el dia del mes al que fa referència la data
     * @return 
     */
    public int getDia(){
        return dia;
    }
    
    /**
     * Retorna el número del mes al que fa referència la data
     * @return
     */
    public int getMes(){
        return mes;
    }
    
    /**
     * Afegeix el torn al final de la llista
     * @param t Torn que es vol afegir
     */
    public void addTorn(Torn t){
        torns.add(t);
    }
    
    /**
     * Afegeix un torn a una posició determinada de la llista
     * Si la llista te algun element a la posició a la que es vol inserir el torn es desplaça aquest i tots els que estiguin a la seva dreta una posició
     * @param t Torn que es vol afegir
     * @param pos Posicio a la que es vol afegir el torn
     */
    public void addTorn(Torn t, int pos){
        torns.add(pos, t);
    }
    
    /**
     * Retorna un torn de la llista de torns que pertanyen a aquesta data
     * @param pos Posicio del torn que es vol retornar
     * @return 
     */
    public Torn getTorn(int pos){
        return (Torn)torns.get(pos);
    }
    
    /**
     * Retorna el numero de torns assignats a aquesta data
     * @return 
     */
    public int getNumTorns(){
        return torns.size();
    }
}
