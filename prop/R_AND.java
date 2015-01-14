/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;


public class R_AND extends Restriccio{
    private Restriccio restriccio1 = null, restriccio2 = null;
    private String fill1 = null, fill2 = null;
    private final int capacitat = 2;
    /**
     * Operació lògica AND fent referència a 2 altres restriccions 
     * @param r1 Primera restricció
     * @param r2 Sengona restricció
     */
    public R_AND(Restriccio r1, Restriccio r2, String ident){
        restriccio1 = r1;
        restriccio2 = r2;
        id = "AND"+ident;
    }
    
    /**
     * Operació lògica AND fent referència a 2 torns
     * @param f1 Primer torn
     * @param f2 Segon torn
     */
    public R_AND(String f1, String f2, String ident){
        fill1 = f1;
        fill2 = f2;
        id = "AND"+ident;
    }
    
    /**
     * Retorna el primer fill de la restricció. 
     * @return Objecte que fa referencia al primer fill (restricció o torn)
     */
    public Object getFill1(){
        if(restriccio1 == null) return fill1;
        else return restriccio1;
    }
    
    /**
     * Retorna el segon fill de la restricció
     * @return Objecte que fa referencia al segon fill (restricció o torn)
     */
    public Object getFill2(){ 
        if(restriccio2 == null) return fill2;
        else return restriccio2;
    }

    
    /**
     * Retorna el tipus d'operacio.
     */
    public String getOp() {
        return "AND";
    }

    /**
     * Retorna l'identificador de la classe.
     */
    public String getId() {
        return id;
    }
    
}
