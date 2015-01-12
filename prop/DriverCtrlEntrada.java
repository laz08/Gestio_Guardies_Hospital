/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;
import java.util.Iterator;

public class DriverCtrlEntrada {

    private static Graf graf = new Graf();

    public static void main(String[] args) throws Error {
        prepara_prova();
        CtrlAlgorisme.initGraf();
        Algorisme algo = new Dijkstra(true);
        //algo.maxFlow();
        Thread thr = new Thread(algo);
        thr.start();
        //graf.mostra_graf();
        while(thr.isAlive());
        Doc_Torn.mostraRelacions();
    }

    private static void prepara_prova() throws Error {
        //Cream una plantilla de prova
        CtrlPlantilla.creariAfegirPlantilla("Prova");
        CtrlPlantilla.setPlantillaActual("Prova");

        Calendari c = new Calendari("Prova", 1000, 1000);
        CtrlCalendari.afegirCalendarif(c);
        CtrlPlantilla.consultarPlantilla("Prova").set_calendari_asoc(c);
        //Cream un conjunt de doctors de prova i els afagim a la plantilla
        for (int i = 0; i < 6; i++) {
            CtrlHospital.creariAfegirDoctor(i + "", "Doc" + i, "...", "...", (int) (Math.random()*10), (int) (Math.random()*10), "prova");
            CtrlPlantilla.afegirDoctorAPlantilla(i + "", "Prova");
        }

        Dia[] any = c.getCalendari();
        for (int i = 0; i < 2; i++) {
            
            for (int e = 0; e < 3; e++) {
                Torn t = new Torn(e, 10, 2);
                Dia d = any[i];
                switch (e) {
                    case 0:
                        t.setPosicio(i);
                        d.setTornMati(t);
                        break;
                    case 1:
                        t.setPosicio(i);
                        d.setTornTarda(t);
                        break;
                    case 2:
                        t.setPosicio(i);
                        d.setTornNit(t);
                        break;
                }
            }
        }
        CtrlRestriccio.nova_res("D NOP(1-1)");
        CtrlRestriccio.nova_res("D (NOP(1-1))AND((2-1)XOR(3-1))");
        CtrlRestriccio.nova_res("D (1-1)AND(2-1)");
        CtrlRestriccio.nova_res("D (NOP(2-1))XOR(NOP(1-1))");
        ArrayList<Restriccio> llista_res = CtrlRestriccio.consulta_llista_res();

        Plantilla p = CtrlPlantilla.getPlantillaActual();
        Iterator<Doctor> it_doc = p.getLlistaDoctorsDNI().iterator();
        // afagim la restriccio al primer i darrer doctor
        int count = 1, nres = 0;
        if (it_doc.hasNext()) {
            Doctor d = it_doc.next();
            d.afegir_res(llista_res.get(nres));
            count++; nres++;
            while (it_doc.hasNext()) {
                d = it_doc.next();
                if (count == 3 || count == 5 || count == 6) {
                    d.afegir_res(llista_res.get(nres));
                    nres++;
                }
                count++;
            }
        }
    }
}
