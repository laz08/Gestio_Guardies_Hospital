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

        @Override
        public String getId() {
            return "id restriccio";
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
        Calendari c = plt.get_calendari_asoc();
        //ArrayList<Torn> torns = c.getTorns();
        Dia[] any = c.getCalendari();
            System.out.println("Per seleccionar un torn, introdueix la seva posició de la llista dins l'expressió\n");
            switch (tipus) {
                case "D":
                    System.out.println("ATENCIÓ heu seleccionat elecció de torns per dia d'inici, es possible"
                            + " que un torn comenci un dia i acabi un altre.");
                    for (int i = 0; i < any.length; i++) {
                        System.out.println("-------------DIA " + i + "-------------");
                        for(int e = 0; e<3; e++){
                            Torn torn = null;
                            switch(e){
                                case 0:
                                    torn = any[i].getTornMati();
                                    break;
                                case 1:
                                    torn = any[i].getTornTarda();
                                    break;
                                case 2:
                                    torn = any[i].getTornNit();
                                    break;
                            }
                            if(torn != null){
                                System.out.println("Torn de "+torn.getHora_inici()+"h fins "+torn.getHora_fi());
                            }
                        }
                    }
                    break;
                case "H":
                    System.out.println("ATENCIÓ heu seleccionat elecció de torns per hora d'inici, es possible"
                            + " que un torn ocupi varies hores o que s'inici a la mitat d'una");
                    System.out.println("Les hores venen determinades per valors unicament numerics de 00 a 23");
                    break;
                case "S":
                    System.out.println("ATENCIÓ heu seleccionat elecció de torns per setmana");
                    System.out.println("Les setmanes disponibles van de 1 a 53");

                default:
                    System.out.println("NO ES RECONEIX EL TIPUS DE RESTRICCIÓ");
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
        CtrlPlantilla.setPlantillaActual("Prova_Driver_Restriccions");

        //Cream un conjunt de doctors de prova i els afagim a la plantilla
        for (int i = 0; i < 10; i++) {
            CtrlHospital.creariAfegirDoctor(i + "", "Doc" + i, "...", "...", 0, 0, "prova");
            CtrlPlantilla.afegirDoctorAPlantilla(i + "", "Prova_Driver_Restriccions");
        }
        
        Calendari c = new Calendari("Prova_Driver_Restriccions", 1000, 1001);
        CtrlPlantilla.getPlantillaActual().set_calendari_asoc(c);
        Dia[] any = c.getCalendari();
        for (int i = 0; i < any.length; i++) {
            for(int e=0; e<3; e++){
                Torn t = new Torn(0+e*8, 8+e*8-1, 10, 2);
                    switch(e){
                        case 0:
                            any[i].setTornMati(t);
                            break;
                        case 1:
                            any[i].setTornTarda(t);
                            break;
                        case 2:
                            any[i].setTornNit(t);
                            break;
                    }
            }
        }
    }
}
