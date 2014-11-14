/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;


public class R_OR extends Restriccio{
    private Restriccio restriccio1 = null, restriccio2 = null;
    private String torn1 = null, torn2 = null;
    
    /**
     * Operació lògica OR que fa referència a 2 restriccions
     * @param r1 Primera restricció
     * @param r2 Segona restricció
     */
    public R_OR(Restriccio r1, Restriccio r2){
        restriccio1 = r1;
        restriccio2 = r2;
    }
    
    /**
     * Operació lògica OR que fa referència a 2 torns
     * @param t1 Primer torn
     * @param t2 Segon torn
     */
    public R_OR(String t1, String t2){
        torn1 = t1;
        torn2 = t2;
    }
    
    /**
     * Retorna el primer fill de la restricció. 
     * @return Objecte que fa referencia al primer fill (restricció o torn)
     */
    public Object getFill1(){
        if(restriccio1 == null) return torn1;
        else return restriccio1;
    }
    
    /**
     * Retorna el segon fill de la restricció
     * @return Objecte que fa referencia al segon fill (restricció o torn)
     */
    public Object getFill2(){ 
        if(restriccio2 == null) return torn2;
        else return restriccio2;
    }
    
    @Override
    public String getOp() {
        return "OR";
    }
    
}
