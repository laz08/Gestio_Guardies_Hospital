/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;

public class CtrlRestriccio {

    private static ArrayList<Restriccio> restriccions = new ArrayList<Restriccio>();
    private static ArrayList<String> eRestriccions = new ArrayList<String>();
    private static int idcount = 0;

    public static void nova_res(String expressio) {
        Restriccio r = crea_arbre(expressio);
        restriccions.add(r);
        eRestriccions.add(expressio);
    }

    public static void elimina_res(Restriccio r) {
        int pos = consulta_pos(r);
        if (pos != -1) {
            restriccions.remove(pos);
            eRestriccions.remove(pos);
        }
    }
    
    public static void elimina_res(int pos){
        if(pos<restriccions.size()){
            restriccions.remove(pos);
            eRestriccions.remove(pos);
        }
    }

    public static ArrayList<Restriccio> consulta_llista_res() {
        return restriccions;
    }

    public static Restriccio consulta_res(int i) {
        return restriccions.get(i);
    }
    
    public static String consulta_explesio_res(int i){
        return eRestriccions.get(i);
    }

    /**
     * Donat una explresió lògica, es crea un arbre que la representa i en
     * retorna l'arrel
     *
     * @param s String amb la explresió lògica que determina la restricció
     * completa
     * @return Restricció arrel de l'arbre. Null si hi ha hagut un error
     */
    private static Restriccio crea_arbre(String s) {
        int id = idcount++; // guardam l'id que tocaria a questa restriccio i incrementam el contador per el proxim

        String tipus = s.substring(0, 1); //agafam el tipus de restricció

        /* si s'introdueix un tipus correcte, es caclula l'arbre per la resta
         * però si el tipus no es correcte, es marca com a null abans de continuar amb l'arbre
         */
        if (tipus.equals("D") || tipus.equals("H")) {
            s = s.substring(1);
        } else {
            tipus = null;
        }
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
            String sub_r2 = Restriccio.cerca_tancament(c + s);
            if (Restriccio.esRestriccio(sub_r1) && Restriccio.esRestriccio(sub_r2)) {
                Restriccio r1 = crea_arbre(tipus + sub_r1);
                Restriccio r2 = crea_arbre(tipus + sub_r2);
                switch (operacio) {
                    case "AND":
                        r = new R_AND(r1, r2, "R" + id);
                        break;
                    case "XOR":
                        r = new R_XOR(r1, r2, "R" + id);
                        break;
                }
                r.setNumVertex(r1.getNumVertex());
                r.setNumVertex(r2.getNumVertex());
                r.setNumVertex(r.getNumVertex() + 1);
            } else if (!Restriccio.esRestriccio(sub_r1) && !Restriccio.esRestriccio(sub_r2)) {

                switch (operacio) {
                    case "AND":
                        r = new R_AND(sub_r1, sub_r2, "R" + id);
                        break;
                    case "XOR":
                        r = new R_XOR(sub_r1, sub_r2, "R" + id);
                        //r = new R_XOR(t1,t2);
                        break;
                }
                r.setNumVertex(r.getNumVertex()); // si els fills no son restriccions i en te 2, en nombre de vertex del seu subarbre serà 3
            }
        } else if (c.equals("N")) { //es NOT o NOP
            s = c + s; // Afagim el caracter a l'inici de la cadena per treure l'operacio sencera
            String operacio = Restriccio.llegeix_op(s);
            s = s.substring(operacio.length());
            c = s.substring(0, 1);
            s = s.substring(1);
            String sub_r1 = Restriccio.cerca_tancament(c + s);
            r = new R_NOP(sub_r1, "R" + id);
            r.setNumVertex(r.getNumVertex() + 1);

        }
        r.setTipus(tipus);
        return r;
    }

    public static String mostra_arbre(Object o) {
        String sortida = "";
        if (R_AND.class.equals(o.getClass())
                || R_XOR.class.equals(o.getClass())
                || R_NOP.class.equals(o.getClass())) {
            Restriccio r = (Restriccio) o;
            String op = r.getOp();
            switch (op) {
                case "AND":
                    R_AND and = (R_AND) r;
                    //sortida += "AND ( ";
                    sortida += r.getId() + "( ";
                    sortida += mostra_arbre(and.getFill1());
                    sortida += " || ";
                    sortida += mostra_arbre(and.getFill2());
                    sortida += " )";
                    break;
                case "XOR":
                    R_XOR xor = (R_XOR) r;
                    //sortida += "XOR ( ";
                    sortida += r.getId() + "( ";
                    sortida += mostra_arbre(xor.getFill1());
                    sortida += " || ";
                    sortida += mostra_arbre(xor.getFill2());
                    sortida += " )";
                    break;
                case "NOP":
                    R_NOP nop = (R_NOP) r;
                    //sortida += "NOP ( ";
                    sortida += r.getId() + "( ";
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
    
    public static int consulta_pos(String expressio){
        int trobat = -1;
        int i = 0;
        String eRes;
        while (trobat == -1 && i < eRestriccions.size()) {
            eRes = eRestriccions.get(i);
            if (eRes.equals(expressio)) {
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

    public static void guardar(File f) {
        String content = "";
        for (String r : eRestriccions) {
            content += r + "/";
        }
        CtrlPersistencia.guardar(content, f);
    }
    
    public static String carrega(File f) throws FileNotFoundException{
        String contingut = CtrlPersistencia.carregar(f);
        return contingut;
    }
    
}
