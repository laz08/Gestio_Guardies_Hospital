
package prop;

public class R_NOP extends Restriccio{
    String torn;
    
    /**
     * La restriccio NOP "converteix" un torn a restricció per poder operar amb ell 
     * @param t Torn al que fa referència la restricció
     */
    public R_NOP(String t) {
        torn = t;
    }
    
    /**
     * Retorna el torn al que fa referència
     * @return Torn al que fa referència la restricció
     */
    public String getTorn(){
        return torn;
    }
    
    @Override
    public String getOp() {
        return "NOP";
    }
    
}
