package prop;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Torn {
    private GregorianCalendar data_inici, data_fi;
    private int n_min_doc;
    private ArrayList<String> doc_assignats;
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
    
    @SuppressWarnings("unchecked")
	public Torn(GregorianCalendar d_i, GregorianCalendar d_f, int min_d, int p_sou, ArrayList<String> lldoc ){
        if(d_i.compareTo(d_f)==1) System.out.println("Aquest torn no s'ha creat perque la data final ha de ser posterior a la data inci del torn\n");
        else {
        	data_inici = d_i;
        	data_fi = d_f;
        	n_min_doc = min_d;
        	percent_sou = p_sou;
        	doc_assignats = (ArrayList<String>) lldoc.clone();
        }
    }
    
    public int getHora_inici() {
    	return data_inici.get(Calendar.HOUR_OF_DAY);
    }
    
    public int getHora_fi() {
    	return data_fi.get(Calendar.HOUR_OF_DAY);
    }
    
    public String getDia_setmana_inici() {
    	int dia = data_inici.get(Calendar.DAY_OF_WEEK);
    	if(dia==1) return "Diumenge";
    	else if(dia==2) return "Dilluns";
    	else if(dia==3) return "Dimarts";
    	else if(dia==4) return "Dimecres";
    	else if(dia==5) return "Dijous";
    	else if(dia==6) return "Divendres";
    	else return "Dissabte";
    }
    
    public String getDia_setmana_fi() {
    	int dia = data_fi.get(Calendar.DAY_OF_WEEK);
    	if(dia==1) return "Diumenge";
    	else if(dia==2) return "Dilluns";
    	else if(dia==3) return "Dimarts";
    	else if(dia==4) return "Dimecres";
    	else if(dia==5) return "Dijous";
    	else if(dia==6) return "Divendres";
    	else return "Dissabte";
    }
    
    public GregorianCalendar getData_inici() {
    	return data_inici;
    }
    
    public void setData_inici (GregorianCalendar data_i) {
    	if(data_i.compareTo(data_fi)==1) System.out.println("No es pot modificar amb aquesta data, ja que la data inici ha de ser anterior a la data fi\n");
    	else data_inici = data_i;
    }
    
    public GregorianCalendar getData_fi() {
    	return data_fi;
    }
    
    public void setData_fi (GregorianCalendar data_f) {
    	if(data_inici.compareTo(data_f)==1) System.out.println("No es pot modificar amb aquesta data, ja que la data fi ha de ser posterior a la data inci\n");
    	else data_fi = data_f;
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
     * Retorna el nombre de doctors assignats a aquest torn
     * @return 
     */
    public ArrayList<String> getDoc_assignats(){
        return doc_assignats; 
    }
    
    /**
     * Assigna un valor al nombre de doctors assignats a aquest torn
     * @param doc_assignats 
     */
    @SuppressWarnings("unchecked")
	public void setDoc_assignats(ArrayList<String> dtrs_assig){
        doc_assignats = (ArrayList<String>)dtrs_assig.clone();
    }
    
    /**
     * Retorna la data d'inici del torn
     * @return 
     */
    
    /**
     * Retorna la data en que finalitza el torn
     * @return 
     */
    
    
    /**
     * Estableix una nova data d'inici per aquest torn
     * @param data_i 
     */
    


    /**
     * Estableix una nova data de finalització per aquest torn
     * @param data_f 
     */



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
