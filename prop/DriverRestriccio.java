/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.Scanner;

/**
 *
 * @author Xisco
 */
public class DriverRestriccio {

    public static void main(String[] args) throws Error {
        Restriccio r = null;
        String expressio = null;
        Scanner scan;
        while (true) {
            System.out.println("---------MENU---------");
            System.out.println("1 => Insereix una expressió per la restriccio");
            System.out.println("2 => Crea l'arbre de la restriccio");
            System.out.println("3 => Consulta l'operació de la restricció");
            System.out.println("4 => Mostra l'arbre que sorgeix de la restricció");
            System.out.println("5 => consulta el primer fill de la restricció");
            System.out.println("6 => consulta el segon fill de la restricció");
            System.out.println("7 => accedeix a la primera restricció filla");
            System.out.println("8 => accedeix a la segona restricció filla");

            scan = new Scanner(System.in);

            int opcio = scan.nextInt();
            switch (opcio) {
                case 1:
                    System.out.println("\nInsereix l'expressió que expresa la restricció:");
                    scan = new Scanner(System.in);
                    expressio = scan.nextLine();
                    break;
                case 2:
                    try {
                        r = Restriccio.crea_arbre(expressio);
                    } catch (Error e) {
                        System.err.println("Hi ha hagut un error durant la creació de l'arbre\n " + e);
                    }
                    break;
                case 3:
                    System.out.println(r.getOp());
                    break;
                case 4:
                    MostraArbre(r);
                    System.out.println();
                    break;
                case 5:
                    String op = r.getOp();
                    switch (op) {
                        case "AND":
                            R_AND and = (R_AND) r;
                            Object oAnd = and.getFill1();
                            if(oAnd.getClass().equals(Torn.class)){
                                Torn f1 = (Torn) oAnd;
                                System.out.println("El primer torn va de " + f1.getData_inici().getTime() + " fins " + f1.getData_fi().getTime());
                            }
                            else{
                                Restriccio f1 = (Restriccio) oAnd;
                                System.out.println("La primera restricció es " + f1.getOp());
                            }
                            break;
                        case "OR":
                            R_OR or = (R_OR) r;
                            Object oOr = or.getFill1();
                            if(Torn.class.equals(oOr.getClass())){
                                Torn f1 = (Torn) oOr;
                                System.out.println("El primer torn va de " + f1.getData_inici().getTime() + " fins " + f1.getData_fi().getTime());
                            }
                            else{
                                Restriccio f1 = (Restriccio) oOr;
                                System.out.println("La primera restricció es " + f1.getOp());
                            }
                            break;
                        case "NOT":
                            R_NOT not = (R_NOT) r;
                            Object oNot = not.getFill();
                            if(Torn.class.equals(oNot.getClass())){
                                Torn f1 = (Torn) oNot;
                                System.out.println("El primer torn va de " + f1.getData_inici().getTime() + " fins " + f1.getData_fi().getTime());
                            }
                            else{
                                Restriccio f1 = (Restriccio) oNot;
                                System.out.println("La primera restricció es " + f1.getOp());
                            }
                            break;
                        case "NOP":
                            R_NOP nop = (R_NOP) r;
                            Torn nopt1 = nop.getTorn();
                            System.out.println("El primer torn va de " + nopt1.getData_inici().getTime() + " fins " + nopt1.getData_fi().getTime());
                            break;
                    }
                    break;
                case 6:
                    String op2 = r.getOp();
                    switch (op2) {
                        case "AND":
                            R_AND and = (R_AND) r;
                            Object oAnd = and.getFill2();
                            if(oAnd.getClass().equals(Torn.class)){
                                Torn f2 = (Torn) oAnd;
                                System.out.println("El segon torn va de " + f2.getData_inici().getTime() + " fins " + f2.getData_fi().getTime());
                            }
                            else{
                                Restriccio f2 = (Restriccio) oAnd;
                                System.out.println("La segona restricció es " + f2.getOp());
                            }
                            break;
                        case "OR":
                            R_OR or = (R_OR) r;
                            Object oOr = or.getFill2();
                            if(Torn.class.equals(oOr.getClass())){
                                Torn f2 = (Torn) oOr;
                                System.out.println("El segon torn va de " + f2.getData_inici().getTime() + " fins " + f2.getData_fi().getTime());
                            }
                            else{
                                Restriccio f2 = (Restriccio) oOr;
                                System.out.println("La segona restricció es " + f2.getOp());
                            }
                            break;
                    }
                    break;
                case 7:
                    String op5 = r.getOp();
                    switch (op5) {
                        case "AND":
                            R_AND and = (R_AND) r;
                            if(!Torn.class.equals(and.getFill1().getClass())){
                                r = (Restriccio) and.getFill1();
                            }
                            else{
                                System.err.println("el fill no es una restriccio");
                            }
                            break;
                        case "OR":
                            R_OR or = (R_OR) r;
                            if(!Torn.class.equals(or.getFill1().getClass())){
                                r = (Restriccio) or.getFill1();
                            }
                            else{
                                System.err.println("el fill no es una restriccio");
                            }
                            break;
                        case "NOT":
                            R_NOT not = (R_NOT) r;
                            if(!Torn.class.equals(not.getFill().getClass())){
                                r = (Restriccio) not.getFill();
                            }
                            else{
                                System.err.println("el fill no es una restriccio");
                            }
                            break;
                    }
                    break;
                case 8:
                    String op6 = r.getOp();
                    switch (op6) {
                        case "AND":
                            R_AND and = (R_AND) r;
                            if(!Torn.class.equals(and.getFill2().getClass())){
                                r = (Restriccio) and.getFill2();
                            }
                            else{
                                System.err.println("el fill no es una restriccio");
                            }
                            break;
                        case "OR":
                            R_OR or = (R_OR) r;
                            if(!Torn.class.equals(or.getFill2().getClass())){
                                r = (Restriccio) or.getFill2();
                            }
                            else{
                                System.err.println("el fill no es una restriccio");
                            }
                            break;
                    }
                    break;
            }

        }
    }

    private static void MostraArbre(Object o) throws Error {
        if (R_AND.class.equals(o.getClass())
                || R_OR.class.equals(o.getClass())
                || R_NOT.class.equals(o.getClass())
                || R_NOP.class.equals(o.getClass())) {
            Restriccio r = (Restriccio) o;
            String op = r.getOp();
            switch (op) {
                case "AND":
                    R_AND and = (R_AND) r;
                    System.out.print("AND ( ");
                    MostraArbre(and.getFill1());
                    System.out.print(" || ");
                    MostraArbre(and.getFill2());
                    System.out.print(" )");
                    break;
                case "OR":
                    R_OR or = (R_OR) r;
                    System.out.print("OR ( ");
                    MostraArbre(or.getFill1());
                    System.out.print(" || ");
                    MostraArbre(or.getFill2());
                    System.out.print(" )");
                    break;
                case "NOT":
                    R_NOT not = (R_NOT) r;
                    System.out.print("NOT ( ");
                    MostraArbre(not.getFill());
                    System.out.print(" )");
                    break;
                case "NOP":
                    R_NOP nop = (R_NOP) r;
                    System.out.println("NOP ( ");
                    MostraArbre(nop.getTorn());
                    System.out.print(" )");
                    break;
            }
        }  else {
            System.out.print(" T ");
        }
    }
}
