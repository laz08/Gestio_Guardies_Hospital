package prop;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DriverCtrlCalendari {

    static CtrlCalendari c = new CtrlCalendari();
    static Scanner arg = new Scanner(System.in);

    public static void main(String[] args) {

        int cas;
        boolean sortir = false;
        escriureMenu();

        while (!sortir && arg.hasNext()) {
            try {
                cas = arg.nextInt();
            } catch (Exception e) {
                System.out.println("S'esperava la introduccio d'un numero.");
                System.out.print(">> ");
                arg.next();
                continue;
            }

            switch (cas) {
                case 0:
                    sortir = true;
                    break;
                case 1:
                    consultar_calendari();
                    break;
                case 2:
                    consultar_calendari_doctor();
                    break;
                case 3:
                    afegir_torn();
                    break;
                case 4:
                    modificar_torn();
                    break;
                case 5:
                    eliminar_torn();
                    break;
                case 6:
                    afegir_calendari();
                    break;
                case 7:
                    eliminar_calendari();
                    break;
                default:
                    System.out.println("El número ha d'estar entre 0 i 7");
            }
            if (cas != 0) {
                escriureMenu();
            }
        }
    }

    public static void escriureMenu() {
        System.out.println("----------Menu:-----------\n"
                + "1. Consultar calendari(id plantilla: int)\n"
                + "2. Consultar calendari d'un doctor(id plantilla: int, dni doctor: String)\n"
                + "3. Afegir torn a una plantilla(id plantilla: String, torn: (data inici (yyyy:int, mm:int, dd:int, hh:int), data fi (yyyy:int, mm:int, dd:int, hh:int), numero minim doctors: int, numero doctors assignats: int, doctors assignats: String .. String, percentatge sou: float)\n"
                + "4. Modificar torn(id plantilla: int, data inici del torn a modificar(yyyy:int, mm:int, dd:int, hh:int), torn_nou)\n"
                + "5. Eliminar torn(id plantilla: int, data inici del torn a eliminar(yyyy:int, mm:int, dd:int, hh:int))\n"
                + "6. Afegir calendari(id_Calendari: int, id plantilla: int, torn..torn)\n"
                + "7. Eliminar calendari(id plantilla: int)\n"
                + "0. Sortir");
        System.out.print(">> ");
    }

    public static void afegir_torn() {
        String id_plt = arg.next();
        System.out.println("Plantilla: " + id_plt);
        Calendari cl = CtrlCalendari.consultar_calendari(id_plt);
        if (cl != null) {
            Torn t = crear_torn();
            c.afegir_torn_calendari(t, cl);
        } else {
            System.out.println("No hi ha cap calendari associat a la plantilla amb id " + id_plt);
        }
    }

    public static void modificar_torn() {
        String id_plt = arg.next();
        Calendari cl = CtrlCalendari.consultar_calendari(id_plt);

        if (cl != null) {
            ArrayList<Torn> llt = cl.getTorns();
            for (int i = 0; i < llt.size(); i++) {
                escriure_torn(llt.get(i));
            }
            System.out.println("Introdueix la data inici del torn a modificar(yyyy mm dd hh) i tots els parametres d'un torn per modificarlo\n");
            // DATA INICI
            int any = -1;
            boolean valid = false;
            while (!valid) {
                try {
                    any = arg.nextInt();
                    valid = true;
                } catch (Exception e) {
                    System.out.println("Any ha de ser un numero.");
                    System.out.println("Torna a introduir l'any.");
                }
            }

            valid = false;
            int mes = -1;
            while (!valid) {
                try {
                    mes = arg.nextInt() - 1;
                    valid = true;
                } catch (Exception e) {
                    System.out.println("Mes ha de ser un numero.");
                    System.out.println("Torna a introduir el mes.");
                }
            }

            valid = false;
            int dia = -1;
            while (!valid) {
                try {
                    dia = arg.nextInt();
                    valid = true;
                } catch (Exception e) {
                    System.out.println("Dia ha de ser un numero.");
                    System.out.println("Torna a introduir el dia.");
                }
            }


            valid = false;
            int hora = -1;
            while (!valid) {
                try {
                    hora = arg.nextInt();
                    valid = true;
                } catch (Exception e) {
                    System.out.println("Hora ha de ser un numero.");
                    System.out.println("Torna a introduir l'hora.");
                }
            }
            GregorianCalendar data_inici = new GregorianCalendar(any, mes, dia, hora, 0);
            Torn t = CtrlCalendari.torn_data(data_inici, cl);
            if (t != null) {
                Torn t_nou = crear_torn();
                CtrlCalendari.modificar_torn(t, t_nou, cl);
            }
        }
    }

    public static void eliminar_torn() {
        String id_plt = arg.next();
        Calendari cl = CtrlCalendari.consultar_calendari(id_plt);

        if (cl != null) {
            ArrayList<Torn> llt = cl.getTorns();
            for (int i = 0; i < llt.size(); i++) {
                escriure_torn(llt.get(i));
            }
            System.out.println("Introdueix la data inici del torn que vols eliminar\n");
            int any = arg.nextInt();
            int mes = arg.nextInt() - 1;
            int dia = arg.nextInt();
            int hora = arg.nextInt();
            GregorianCalendar data_inici = new GregorianCalendar(any, mes, dia, hora, 0);
            CtrlCalendari.eliminar_torn_data(data_inici, cl);
        } else {
            System.out.println("No existeix cap plantilla amb el nom " + id_plt);
        }
    }

    public static void consultar_calendari() {
        String id_plt = arg.next();
        Calendari cld = CtrlCalendari.consultar_calendari(id_plt);
        if (cld != null) {
            System.out.println("El identificador es: " + cld.getId());
            System.out.println("Associat a la plantilla: " + cld.getId_plantilla());
            ArrayList<Torn> llt = cld.getTorns();
            if (!llt.isEmpty()) {
                for (int i = 0; i < llt.size(); i++) {
                    escriure_torn(llt.get(i));
                }
            }
        }
    }

    public static void consultar_calendari_doctor() {
        String id_plt = arg.next();
        Calendari cld = CtrlCalendari.consultar_calendari(id_plt);
        if (cld != null) {
            String doc = arg.next();
            ArrayList<Torn> llt = cld.getTorns();
            for (int i = 0; i < llt.size(); i++) {
                ArrayList<String> ldoc = llt.get(i).getDoc_assignats();
                int j = 0;
                while (j < ldoc.size() && doc.compareTo(ldoc.get(j)) > 0) {
                    ++j;
                }
                if (j < ldoc.size() && doc.compareTo(ldoc.get(j)) == 0) {
                    escriure_torn(llt.get(i));
                }
            }
        }
    }

    public static int posicio_torn(ArrayList<Torn> lt, Torn t) {
        GregorianCalendar d_inici = t.getData_inici();
        GregorianCalendar d_fi = t.getData_fi();
        int i = 0;
        while (i != -1 && i < lt.size() && lt.get(i).getData_inici().before(d_fi)) {
            if ((d_fi.before(lt.get(i).getData_inici()) && d_fi.after(lt.get(i).getData_fi()))
                    || (d_inici.after(lt.get(i).getData_inici()) && d_inici.before(lt.get(i).getData_inici()))) {
                System.out.println("El torn es solapa amb un altre torn ja existent");
                i = -1;
            } else {
                ++i;
            }
        }
        return i;
    }

    public static void afegir_t(ArrayList<Torn> lt, Torn t) {
        if (lt.isEmpty()) {
            lt.add(t);
        } else {
            int i = posicio_torn(lt, t);
            if (i != -1) {
                lt.add(i, t);
            }
        }
    }

    public static void afegir_calendari() {
        boolean valid = false;

        int id = -1;
        while (!valid) {
            try {
                id = arg.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("ID calendari ha de ser un numero.");
                System.out.println("Torna a introduir ID calendari.");
            }
        }


        String id_plt = arg.next();

        int n = -1;
        while (!valid) {
            try {
                n = arg.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("n ha de ser un numero.");
                System.out.println("Torna a introduir n.");
            }
        }
        ArrayList<Torn> lt = new ArrayList<Torn>();
        Torn t1 = new Torn();
        for (int i = 0; i < n; i++) {
            t1 = crear_torn();
            afegir_t(lt, t1);
        }
        Calendari cld = new Calendari(id, id_plt, lt);
        c.afegir_calendari(cld);
    }

    public static void eliminar_calendari() {
        String id_plt = arg.next();
        c.eliminar_calendari(id_plt);
    }

    public static void escriure_torn(Torn t) {
        GregorianCalendar dia = t.getData_inici();
        GregorianCalendar diaf = t.getData_fi();
        System.out.println("\n----Torn----");
        System.out.println("Data inici: " + dia.getTime().toString()
                + "\nData fi: " + diaf.getTime().toString()
                + "\nNumero minim doctors: " + t.getN_min_doc()
                + "\nPercentatge de sou: " + t.getPercent_sou()
                + "\nDoctors: ");
        ArrayList<String> l = t.getDoc_assignats();
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
    }

    public static Torn crear_torn() {
        Torn t;
        boolean valid = false;


        // DATA INICI
        int any = -1;
        while (!valid) {
            try {
                any = arg.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Any ha de ser un numero.");
                System.out.println("Torna a introduir l'any.");
            }
        }

        valid = false;
        int mes = -1;
        while (!valid) {
            try {
                mes = arg.nextInt() - 1;
                valid = true;
            } catch (Exception e) {
                System.out.println("Mes ha de ser un numero.");
                System.out.println("Torna a introduir el mes.");
            }
        }

        valid = false;
        int dia = -1;
        while (!valid) {
            try {
                dia = arg.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Dia ha de ser un numero.");
                System.out.println("Torna a introduir el dia.");
            }
        }


        valid = false;
        int hora = -1;
        while (!valid) {
            try {
                hora = arg.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Hora ha de ser un numero.");
                System.out.println("Torna a introduir l'hora.");
            }
        }
        GregorianCalendar data_inici = new GregorianCalendar(any, mes, dia, hora, 0);

        //DATA FI
        valid = false;
        while (!valid) {
            try {
                any = arg.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Any ha de ser un numero.");
                System.out.println("Torna a introduir l'any.");
            }
        }

        valid = false;
        while (!valid) {
            try {
                mes = arg.nextInt() - 1;
                valid = true;
            } catch (Exception e) {
                System.out.println("Mes ha de ser un numero.");
                System.out.println("Torna a introduir el mes.");
            }
        }

        valid = false;
        while (!valid) {
            try {
                dia = arg.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Dia ha de ser un numero.");
                System.out.println("Torna a introduir el dia.");
            }
        }


        valid = false;
        while (!valid) {
            try {
                hora = arg.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Hora ha de ser un numero.");
                System.out.println("Torna a introduir l'hora.");
            }
        }
        GregorianCalendar data_fi = new GregorianCalendar(any, mes, dia, hora, 0);

        valid = false;
        int min_doc = -1;
        while (!valid) {
            try {
                min_doc = arg.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Min_doc ha de ser un numero.");
                System.out.println("Torna a introduir el minim de doctors.");
            }
        }

        valid = false;
        int n = -1;
        while (!valid) {
            try {
                n = arg.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Min_doc ha de ser un numero.");
                System.out.println("Torna a introduir el minim de doctors.");
            }
        }

        String doc;
        ArrayList<String> doctors_assig = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            doc = arg.next();
            if (doctors_assig.isEmpty()) {
                doctors_assig.add(doc);
            } else {
                int j = 0;
                while (j < doctors_assig.size() && doctors_assig.get(j).compareTo(doc) < 0) {
                    ++j;
                }
                doctors_assig.add(j, doc);
            }
        }
        float p_sou = Float.parseFloat(arg.next());
        System.out.println("p_sou: " + p_sou);
        t = new Torn(data_inici, data_fi, min_doc, doctors_assig, p_sou);
        return t;
    }
}
