/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverCtrlRestriccio {

    private static Restriccio restriccio = new Restriccio() {
        @Override
        public String getOp() {
            return "Prova";
        }
    };

    public static void main(String[] args) {
        System.out.println("Per crear restriccions, primer s'ha de crar torns al calendari de la plantilla");
        DriverCtrlPlantilla.main(args);
        DriverCtrlCalendari.main(args);
        boolean sortir = false;
        Scanner scan = new Scanner(System.in);
        while (!sortir) {
            mostra_menu();
            int op = Integer.parseInt(scan.next());
            switch (op) {
                case 0:
                    sortir = true;
                    break;
                case 1: //nova restriccio
                    System.out.println("Respecte a què vols introduir les rectriccions? (H, D, S)");
                    String tipus = scan.nextLine();
                    mostra_llista_torns();
                    System.out.println("Insereix la expresió que defineix la restricció que vols afagir: ");
                    String expressio = scan.nextLine();
                    CtrlRestriccio.nova_res(tipus + " " + expressio);
                    break;
                case 2: // elimina restriccio
                    System.out.println("Insereix la posició de la restricció que vols eliminar: ");
                    llista_seleccio_restriccions();
                    ArrayList<Restriccio> llista_r = CtrlRestriccio.consulta_llista_res();
                    Restriccio r = llista_r.get(scan.nextInt() - 1);
                    CtrlRestriccio.elimina_res(r);
                    break;
                case 3:
                    ArrayList<Restriccio> llista_r1 = CtrlRestriccio.consulta_llista_res();
                    for (int i = 0; i < llista_r1.size(); i++) {
                        Restriccio restriccio = llista_r1.get(i);
                        System.out.print("Restriccio " + i + ": ");
                        System.out.println(CtrlRestriccio.mostra_arbre(restriccio));
                    }
                    break;
                case 4:
                    restriccio = CtrlRestriccio.consulta_res(scan.nextInt());
                    break;
                case 5:
                    if (restriccio.getOp().equals("Prova")) {
                        System.out.println("Primer has de consultar una restricció");
                    } else {
                        System.out.println("L'arbre que es genera a partir de la restricció es: ");
                        System.out.println(CtrlRestriccio.mostra_arbre(restriccio));
                    }
                    break;
                case 6:
                    System.out.println("La restricció es troba a la posició " + CtrlRestriccio.consulta_pos(restriccio));
                    break;
                case 7:
                    if (restriccio.getOp().equals("Prova")) {
                        System.out.println("Primer has de consultar una restricció");
                    } else {
                        Object obj1 = CtrlRestriccio.selecciona_Fill1(restriccio);
                        if (!obj1.getClass().equals(R_NOP.class)
                                && !obj1.getClass().equals(R_NOT.class)
                                && !obj1.getClass().equals(R_AND.class)
                                && !obj1.getClass().equals(R_XOR.class)) {
                            System.out.println("El fill esquerra no es una restricció");
                        } else {
                            restriccio = (Restriccio) obj1;
                        }
                    }
                    break;
                case 8:
                    if (restriccio.getOp().equals("Prova")) {
                        System.out.println("Primer has de consultar una restricció");
                    } else {
                        Object obj2 = CtrlRestriccio.selecciona_Fill2(restriccio);

                        if (!obj2.getClass().equals(R_NOP.class)
                                && !obj2.getClass().equals(R_NOT.class)
                                && !obj2.getClass().equals(R_AND.class)
                                && !obj2.getClass().equals(R_XOR.class)) {
                            System.out.println("El fill dret no es una restricció");
                        } else {
                            restriccio = (Restriccio) obj2;
                        }
                    }
                    break;
            }
        }
    }

    public static void mostra_menu() {
        System.out.println("------- MENU -------");
        System.out.println("0 => Sortir");
        System.out.println("1 => nova restriccio");
        System.out.println("2 => elimina restriccio");
        System.out.println("3 => mostra llista restriccions");
        System.out.println("4 => consulta restriccio de la llista");
        System.out.println("5 => mostra la restricció consultada");
        System.out.println("6 => consulta la posició de la restriccio dins la llista");
        System.out.println("7 => Selecciona fill esquerra de una restricció");
        System.out.println("8 => Selecciona fill dret d'una restricció");
    }

    public static void mostra_llista_torns() {
        System.out.println("Introdueix l'expressió que defineix la nova restricció que vols crear: ");
        Plantilla plt = CtrlPlantilla.getPlantillaActual();
        Calendari c = CtrlCalendari.consultar_calendari(plt.getNomPlantilla());
        ArrayList<Torn> torns = c.getTorns();
        if (torns.isEmpty()) {
            System.out.println("No hi ha torns creats a la plantilla actual");
        } else {
            System.out.println("Per seleccionar un torn, introdueix la seva posició de la llista dins l'expressió\n");
            for (int i = 0; i < torns.size(); i++) {
                System.out.println("-------------TORN " + i + "-------------");
                System.out.println("desde " + torns.get(i).getData_inici().getTime());
                System.out.println("fins " + torns.get(i).getData_fi().getTime());
            }
        }
    }

    private static void llista_seleccio_restriccions() {
        ArrayList<Restriccio> llista_r = CtrlRestriccio.consulta_llista_res();
        for (int i = 0; i < llista_r.size(); i++) {
            Restriccio restriccio = llista_r.get(i);
            System.out.print(i + 1 + " => ");
            System.out.println(CtrlRestriccio.mostra_arbre(restriccio));
        }
    }
}
