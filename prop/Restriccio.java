/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

<<<<<<< HEAD
public abstract class Restriccio {
=======
import prop.Operacio_Logica.OP;

public class Restriccio {
    private OP op;
>>>>>>> 866e95ac90529616f1234859b9902c1d518682d7
    private Torn t1 = null, t2 = null;
    private Restriccio r1, r2;
    private Doctor d; 
    //////////////////////////////////////////////////////////////////////////
    // Per accedir a un torn determinat s'hauria de consultar la classe graf//
    // i anar recorreguent node a node fins trobar-ne un amb un             //
    // identificador de torn que sigui igual al que es cerca??              //
    //////////////////////////////////////////////////////////////////////////
    
    /**
<<<<<<< HEAD
=======
     * Restricció formada per una operació lògica i per un puntrer a un torn
     * @param op Operació lògica
     * @param t Torn fill
     * @throws Error 
     */
    public Restriccio(OP op, Torn t) throws Error{
        if(op == OP.NOT || op == OP.NOP){
            this.op = op;
            this.t1 = t;
        }
        else{
            throw new Error("No es pot utilitzar la operacio AND o OR a aquesta restriccio");
        }
        
    }
    
    /**
     * Restriccio composada per una operació lògica i un punter a una altra restricció
     * @param op Operació lògica
     * @param r1 Restricció filla
     * @throws Error 
     */
    private Restriccio(OP op, Restriccio r1) throws Error{
        if(op == OP.NOT || op == OP.NOP){
            this.op = op;
            this.r1 = r1;
        }
        else{
            throw new Error("No es pot utilitzar la operacio AND o OR a aquesta restriccio");
        }
    }
    
    /**
     * Restricció formada per una operació lògica i 2 punters a altres restriccions
     * @param op Operació lògica
     * @param r1 Preimera restricció filla 
     * @param r2 Segona restricció filla
     * @throws Error 
     */
    public Restriccio(OP op, Restriccio r1, Restriccio r2) throws Error{
        if(op != OP.NOT || op != OP.NOP){
            this.op = op;
            this.r1 = r1;
            this.r2 = r2;
        }
        else{
            throw new Error("No es pot utilitzar la operacio NOT o NOP a aquesta restriccio");
        }
    }
    
    /**
     * Restricció formada per una operació lògica i 2 punters a torns
     * @param op Operació lògica
     * @param t1 Primer torn fill
     * @param t2 Segon torn fill
     * @throws Error 
     */
    public Restriccio(OP op, Torn t1, Torn t2) throws Error{
        if(op != OP.NOT || op != OP.NOP){
            this.op = op;
            this.t1 = t1;
            this.t2 = t2;
        }
        else{
            throw new Error("No es pot utilitzar la operacio NOT o NOP a aquesta restriccio");
        }
    }
    
// En principi no es necessita una comprovació de si es compleix o no una restricció ja que aquestes
// formen part del graf i per tant (si es tradueix be de l'arbre al graf) sempre que es trobi una solució
// s'haurien de complir totes les restriccions
// De totes paneres, per si acas no elimin el codi, sols el coment
//
//    /**
//     * Funció que retorna un boolea que permet determinar si s'ha complit aquesta restricció o no
//     * @return boolea que determina si es compleix la restricció o no
//     */
//    public boolean compleixRestriccio(){
//        boolean compleix = false;
//        if(t1 == null && t2 == null && r2!=null){ //restriccio composada per 2 restriccions
//            switch(op){
//                case AND:
//                    compleix = r1.compleixRestriccio() && r2.compleixRestriccio();
//                break;
//                
//                case OR:
//                    compleix = r1.compleixRestriccio() || r2.compleixRestriccio();
//                break;
//                    
//                case XOR:
//                    compleix = (r1.compleixRestriccio() && !r2.compleixRestriccio())
//                                ||
//                               (r2.compleixRestriccio() && !r1.compleixRestriccio());
//                break;
//                    
//                case NAND:
//                    compleix = !(r1.compleixRestriccio() && r2.compleixRestriccio());
//                break;
//            }
//        }
//        else if(r1 != null && r2 == null){ // restriccio composada per 1 resticcio
//            if(op == OP.NOP) compleix = r1.compleixRestriccio(); 
//            else compleix = !r1.compleixRestriccio();
//        }
//        else if (t1 != null && t2 == null){ // restriccio composada per 1 Torn
//            if(op == OP.NOP) compleix = t1.assignat(); //revisar com es sabra si un torn esta disponible o no
//            else compleix = !t1.assignat();
//        }
//        else if (t1 != null && t2 != null){ // restriccio composada per 2 torns
//            switch(op){
//                case AND:
//                    compleix = t1.esDisponible() && t2.esDisponible();
//                break;
//                
//                case OR:
//                    compleix = t1.esDisponible() || t2.esDisponible();
//                break;
//                    
//                case XOR:
//                    compleix = (t1.esDisponible() && !t2.esDisponible())
//                                ||
//                               (t2.esDisponible() && !t1.esDisponible());
//                break;
//                    
//                case NAND:
//                    compleix = !(t1.esDisponible() && t2.esDisponible());
//                break;
//            }
//        }
//        
//        return compleix;
//    }
    
