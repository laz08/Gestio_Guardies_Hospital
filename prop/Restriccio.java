/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

public abstract class Restriccio {
    
    private String t;
    private int numv = 0;
    
    /**
     * Donat una explresió lògica, es crea un arbre que la representa i en retorna l'arrel
     * @param s String amb la explresió lògica que determina la restricció completa
     * @return Restricció arrel de l'arbre
     * @throws Error 
     */
    public static Restriccio crea_arbre(String s) throws Error{
        String tipus = s.substring(0,1); //agafam el tipus de restricció
        s = s.substring(1);
       
       Restriccio r = null; // arrel de l'arbre de restriccions
       String c = s.substring(0,1);
       s = s.substring(1);
       while (c.equals(" ")){
          c = s.substring(0,1); // agafam el primer cara<cter de s
          s = s.substring(1); // treim el primer caracter de s
       }
       if (c.equals("(")) {
           String sub_r1 = cerca_tancament(s);;
           s = s.substring(sub_r1.length()); // treim la part de sub_r1 de s
           c = s.substring(0,1); // agafam el primer caracter de s
           s = s.substring(1); // treim el primer caracter de s
           String operacio = llegeix_op(s);
           s = s.substring(operacio.length()); // idem anterior per operacio 
           c = s.substring(0,1); 
           s = s.substring(1);
           while (c.equals(" ")){
               c = s.substring(0,1);
               s = s.substring(1);
           }
           if (!c.equals("(")) throw new Error("Error a l'estructuració de les restriccions");
           String sub_r2 = cerca_tancament(s);
           if (esRestriccio(sub_r1) && esRestriccio(sub_r2)){
               Restriccio r1 = crea_arbre(tipus+sub_r1);
               Restriccio r2 = crea_arbre(tipus+sub_r2);
               switch (operacio){
                   case "AND":
                      r = new R_AND(r1,r2); 
                      break;
                   case "XOR":
                       r = new R_XOR(r1,r2);
                       break;
                   default:
                       throw new Error("El nom de l'operació logica no es correcte");
               }
               r.numv += r1.numv;
               r.numv += r2.numv+1;
           }
           else if(!esRestriccio(sub_r1) && !esRestriccio(sub_r2)){
               
               switch (operacio){
                   case "AND":
                      //r = new R_AND(t1,t2);
                       r = new R_AND(sub_r1, sub_r2); 
                      break;
                   case "XOR":
                       r = new R_XOR(sub_r1, sub_r2);
                       //r = new R_XOR(t1,t2);
                       break;
                   default:
                       throw new Error("El nom de l'operació logica no es correcte");
               }
               r.numv ++; // si els fills no son restriccions i en te 2, en nombre de vertex del seu subarbre serà 3
           }
           else{
               throw new Error("Error a la deteccio dels parametres de les restriccions");
           }
       }
       else if( c.equals("N")){ //es NOT o NOP
           s = c+s; // Afagim el caracter a l'inici de la cadena per treure l'operacio sencera
           String operacio = llegeix_op(s);
           s = s.substring(operacio.length());
           c = s.substring(0,1);
           s = s.substring(1);
           String sub_r1 = cerca_tancament(s);
           if (esRestriccio(sub_r1)){
               Restriccio r1 = crea_arbre(tipus+sub_r1);
               if (!operacio.equals("NOT")) throw new Error ("L'operacio lògica no es pot aplicar sobre una restricció");
               r = new R_NOT(r1);
               r.numv += r1.numv+1;
           }
           else{
               
               switch (operacio){
                   case "NOT":
                      r = new R_NOT(sub_r1); 
                      //r = new R_NOT(t1);
                      break;
                   case "NOP":
                       //r = new R_NOP(t1);
                       r = new R_NOP(sub_r1); 
                       break;
                   default:
                       throw new Error("El nom de l'operació logica no es correcte");
               }
               r.numv ++;
           }
       }
       else {
           throw new Error("No s'ha detectat la restriccio correctament");
       }
       
       r.setTipus(tipus);
       return r;
    }
    
    /**
     * Donat una explresió com string retorna la part que es troba encapsulada dins el parentesis que ve a continuació
     * @param s Expresió d'on s'ha d'extreure el primer parentesis
     * @return Substring que conté l'explresio encapsulada dins el primer parentesis de l'expresió
     */
    private static String cerca_tancament(String s){
        String sub = "";
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
     * @param s Expresió d'on s'ha d'extreure l'operació
     * @return Substring que conte el nom de la operacio lògica
     */
    private static String llegeix_op(String s){
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
    private static boolean esRestriccio(String s){
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
    
    public int getNumVertex(){
        return numv;
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
     * Retorna una constant que conté la capacitat que hauria de tenir l'aresta que apunta a aquesta restricció dins el graf
     * @return 
     */
    public abstract int getCapacitat();
    
    /**
     * Retorna l'operació lògica que s'aplica a aquesta restricció
     * @return Operació lògica
     */
    public abstract String getOp();
}
