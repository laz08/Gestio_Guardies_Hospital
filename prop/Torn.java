package prop;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.GregorianCalendar;

public class Torn {
    //private int hora_inici, hora_fi;
    private GregorianCalendar data_inici, data_fi;
    private int n_min_doc;
    private int doc_assignats = 0;
    private float percent_sou;
    
    /**
     *
     * @param h_i hora d'inici del torn 
     * @param h_f hora de finalització del torn
     * @param min_d minim nombre de doctors per torn
     * @param p_sou percentatge de l'increment del sou
     */
    
    public Torn() {
    	
    }
    
    public Torn(GregorianCalendar d_i, GregorianCalendar d_f, int min_d, int p_sou){
        data_inici = d_i;
        data_fi = d_f;
        n_min_doc = min_d;
        percent_sou = p_sou;
    }
    
    /**
     * Retorna el nombre de doctors assignats a aquest torn
     * @return 
     */
    public int getDoc_assignats(){
        return doc_assignats; 
    }
    
    /**
     * Assigna un valor al nombre de doctors assignats a aquest torn
     * @param doc_assignats 
     */
    public void setDoc_assignats(int dtrs_assig){
        doc_assignats = dtrs_assig;
    }
    
    /**
     * Retorna la data d'inici del torn
     * @return 
     */
    public GregorianCalendar getData_inici() {
    	return data_inici;
    }
    
    /**
     * Retorna la data en que finalitza el torn
     * @return 
     */
    public GregorianCalendar getData_fi() {
    	return data_fi;
    }
    
    
    /**
     * Estableix una nova data d'inici per aquest torn
     * @param data_i 
     */
    public void setData_inici (GregorianCalendar data_i) {
    	data_inici = data_i;
    }


    /**
     * Estableix una nova data de finalització per aquest torn
     * @param data_f 
     */
    public void setData_fi (GregorianCalendar data_f) {
    	data_fi = data_f;
    }

    /**
     * Retorna el nombre mínim de doctors que ha de tenir aquest torn
     * @return 
     */
    public int getN_min_doc() {
        return n_min_doc;
    }

    /**
     * Estableix un nombre minim de doctors per aquest torn
     * @param n_min_doc 
     */
    public void setN_min_doc(int num_min_d) {
        n_min_doc = num_min_d;
    }

    /**
     * Retorna el percentatge que augmentarà el sou del doctors assignats a aquest torn
     * @return 
     */
    public float getPercent_sou() {
        return percent_sou;
    }

    /**
     * Assigna un nou percentatge a aquest torn
     * @param percent_sou 
     */
    public void setPercent_sou(float prcnt_sou) {
        percent_sou = prcnt_sou;
    }
    
    
}