    /**
>>>>>>> 866e95ac90529616f1234859b9902c1d518682d7
     * Donat una explresió lògica, es crea un arbre que la representa i en retorna l'arrel
     * @param s String amb la explresió lògica que determina la restricció completa
     * @return Restricció arrel de l'arbre
     * @throws Error 
     */
    public static Restriccio crea_arbre(String s) throws Error{
       Restriccio r = null; // arrel de l'arbre de restriccions
       String c = s.substring(0,1);
       s = s.substring(1);
       while (c.equals(" ")){
          c = s.substring(0,1); // agafam el primer cara<cter de s
          s = s.substring(1); // treim el primer caracter de s
       }
       if (c.equals("(")) {
           String sub_r1 = cerca_tancament(s);
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
               Restriccio r1 = crea_arbre(sub_r1);
               Restriccio r2 = crea_arbre(sub_r2);
<<<<<<< HEAD
               switch (operacio){
                   case "AND":
                      r = new R_AND(r1,r2); 
                      break;
                   case "OR":
                       r = new R_OR(r1,r2);
=======
               OP op;
               switch (operacio){
                   case "AND":
                      op = OP.AND; 
                      break;
                   case "NAND":
                       op = OP.NAND;
                       break;
                   case "OR":
                       op = OP.OR;
                       break;
                   case "XOR":
                       op = OP.XOR;
>>>>>>> 866e95ac90529616f1234859b9902c1d518682d7
                       break;
                   default:
                       throw new Error("El nom de l'operació logica no es correcte");
               }
<<<<<<< HEAD
           }
           else if(!esRestriccio(sub_r1) && !esRestriccio(sub_r2)){
               Torn t1 = Cjt_calendaris.cerca_torn(sub_r1); //retorna el torn que te per identificador sub_r1
               Torn t2 = Cjt_calendaris.cerca_torn(sub_r2); //retorna el torn que te per identificador sub_r2
               switch (operacio){
                   case "AND":
                      r = new R_AND(t1,t2);
                      break;
                   case "OR":
                       r = new R_OR(t1,t2);
=======
               r = new Restriccio(op,r1,r2);
           }
           else if(!esRestriccio(sub_r1) && !esRestriccio(sub_r2)){
               Torn t1 = consulta_torn(sub_r1); //retorna el torn que te per identificador sub_r1
               Torn t2 = consulta_torn(sub_r2); //retorna el torn que te per identificador sub_r2
               OP op;
               switch (operacio){
                   case "AND":
                      op = OP.AND; 
                      break;
                   case "NAND":
                       op = OP.NAND;
                       break;
                   case "OR":
                       op = OP.OR;
                       break;
                   case "XOR":
                       op = OP.XOR;
>>>>>>> 866e95ac90529616f1234859b9902c1d518682d7
                       break;
                   default:
                       throw new Error("El nom de l'operació logica no es correcte");
               }
<<<<<<< HEAD
=======
               r = new Restriccio(op,t1,t2);
>>>>>>> 866e95ac90529616f1234859b9902c1d518682d7
           }
           else{
               throw new Error("Error a la deteccio dels parametres de les restriccions");
           }
       }
       else if( c.equals("N")){ //es NOT o NOP
           s = c+s; // Afagim el caracter a l'inici de la cadena per treure l'operacio sencera
           String operacio = llegeix_op(s);
           s = s.substring(s.length());
           c = s.substring(0,1);
           s = s.substring(1);
<<<<<<< HEAD
           String sub_r1 = cerca_tancament(s);
           if (esRestriccio(sub_r1)){
               Restriccio r1 = crea_arbre(sub_r1);
               if (!operacio.equals("NOT")) throw new Error ("L'operacio lògica no es pot aplicar sobre una restricció");
               r = new R_NOT(r1);
           }
           else{
               Torn t1 = Cjt_calendaris.cerca_torn(sub_r1);
               switch (operacio){
                   case "NOT":
                      r = new R_NOT(t1);
                      break;
                   case "NOP":
                       r = new R_NOP(t1);
=======
           OP op;
           switch (operacio){
                   case "AND":
                      op = OP.AND; 
                      break;
                   case "NAND":
                       op = OP.NAND;
                       break;
                   case "OR":
                       op = OP.OR;
                       break;
                   case "XOR":
                       op = OP.XOR;
>>>>>>> 866e95ac90529616f1234859b9902c1d518682d7
                       break;
                   default:
                       throw new Error("El nom de l'operació logica no es correcte");
               }
<<<<<<< HEAD
=======
           String sub_r1 = cerca_tancament(s);
           if (esRestriccio(sub_r1)){
               Restriccio r1 = crea_arbre(sub_r1);
               r = new Restriccio(op, r1);
           }
           else{
               Torn t1 = consulta_torn(sub_r1);
               r = new Restriccio(op, t1);
>>>>>>> 866e95ac90529616f1234859b9902c1d518682d7
           }
       }
       else {
           throw new Error("No s'ha detectat la restriccio correctament");
       }
       return r;
    }
    
    /**
     * Donat una explresió com string retorna la part que es troba encapsulada dins el parentesis que ve a continuació
     * @param s Expresió d'on s'ha d'extreure el primer parentesis
     * @return Substring que conté l'explresio encapsulada dins el primer parentesis de l'expresió
     */
    private static String cerca_tancament(String s){
        String sub = null;
        while(!s.substring(0, 1).equals(")")){
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
        String op = null;
        while(!s.substring(0,1).equals(" ") || !s.substring(0,1).equals("(") || !s.substring(0,1).equals(")")){
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
        else if (s.substring(0,3).equals("NOP") || s.substring(0,3).equals("NOT")){ // si llegeix NOT o NOP donara per suposat que es una restricció
            esR = true;
        }
        // en qualsevol altre cas no es considerarà restreicció
        return esR;
    }
    
    /**
     * Retorna la primera restricció en cas de que la actual estigui composta per una o més restriccions
     * @return Primera restricció filla
     */
<<<<<<< HEAD
=======
    public Restriccio getRestriccio1(){
        return r1;
    }
    
    /**
     * Retorna la segona restricció en cas de que la actual estigui composta per més d'una restricció
     * @return Segona restricció filla
     */
    public Restriccio getRestriccio2(){
        return r2;
    }
    
    /**
     * Retorna el primer torn en cas de que la restricció actual estigui composta per un o més torns 
     * @return Primer torn fill
     */
    public Torn getTorn1(){
        return t1;
    }
    
    /**
     * Retorna el segon torn en cas de que la restricció actual estigui composta més d'un torn
     * @return Segon torn fill
     */
    public Torn getTorn2(){
        return t2;
    }
>>>>>>> 866e95ac90529616f1234859b9902c1d518682d7
    
    /**
     * Retorna l'operació lògica que s'aplica a aquesta restricció
     * @return Operació lògica
     */
<<<<<<< HEAD
    public abstract String getOp();
=======
    public OP getOp(){
        return op;
    }
>>>>>>> 866e95ac90529616f1234859b9902c1d518682d7
}
