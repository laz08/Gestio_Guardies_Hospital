/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;


public class R_OR extends Restriccio{
    Restriccio restriccio1 = null, restriccio2 = null;
    Torn torn1 = null, torn2 = null;
    
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
    public R_OR(Torn t1, Torn t2){
        torn1 = t1;
        torn2 = t2;
    }
    
    /**
     * En el cas que faci referència a restriccions, retorna la primera
     * @return Primera restricció
     * @throws Error 
     */
    public Restriccio getRestriccio1() throws Error{
        if(restriccio1 == null) throw new Error ("Aquesta restricció OR no esta formada per altres restriccions");
        return restriccio1;
    }
    
    /**
     * En el cas que faci referència a restriccions, retorna la segona
     * @return Segona restricció
     * @throws Error 
     */
    public Restriccio getRestriccio2() throws Error{
        if(restriccio2 == null) throw new Error ("Aquesta restricció OR no esta formada per altres restriccions");
        return restriccio2;
    }
    
    /**
     * En el cas que faci referència a torns, retorna el primer
     * @return Primer torn
     * @throws Error 
     */
    public Torn getTorn1() throws Error{
        if(torn1 == null) throw new Error ("Aquesta restricció OR no esta formada per torns");
        return torn1;
    }
    
    /**
     * En el cas que faci referència a torns, retorna el segon
     * @return Segon torn
     * @throws Error 
     */
    public Torn getTorn2() throws Error{
        if(torn2 == null) throw new Error ("Aquesta restricció OR no esta formada per torns");
        return torn2;
    }
    
    @Override
    public String getOP() {
        return "OR";
    }
    
}
