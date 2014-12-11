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

        Calendari c = new Calendari("Prova",1000, 1001);
        CtrlPlantilla.consultarPlantilla("Prova").set_calendari_asoc(c);
        //Cream un conjunt de doctors de prova i els afagim a la plantilla
        for (int i = 0; i < 4; i++) {
            CtrlHospital.creariAfegirDoctor(i + "", "Doc" + i, "...", "...", 0, 0, "prova");
            CtrlPlantilla.afegirDoctorAPlantilla(i + "", "Prova");
        }

        Dia[] any = c.getCalendari();
        for (int i = 0; i < 5; i++) {
            for(int e=0; e<3; e++){
                Torn t = new Torn(0+e*8, 8+e*8-1, 10, 2);
                Dia d = any[i];
                if(d!=null) d.setTornConcret(t, e);
            }
        }

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
