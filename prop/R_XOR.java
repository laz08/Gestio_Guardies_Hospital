/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;


public class R_XOR extends Restriccio{
    private Restriccio restriccio1 = null, restriccio2 = null;
    private String fill1 = null, fill2 = null;
    private final int capacitat = 1;
    /**
     * Operació lògica OR que fa referència a 2 restriccions
     * @param r1 Primera restricció
     * @param r2 Segona restricció
     */
    public R_XOR(Restriccio r1, Restriccio r2){
        restriccio1 = r1;
        restriccio2 = r2;
    }
    
    /**
     * Operació lògica OR que fa referència a 2 fills
     * @param f1 Primer fill
     * @param f2 Segon fill
     */
    public R_XOR(String f1, String f2){
        fill1 = f1;
        fill2 = f2;
    }
    
    /**
     * Retorna el primer fill de la restricció. 
     * @return Objecte que fa referencia al primer fill (restricció o fill)
     */
    public Object getFill1(){
        if(restriccio1 == null) return fill1;
        else return restriccio1;
    }
    
    /**
     * Retorna el segon fill de la restricció
     * @return Objecte que fa referencia al segon fill (restricció o fill)
     */
    public Object getFill2(){ 
        if(restriccio2 == null) return fill2;
        else return restriccio2;
    }
    
    @Override
    public String getOp() {
        return "XOR";
    }
    
}
