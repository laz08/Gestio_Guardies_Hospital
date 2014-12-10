/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class DriverCtrlEntrada {

    private static Graf graf = new Graf();

    public static void main(String[] args) throws Error {
        prepara_prova();
        graf = CtrlEntrada.crea_graf();
        graf.mostra_graf();
        System.out.println("---------------------------------");
    }

    private static void prepara_prova() throws Error {
        //Cream una plantilla de prova
        CtrlPlantilla.creariAfegirPlantilla("Prova");
        CtrlPlantilla.setPlantillaActual("Prova");

        //Cream un conjunt de doctors de prova i els afagim a la plantilla
        for (int i = 0; i < 4; i++) {
            CtrlHospital.creariAfegirDoctor(i + "", "Doc" + i, "...", "...", 0, 0, "prova");
            CtrlPlantilla.afegirDoctorAPlantilla(i + "", "Prova");
        }

        ArrayList<Torn> lt = new ArrayList<Torn>();
        for (int i = 1; i <= 5; i++) {
            GregorianCalendar d_i = new GregorianCalendar(1000, 0, i, 00, 00);
            GregorianCalendar d_f = new GregorianCalendar(1000, 0, i, 23, 59);
            ArrayList<String> ldoc = new ArrayList<String>();
            Torn t = new Torn(d_i, d_f, 1, ldoc, 10);
            lt.add(t);
        }

        Calendari clnd = new Calendari(1, "Prova", lt);
        CtrlCalendari.afegir_calendari(clnd);

        CtrlRestriccio.nova_res("D NOT(1-0)");
        CtrlRestriccio.nova_res("D (NOP(2-0))XOR((3-0)AND(4-0))");
        ArrayList<Restriccio> llista_res = CtrlRestriccio.consulta_llista_res();

        Plantilla p = CtrlPlantilla.getPlantillaActual();
        Iterator<Doctor> it_doc= p.getLlistaDoctorsDNI().iterator();
        // afagim la restriccio al primer i darrer doctor
        if(it_doc.hasNext()){
            Doctor d = it_doc.next();
            d.afegir_res(llista_res.get(0));
            while(it_doc.hasNext()){
                d = it_doc.next();
            }
            d.afegir_res(llista_res.get(1));
        }
    }
}
