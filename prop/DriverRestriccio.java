/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.Scanner;

public class DriverRestriccio {

    public static void main(String[] args) throws Error {
        Restriccio r = null;
        String expressio = null;
        Scanner scan;
        boolean sortir = false;
        while (!sortir) {
            System.out.println("---------MENU---------");
            System.out.println("0 => Sortir");
            System.out.println("1 => crea_arbre(String expresio)");
            System.out.println("2 => getOp()");
            System.out.println("3 => Mostra l'arbre que sorgeix de la restricció");
            System.out.println("4 => getFill1()");
            System.out.println("5 => getFill2()");
            System.out.println("6 => accedeix al fill1 (recorregut dins l'arbre)");
            System.out.println("7 => accedeix al fill2 (recorregut dins l'arbre)");
            System.out.println("8 => getTipus()");
            System.out.println("9 => getNumVertex()");
            scan = new Scanner(System.in);

            int opcio = scan.nextInt();
            switch (opcio) {
                case 0:
                    sortir = true;
                    break;
                case 1:
                    expressio = scan.nextLine();
                    try {
                        r = Restriccio.crea_arbre(expressio);
                    } catch (Error e) {
                        System.err.println("Hi ha hagut un error durant la creació de l'arbre\n " + e);
                    }
                    break;
                case 2:
                    System.out.print("L'operació lògica de la restricció actual és: ");
                    System.out.println(r.getOp());
                    break;
                case 3:
                    System.out.println("L'estructura de l'arbre que penja d'aquesta restricció és: ");
                    MostraArbre(r);
                    System.out.println();
                    break;
                case 4: selecciona_Fill1(r);break;
                case 5:selecciona_Fill2(r);break;
                case 6:
                    System.out.println("Accedim al fill dret de la restricció actual");
                    acces_FillDret(r);
                    break;
                case 7:
                    System.out.println("Accedim al fill esquerre de la restricció actual");
                    acces_FillEsq(r);
                    break;
                case 8:
                    System.out.println("El tipus de la restricció es: " + r.getTipus());
                    break;
                case 9:
                    System.out.println("El nombre de vertex de l'arbre es: " + r.getNumVertex());
            }

        }
    }

    private static void MostraArbre(Object o) throws Error {
        if (R_AND.class.equals(o.getClass())
                || R_XOR.class.equals(o.getClass())
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
                case "XOR":
                    R_XOR xor = (R_XOR) r;
                    System.out.print("XOR ( ");
                    MostraArbre(xor.getFill1());
                    System.out.print(" || ");
                    MostraArbre(xor.getFill2());
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
                    System.out.print("NOP ( ");
                    MostraArbre(nop.getTorn());
                    System.out.print(" )");
                    break;
            }
        } else {
            String torn = " " + o + " ";
            System.out.print(torn);
        }
    }

    private static void selecciona_Fill1(Restriccio r) {
        String op = r.getOp();
        switch (op) {
            case "AND":
                R_AND and = (R_AND) r;
                Object oAnd = and.getFill1();
                if (oAnd.getClass().equals(String.class)) {
                    System.out.println("El primer torn es " + oAnd);
                } else {
                    Restriccio f1 = (Restriccio) oAnd;
                    System.out.println("La primera restricció es " + f1.getOp());
                }
                break;
            case "XOR":
                R_XOR xor = (R_XOR) r;
                Object oXor = xor.getFill1();
                if (String.class.equals(oXor.getClass())) {
                    System.out.println("El primer torn es " + oXor);
                } else {
                    Restriccio f1 = (Restriccio) oXor;
                    System.out.println("La primera restricció es " + f1.getOp());
                }
                break;
            case "NOT":
                R_NOT not = (R_NOT) r;
                Object oNot = not.getFill();
                if (String.class.equals(oNot.getClass())) {
                    System.out.println("El primer torn es " + oNot);
                } else {
                    Restriccio f1 = (Restriccio) oNot;
                    System.out.println("La primera restricció es " + f1.getOp());
                }
                break;
            case "NOP":
                R_NOP nop = (R_NOP) r;
                System.out.println("El primer torn es " + nop);
                break;
        }
    }

    private static void selecciona_Fill2(Restriccio r) {
        String op2 = r.getOp();
        switch (op2) {
            case "AND":
                R_AND and = (R_AND) r;
                Object oAnd = and.getFill2();
                if (oAnd.getClass().equals(String.class)) {
                    System.out.println("El segon torn es " + oAnd);
                } else {
                    Restriccio f2 = (Restriccio) oAnd;
                    System.out.println("La segona restricció es " + f2.getOp());
                }
                break;
            case "XOR":
                R_XOR xor = (R_XOR) r;
                Object oXor = xor.getFill2();
                if (String.class.equals(oXor.getClass())) {
                    System.out.println("El segon torn es " + oXor);
                } else {
                    Restriccio f2 = (Restriccio) oXor;
                    System.out.println("La segona restricció es " + f2.getOp());
                }
                break;
        }
    }

    private static void acces_FillDret(Restriccio r) {
        String op5 = r.getOp();
        switch (op5) {
            case "AND":
                R_AND and = (R_AND) r;
                if (!String.class.equals(and.getFill1().getClass())) {
                    r = (Restriccio) and.getFill1();
                } else {
                    System.err.println("el fill no es una restriccio");
                }
                break;
            case "XOR":
                R_XOR xor = (R_XOR) r;
                if (!String.class.equals(xor.getFill1().getClass())) {
                    r = (Restriccio) xor.getFill1();
                } else {
                    System.err.println("el fill no es una restriccio");
                }
                break;
            case "NOT":
                R_NOT not = (R_NOT) r;
                if (!String.class.equals(not.getFill().getClass())) {
                    r = (Restriccio) not.getFill();
                } else {
                    System.err.println("el fill no es una restriccio");
                }
                break;
        }
    }

    private static void acces_FillEsq(Restriccio r) {
        String op6 = r.getOp();
        switch (op6) {
            case "AND":
                R_AND and = (R_AND) r;
                if (!String.class.equals(and.getFill2().getClass())) {
                    r = (Restriccio) and.getFill2();
                } else {
                    System.err.println("el fill no es una restriccio");
                }
                break;
            case "XOR":
                R_XOR xor = (R_XOR) r;
                if (!String.class.equals(xor.getFill2().getClass())) {
                    r = (Restriccio) xor.getFill2();
                } else {
                    System.err.println("el fill no es una restriccio");
                }
                break;
        }
    }
}
