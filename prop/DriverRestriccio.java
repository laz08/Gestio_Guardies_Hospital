/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.Scanner;

public class DriverRestriccio {

    // per introduir un String que representi una restricció ha de ser (___)"operacio"(___)
    // dins el parentesi `ot haver-hi tant una altre restriccio com un String (que representara un torn)
    // la operació ha de ser AND, OR, NOT o NOP
    
    public static void main(String[] args) throws Error {
        Restriccio r = new Restriccio() {
            @Override
            public String getOp() {
                return "no Conté una operació definida ja que és una restriccio de prova";
            }

            @Override
            public String getId() {
                return "id de prova";
            }
        };
        
        Scanner scan;
        boolean sortir = false;
        while (!sortir) {
            System.out.println("---------MENU---------");
            System.out.println("0 => Sortir");
            System.out.println("1 => cerca_tancament(String s)");
            System.out.println("2 => llegeix_op(String s)");
            System.out.println("3 => esRestriccio(String s)");
            System.out.println("4 => getNumVertex()");
            System.out.println("5 => setNumVertex(int nv)");
            System.out.println("6 => getTipus()");
            System.out.println("7 => setTipus()");
            
            
            scan = new Scanner(System.in);

            int opcio = scan.nextInt();
            switch (opcio) {
                case 0:
                    sortir = true;
                    break;
                case 1:
                    String sub = Restriccio.cerca_tancament(scan.next());
                    System.out.println("La expressió que hi ha dins el primer parentesis es: "+sub);
                    break;
                case 2:
                    String op = Restriccio.llegeix_op(scan.next());
                    System.out.println("L'operacio es: "+ op);
                    break;
                case 3:
                    boolean restriccio = Restriccio.esRestriccio(scan.next()); 
                    System.out.println(restriccio);
                    break;
                case 4:
                    System.out.println(r.getNumVertex());
                    break;
                case 5:
                    r.setNumVertex(scan.nextInt());
                    break;
                case 6:
                    System.out.println(r.getTipus());
                    break;
                case 7:
                    r.setTipus(scan.next());
                    break;
            }

        }
    }
}
