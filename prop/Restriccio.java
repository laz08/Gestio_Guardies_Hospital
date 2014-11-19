/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

public abstract class Restriccio {
    
    private String t = "D"; // per defecte les restriccions seran diaries
    private int numv = 0;
    
    /**
     * Donat una explresió com string retorna la part que es troba encapsulada dins el parentesis que ve a continuació
     * Es necessari que el primer que trobi sigui un parentesi obert
     * @param s Expresió d'on s'ha d'extreure el primer parentesis
     * @return Substring que conté l'explresio encapsulada dins el primer parentesis de l'expresió
     */
    public static String cerca_tancament(String s){
        String sub = "";
        s = s.substring(1);
        int contador = 0; //parentesis que ha trobat oberts abans del primer tancat
        while(!s.substring(0, 1).equals(")") || contador>0){
            if(s.substring(0,1).equals("(")) contador++;
            else if(s.substring(0, 1).equals(")"))contador--;
            sub += s.substring(0,1);
            s = s.substring(1);
        }
        return sub;
    }
    
    /**
     * Donat una part de la expresió, en treu la operació lògica que es troba a continuació
     * Es necessari que el primer que hi hagui a l'string sigui el començament de l'operació lògica
     * @param s Expresió d'on s'ha d'extreure l'operació
     * @return Substring que conte el nom de la operacio lògica
     */
    public static String llegeix_op(String s){
        String op = "";
        while(!s.substring(0,1).equals(" ") && !s.substring(0,1).equals("(") && !s.substring(0,1).equals(")")){
            op += s.substring(0,1); 
            s = s.substring(1);
        }
        return op;
    }
    
    /**
     * Retorna un valor boolea que determina si una expresió donada és una restricció o no
     * @param s Expresió que cal evaluar
     * @return 
     */
    public static boolean esRestriccio(String s){
        boolean esR = false;
        while(s.substring(0,1).equals(" ")){ // elimina tots els espais en blanc
            s = s.substring(1);
        }
        if (s.subSequence(0,1).equals("(")){ // si troba un parentesi obert dona per suposat que es una restriccio del tipus (...)AND(...) o (...)OR(...)
            esR = true;
        }
        else if (s.length()>3){
            if (s.substring(0,3).equals("NOP") || s.substring(0,3).equals("NOT")){ // si llegeix NOT o NOP donara per suposat que es una restricció
                esR = true;
            }
        }
        // en qualsevol altre cas no es considerarà restreicció
        return esR;
    }
    
    /**
     * Retorna el nombre de vertex no fulles que formen el subarbre d'aquesta restricció
     * @return 
     */
    public int getNumVertex(){
        return numv;
    }    
    
    /**
     * modifica el nombre de vertex no fulles que formen el subarbre d'aquesta restricció
     * @param nv Nou nombre de vertex
     */
    public void setNumVertex(int nv){
        numv = nv;
    }
    
    /**
     * Assigna un valor a la variable tipus que defineix si la restricció fa referència a dies, hores o setmanes
     * @param tipus 
     */
    public void setTipus(String tipus) throws Error{
        if (tipus.equals("D") || tipus.equals("H") || tipus.equals("S")){
            t = tipus;
        }
        else{
            throw new Error("El tipus de que s'ha assignat a la restricció no es el correcte");
        }
    }
    
    /**
     * Retorna un String que determina si la restricció fa referència a dies, hores o setmanes
     * @return D, H o S
     */
    public String getTipus(){
        return t;
    }
    
    /**
     * Retorna l'operació lògica que s'aplica a aquesta restricció
     * @return Operació lògica
     */
    public abstract String getOp();
}
