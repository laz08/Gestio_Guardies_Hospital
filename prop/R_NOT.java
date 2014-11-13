/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;


public class R_NOT extends Restriccio{
    Restriccio restriccio = null;
    Torn torn = null;
    
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
    public R_NOT(Torn t){
        torn = t;
    }
    
    /**
     * En el cas que es faci referència a una restricció, la retorna
     * @return Restricció a la que es fa referència
     * @throws Error 
     */
    public Restriccio getRestriccio() throws Error{
        if (restriccio == null) throw new Error("Aquesta restricció NOT no fa referència a una altra restricció");
        return restriccio;
    }
    
    /**
     * En el cas que es daci referència a un torn, el retorna
     * @return Torn al que fa referència
     * @throws Error 
     */
    public Torn getTorn() throws Error{
        if (torn == null) throw new Error("Aquesta restriccio NOT no fa referència a un torn");
        return torn;
    }

    @Override
    public String getOP() {
        return "NOT";
    }
    
}
