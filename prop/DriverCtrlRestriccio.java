/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DriverCtrlRestriccio {

    private static Restriccio restriccio = new Restriccio() {
        @Override
        public String getOp() {
            return "Prova";
        }
    };

    public static void main(String[] args) {
        prepara_plantilla_prova();
        boolean sortir = false;

        while (!sortir) {
            mostra_menu();
            Scanner scan = new Scanner(System.in);
            int op = Integer.parseInt(scan.nextLine());
            switch (op) {
                case 0:
                    sortir = true;
                    break;
                case 1: //nova restriccio
                    System.out.println("Respecte a què vols introduir les rectriccions? (H, D, S)");
                    String tipus = scan.nextLine();
                    mostra_llista_torns(tipus);
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
                    llista_seleccio_restriccions();
                    break;
                case 4:
                    llista_seleccio_restriccions();
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

    private static void mostra_menu() {
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

    private static void mostra_llista_torns(String tipus) {
        Plantilla plt = CtrlPlantilla.getPlantillaActual();
        Calendari c = CtrlCalendari.consultar_calendari(plt.getNomPlantilla());
        ArrayList<Torn> torns = c.getTorns();
        if (torns.isEmpty()) {
            System.out.println("No hi ha torns creats a la plantilla actual");
        } else {
            System.out.println("Per seleccionar un torn, introdueix la seva posició de la llista dins l'expressió\n");
            switch (tipus) {
                case "D":
                    System.out.println("ATENCIÓ heu seleccionat elecció de torns per dia d'inici, es possible"
                            + " que un torn comenci un dia i acabi un altre.");
                    for (int i = 0; i < torns.size(); i++) {
                        int d_i = torns.get(i).getData_inici().get(Calendar.DAY_OF_YEAR);
                        int d_f = torns.get(i).getData_fi().get(Calendar.DAY_OF_YEAR);

                        System.out.println("-------------DIA " + d_i + "-------------");
                        System.out.println("desde " + torns.get(i).getData_inici().getTime());
                        if (d_i == d_f) {
                            int d_i2 = d_f;
                            int seguent = i;
                            while (d_i == d_i2 && seguent+1 < torns.size()) {
                                seguent++;
                                i = seguent - 1;
                                d_i2 = torns.get(seguent).getData_inici().get((Calendar.DAY_OF_YEAR));
                            }
                            if (d_i == d_i2) {
                                i = seguent;
                            }
                            System.out.println("fins " + torns.get(i).getData_fi().getTime());
                        }
                    }
                    break;
                case "H":
                    System.out.println("ATENCIÓ heu seleccionat elecció de torns per hora d'inici, es possible"
                            + " que un torn ocupi varies hores o que s'inici a la mitat d'una");
                    System.out.println("Les hores venen determinades per valors unicament numerics de 00 a 23");
                    break;
                case "S":
                    System.out.println("ATENCIÓ heu seleccionat elecció de torns per setmana d'inici, es possible"
                            + " que un torn comenci una setmana i acabi una altra.");
                    for (int i = 0; i < torns.size(); i++) {
                        int s_i = torns.get(i).getData_inici().get(Calendar.WEEK_OF_YEAR);
                        int s_f = torns.get(i).getData_fi().get(Calendar.WEEK_OF_YEAR);

                        System.out.println("-------------SETMANA " + s_i + "-------------");
                        System.out.println("desde " + torns.get(i).getData_inici().getTime());
                        if (s_i == s_f) {
                            int s_i2 = s_f;
                            int seguent = i;
                            while (s_i == s_i2 && seguent + 1 < torns.size()) {
                                seguent++;
                                i = seguent - 1;
                                s_i2 = torns.get(seguent).getData_inici().get((Calendar.WEEK_OF_YEAR));
                            }
                            if (s_i == s_i2) {
                                i = seguent;
                            }
                        }
                        System.out.println("fins " + torns.get(i).getData_fi().getTime());
                    }
                    break;

                default:
                    System.out.println("NO ES RECONEIX EL TIPUS DE RESTRICCIÓ");
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

    private static void prepara_plantilla_prova() {
        //Cream una plantilla de prova
        CtrlPlantilla.creariAfegirPlantilla("Prova_Driver_Restriccions");
        int plantilla = CtrlPlantilla.existeixPlantilla("Prova_Driver_Restriccions");
        CtrlPlantilla.setPlantillaActual(plantilla);

        //Cream un conjunt de doctors de prova i els afagim a la plantilla
        for (int i = 0; i < 10; i++) {
            CtrlHospital.creariAfegirDoctor(i + "", "Doc" + i, "...", "...", 0, 0, "prova");
            CtrlPlantilla.afegirDoctorAPlantilla(i + "", "Prova_Driver_Restriccions");
        }

        ArrayList<Torn> lt = new ArrayList<Torn>();
        for (int i = 0; i < 62; i++) {
            int dia = Math.round(i / 2);
            int hora = (i % 2) * 12;
            GregorianCalendar d_i = new GregorianCalendar(1000, 1, dia, hora, 00);
            GregorianCalendar d_f = new GregorianCalendar(1000, 1, dia, hora + 11, 59);
            ArrayList<String> ldoc = new ArrayList<String>();
            Torn t = new Torn(d_i, d_f, 1, ldoc, 10);
            lt.add(t);
        }

        Calendari clnd = new Calendari(1, "Prova_Driver_Restriccions", lt);
        CtrlCalendari.afegir_calendari(clnd);
    }
}
