/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;

public class CtrlRestriccio {

    private static ArrayList<Restriccio> restriccions = new ArrayList<Restriccio>();

    public static void nova_res(String expressio) throws Error {
        Plantilla plt = CtrlPlantilla.getPlantillaActual();
        Calendari cldr = CtrlCalendari.consultar_calendari(plt.getNomPlantilla());
        expressio = transforma_expressio(expressio, cldr);
        Restriccio r = crea_arbre(expressio);
        restriccions.add(r);
    }

    public static void elimina_res(Restriccio r) throws Error {
        int pos = consulta_pos(r);
        if (pos != -1) {
            restriccions.remove(pos);
        } else {
            throw new Error("No existeix la restricció que es vol eliminar");
        }
    }

    public static ArrayList<Restriccio> consulta_llista_res() {
        return restriccions;
    }

    public static Restriccio consulta_res(int i) {
        return restriccions.get(i);
    }

    /**
     * Donat una explresió lògica, es crea un arbre que la representa i en
     * retorna l'arrel
     *
     * @param s String amb la explresió lògica que determina la restricció
     * completa
     * @return Restricció arrel de l'arbre
     * @throws Error
     */
    private static Restriccio crea_arbre(String s) throws Error {
        String tipus = s.substring(0, 1); //agafam el tipus de restricció
        s = s.substring(1);

        Restriccio r = null; // arrel de l'arbre de restriccions
        String c = s.substring(0, 1);
        s = s.substring(1);
        while (c.equals(" ")) {
            c = s.substring(0, 1); // agafam el primer cara<cter de s
            s = s.substring(1); // treim el primer caracter de s
        }
        if (c.equals("(")) {
            String sub_r1 = Restriccio.cerca_tancament(c + s);;
            s = s.substring(sub_r1.length()); // treim la part de sub_r1 de s
            c = s.substring(0, 1); // agafam el primer caracter de s
            s = s.substring(1); // treim el primer caracter de s
            String operacio = Restriccio.llegeix_op(s);
            s = s.substring(operacio.length()); // idem anterior per operacio 
            c = s.substring(0, 1);
            s = s.substring(1);
            while (c.equals(" ")) {
                c = s.substring(0, 1);
                s = s.substring(1);
            }
            if (!c.equals("(")) {
                throw new Error("Error a l'estructuració de les restriccions");
            }
            String sub_r2 = Restriccio.cerca_tancament(c + s);
            if (Restriccio.esRestriccio(sub_r1) && Restriccio.esRestriccio(sub_r2)) {
                Restriccio r1 = crea_arbre(tipus + sub_r1);
                Restriccio r2 = crea_arbre(tipus + sub_r2);
                switch (operacio) {
                    case "AND":
                        r = new R_AND(r1, r2);
                        break;
                    case "XOR":
                        r = new R_XOR(r1, r2);
                        break;
                    default:
                        throw new Error("El nom de l'operació logica no es correcte");
                }
                r.setNumVertex(r1.getNumVertex());
                r.setNumVertex(r2.getNumVertex());
                r.setNumVertex(r.getNumVertex() + 1);
            } else if (!Restriccio.esRestriccio(sub_r1) && !Restriccio.esRestriccio(sub_r2)) {

                switch (operacio) {
                    case "AND":
                        r = new R_AND(sub_r1, sub_r2);
                        break;
                    case "XOR":
                        r = new R_XOR(sub_r1, sub_r2);
                        //r = new R_XOR(t1,t2);
                        break;
                    default:
                        throw new Error("El nom de l'operació logica no es correcte");
                }
                r.setNumVertex(r.getNumVertex()); // si els fills no son restriccions i en te 2, en nombre de vertex del seu subarbre serà 3
            } else {
                throw new Error("Error a la deteccio dels parametres de les restriccions");
            }
        } else if (c.equals("N")) { //es NOT o NOP
            s = c + s; // Afagim el caracter a l'inici de la cadena per treure l'operacio sencera
            String operacio = Restriccio.llegeix_op(s);
            s = s.substring(operacio.length());
            c = s.substring(0, 1);
            s = s.substring(1);
            String sub_r1 = Restriccio.cerca_tancament(c + s);
            if (Restriccio.esRestriccio(sub_r1)) {
                Restriccio r1 = crea_arbre(tipus + sub_r1);
                if (!operacio.equals("NOT")) {
                    throw new Error("L'operacio lògica no es pot aplicar sobre una restricció");
                }
                r = new R_NOT(r1);
                r.setNumVertex(r1.getNumVertex());
                r.setNumVertex(r.getNumVertex() + 1);
            } else {

                switch (operacio) {
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
                r.setNumVertex(r.getNumVertex() + 1);
            }
        } else {
            throw new Error("No s'ha detectat la restriccio correctament");
        }

        r.setTipus(tipus);
        return r;
    }

    public static String mostra_arbre(Object o) {
        String sortida = "";
        if (R_AND.class.equals(o.getClass())
                || R_XOR.class.equals(o.getClass())
                || R_NOT.class.equals(o.getClass())
                || R_NOP.class.equals(o.getClass())) {
            Restriccio r = (Restriccio) o;
            String op = r.getOp();
            switch (op) {
                case "AND":
                    R_AND and = (R_AND) r;
                    sortida += "AND ( ";
                    sortida += mostra_arbre(and.getFill1());
                    sortida += " || ";
                    sortida += mostra_arbre(and.getFill2());
                    sortida += " )";
                    break;
                case "XOR":
                    R_XOR xor = (R_XOR) r;
                    sortida += "XOR ( ";
                    sortida += mostra_arbre(xor.getFill1());
                    sortida += " || ";
                    sortida += mostra_arbre(xor.getFill2());
                    sortida += " )";
                    break;
                case "NOT":
                    R_NOT not = (R_NOT) r;
                    sortida += "NOT ( ";
                    sortida += mostra_arbre(not.getFill());
                    sortida += " )";
                    break;
                case "NOP":
                    R_NOP nop = (R_NOP) r;
                    sortida += "NOP ( ";
                    sortida += mostra_arbre(nop.getTorn());
                    sortida += " )";
                    break;
            }
        } else {
            String obj = (String) o;
            sortida += " " + obj + " ";
        }
        return sortida;
    }

    public static int consulta_pos(Restriccio r) {
        int trobat = -1;
        int i = 0;
        Restriccio res;
        while (trobat == -1 && i < restriccions.size()) {
            res = restriccions.get(i);
            if (res.equals(r)) {
                trobat = i;
            }
            i++;
        }
        return trobat;
    }

    /**
     * Retorna un el primer fill de la restricció intoduida com a Objecte que
     * sera Restriccio o String
     *
     * @param r Restricció de la que es vol obtenir el fill
     * @return
     */
    public static Object selecciona_Fill1(Restriccio r) {
        Object fill = null;
        String op = r.getOp();
        switch (op) {
            case "AND":
                R_AND and = (R_AND) r;
                fill = and.getFill1();
                break;
            case "XOR":
                R_XOR xor = (R_XOR) r;
                fill = xor.getFill1();
                break;
            case "NOT":
                R_NOT not = (R_NOT) r;
                fill = not.getFill();
                break;
            case "NOP":
                R_NOP nop = (R_NOP) r;
                fill = nop.getTorn();
                break;
        }
        return fill;
    }

    public static Object selecciona_Fill2(Restriccio r) {
        Object fill = null;
        String op2 = r.getOp();
        switch (op2) {
            case "AND":
                R_AND and = (R_AND) r;
                fill = and.getFill2();
                break;
            case "XOR":
                R_XOR xor = (R_XOR) r;
                fill = xor.getFill2();
                break;
        }
        return fill;
    }

    /**
     * Donada una expressió que defineix una restricció, canvia els valors
     * numèrics que fan referència als torns per l'identificador de torn
     * correcte
     *
     * @param e expressió amb identificador numèric de torn
     * @return
     */
    private static String transforma_expressio(String e, Calendari cldr) throws Error {
        String t = ""; 
        if (e.length() > 0) {
            String c = e.substring(0, 1);
            String s = e.substring(1);
            while ((c.equals("0") || c.equals("1") || c.equals("2") || c.equals("3")
                    || c.equals("4") || c.equals("5") || c.equals("6") || c.equals("7")
                    || c.equals("8") || c.equals("9")) && s.length() > 0) {
                t += c;
                c = s.substring(0, 1);
                s = s.substring(1);
            }
            if(!t.equals("")){
               Torn torn = troba_torn(Integer.parseInt(t), cldr);
               t = torn.toString();
            }
            t += c;
            t += transforma_expressio(s, cldr);
        }
        return t;
    }
    
    private static Torn troba_torn(int pos, Calendari c) throws Error{
        Torn torn = null;
        ArrayList<Torn> torns = c.getTorns();
        if (pos >= torns.size()) throw new Error("No existeix el torn inserit"); 
        return torns.get(pos);
    }
}
