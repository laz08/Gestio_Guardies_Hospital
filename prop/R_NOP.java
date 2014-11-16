
package prop;

public class R_NOP extends Restriccio{
    private String fill;
    private final int capacitat = 1;
    /**
     * La restriccio NOP "converteix" un torn a restricció per poder operar amb ell 
     * @param t Torn al que fa referència la restricció
     */
    public R_NOP(String t) {
        fill = t;
    }
    
    /**
     * Retorna el torn al que fa referència
     * @return Torn al que fa referència la restricció
     */
    public String getTorn(){
        return fill;
    }
    
    @Override
    public int getCapacitat(){
        return capacitat;
    }
    
    @Override
    public String getOp() {
        return "NOP";
    }
    
}
