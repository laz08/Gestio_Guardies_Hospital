/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;


public class R_NOT extends Restriccio{
    private Restriccio restriccio = null;
    private String fill = null;
    private final int capacitat = 0;
    /**
     * Operació lògica NOT fent referència a una restricció
     * @param r Restricció
     */
    public R_NOT(Restriccio r){
        restriccio = r;
    }
    
    /**
     * Operació lògica NOT fent referència a un torn
     * @param t Torn
     */
    public R_NOT(String t){
        fill = t;
    }
    
    /**
     * Retorna el fill de la restricció. 
     * @return Objecte que fa referencia al primer fill (restricció o torn)
     */
    public Object getFill(){
        if(restriccio == null) return fill;
        else return restriccio;
    }

    @Override
    public String getOp() {
        return "NOT";
    }
}
