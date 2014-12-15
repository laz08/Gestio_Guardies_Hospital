/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.TreeSet;

public class CtrlEntrada {

    public static Graf crea_graf() {
        Graf g = new Graf();
        Plantilla p = CtrlPlantilla.getPlantillaActual();
        try {
            Vertex pou = new Vertex("POU", Vertex.FONT_POU);
            Vertex font = new Vertex("FONT", Vertex.FONT_POU);
            g.afegirVertex(pou);
            g.afegirVertex(font);
            posaVertexDoctor(g, p);
            posaVertexTorn(g);
            posaRestriccions(p.getLlistaDoctorsDNI(), g);
            posaArestesMax(g, p.getLlistaDoctorsDNI().size());
        } catch (Error e) {
            System.err.println("Hi ha hagut problemes a l'hora de crear el graf\n" + e);
        }
        return g;
    }

    private static void posaVertexDoctor(Graf g, Plantilla p) throws Error {
        Iterator<Doctor> it = p.getLlistaDoctorsDNI().iterator();
        while (it.hasNext()) { // cream nodes de tipus doctor i els afagim al graf
            Doctor d = it.next();
            Vertex v = new Vertex(d.getdni(), Vertex.DOCTOR, d);
            Vertex font = g.getVertex("FONT", Vertex.FONT_POU);
            g.afegirVertex(v);
            g.afegirAresta(font, v, Graf.INFINIT, 0);
        }
    }

    private static void posaVertexTorn(Graf g) throws Error {
        Plantilla plantilla = CtrlPlantilla.getPlantillaActual();
        Dia[] dia = plantilla.get_calendari_asoc().getCalendari();
        for (int i = 0; i < dia.length; i++) { // cream nodes de tipus torn i els afagim al graf
            for (int t = 0; t < 3; t++) {
                Torn torn = null;
                switch(t){
                    case 0:
                        torn = dia[i].getTornMati();
                        break;
                    case 1:
                        torn = dia[i].getTornTarda();
                        break;
                    case 2:
                        torn = dia[i].getTornNit();
                        break;
                }
                Dia d = dia[i];
                if (torn != null) {
                    Vertex vmax = new Vertex(torn.toString(), Vertex.MAX);
                    g.afegirVertex(vmax);
                    Vertex v = new Vertex(torn.toString(), Vertex.TORN, torn);
                    Vertex pou = g.getVertex("POU", Vertex.FONT_POU);
                    g.afegirVertex(v);
                    g.afegirAresta(v, pou, torn.getMin_num_doctors(), 0);
                }
            }
        }
    }

    private static void posaRestriccions(TreeSet<Doctor> t_doc, Graf g) throws Error {
        Iterator<Doctor> it_doc = t_doc.iterator();
        Doctor d;
        Restriccio r;
        while (it_doc.hasNext()) {
            d = it_doc.next();
            ArrayList<Integer> llista_r = d.getRestriccions();
            Vertex vd = g.getVertex(d.getdni(), Vertex.DOCTOR);
            for (int e = 0; e < llista_r.size(); e++) {
                r = CtrlRestriccio.consulta_res(llista_r.get(e));
                Vertex vr = recorregut_restriccio(r, d.getdni(), g);
                g.afegirAresta(vd, vr, /*vr.getCapacitatAcumulada()*/ Graf.INFINIT, 0);
            }
        }
    }

    /**
     * Donat un graf per paràmetres, en retorna un l'invers
     *
     * @param g Graf inicial
     * @return Graf invers
     */
    public static Graf calculaInvers(Graf g) {
        Graf graf = new Graf();
        for (int i = 0; i < g.numV(); i++) { // copiam tots els vertex del graf menys les restriccions
            Vertex v = g.getVertex(i);
            if (v.getClasse() != Vertex.RESTRICCIO) {
                graf.afegirVertex(g.getVertex(i));
            }
        }
        //budam les arestes que es guarden als vertex
        for (int i = 0; i < graf.numV(); i++) {
            Vertex v = graf.getVertex(i);
            ArrayList<Integer> arestes = v.getArestes();
            for (int e = 0; e < arestes.size(); e++) {
                if (v.getClasse() != Vertex.TORN) {
                    graf.eliminaAresta(arestes.get(e));
                } else {
                    int posa = arestes.get(e);
                    Aresta aresta = g.getA(posa);
                    Vertex vp = g.getVertex(aresta.getv());
                    String id_vp = vp.getId();
                    if (id_vp.equals(v.getId()) && vp.getClasse() == Vertex.MAX) {// si el vertex origen de l'aresta no es el propi torn, es perque es el vertex Max
                        graf.afegirAresta(vp, v, aresta.getcap(), 0);
                    }
                }
            }
        }
        //relacionam la font amb els doctors y el pou amb els torns
        Vertex pou = graf.getVertex("POU", Vertex.FONT_POU);
        Vertex font = graf.getVertex("FONT", Vertex.FONT_POU);
        Plantilla p = CtrlPlantilla.getPlantillaActual();

        Iterator<Doctor> it_doc = p.getLlistaDoctorsDNI().iterator();
        Dia[] any = p.get_calendari_asoc().getCalendari();

        for (int i = 0; i < graf.numV(); i++) {
            Vertex v = graf.getVertex(i);
            if (v.getClasse() == Vertex.DOCTOR) {
                graf.afegirAresta(font, v, Graf.INFINIT, 0);
            } else if (v.getClasse() == Vertex.TORN) {
                boolean trobat = false;
                int nDia = 0;
                int capacitat = 0;
                while (!trobat && nDia < any.length) {
                    int ntorn = 0;
                    while (!trobat && ntorn < 3) {
                        Torn torn = null;
                        switch(ntorn){
                            case 0:
                                torn = any[nDia].getTornMati();
                                break;
                            case 1:
                                torn = any[nDia].getTornTarda();
                                break;
                            case 2:
                                torn = any[nDia].getTornNit();
                                break;
                        }
                        if (torn != null && torn.toString().equals(v.getId())) {
                            capacitat = torn.getMin_num_doctors();
                            trobat = true;
                        }
                        ntorn ++;
                    }
                    nDia++;
                }
                graf.afegirAresta(pou, v, capacitat, 0);
            } else if (v.getClasse() == Vertex.MAX) {
                ArrayList<String> doc_rel = v.getDoctorsRel();
                while (it_doc.hasNext()) {
                    Doctor doc = it_doc.next();
                    if (doc_rel.contains(doc.getdni())) {
                        ArrayList<Integer> a = v.getArestes();
                        for (int it = 0; it < a.size(); it++) {
                            int posAresta = a.get(it);
                            Aresta aresta = g.getA(posAresta);
                            //agafam el vertex que esta al costat contrari de l'aresta
                            Vertex vRelacionat = graf.getVertex(aresta.getv());
                            if (vRelacionat.equals(v)) {
                                vRelacionat = graf.getVertex(aresta.getw());
                            }
                            //comprovam que es la aresta de cercam
                            if (vRelacionat.getDoctorsRel().contains(doc.getdni())) {
                                if (aresta.getflow() > 0) {
                                    graf.eliminaAresta(posAresta);
                                    v.rmDoctorRel(doc.getdni());
                                }

                            }
                        }
                    } else {
                        v.addDoctorRel(doc.getdni());
                        String dni = doc.getdni();
                        graf.afegirAresta(v, graf.getVertex(dni, Vertex.DOCTOR), 1, 0);
                    }
                }
            }
        }


        return graf;
    }

    /**
     * Una vegada relacionades totes les restriccions amb els seus respectius
     * torns i doctors es dona valor a les arestes que uneixen els vertex max i
     * els torns segons les restriccions que arriben
     *
     * @param g
     */
    private static void posaArestesMax(Graf g, int numDoc) {
        for (int i = 0; i < g.numV(); i++) {
            Vertex v = g.getVertex(i);
            if (v.getClasse() == Vertex.MAX) {
                ArrayList<Integer> la = v.getArestes();
                for (int e = 0; e < la.size(); e++) {
                    Aresta a = g.getA(la.get(e));
                    Vertex vp = g.getVertex(a.getv()); // agafam el vertex anterior al vertex MAX (sempre sera una Restriccio) 
                    ArrayList<String> doc_relacionats = v.getDoctorsRel();
                    boolean existeix = false;
                    int pos = 0;
                    while (!existeix && pos < doc_relacionats.size()) {
                        if (doc_relacionats.get(pos).equals(vp.getDoctorsRel().get(0))) {
                            existeix = true;
                        }
                        pos++;
                    }
                    if (!existeix) {
                        v.addDoctorRel(vp.getDoctorsRel().get(0));
                    }
                }
                Vertex vtorn = g.getVertex(v.getId(), Vertex.TORN);
                ArrayList<Integer> llista_a = vtorn.getArestes();
                Aresta a = g.getA(llista_a.get(0)); // la seva capacitat es el num min de doctors per torn
                v.setNumMaxRestr(numDoc-a.getcap());
                g.afegirAresta(v, vtorn, numDoc, 0); // la capacitat es modificara segons les assignacions dels algorismes
            }
        }
    }

    /**
     * Fa un recorregut a l'arbre que representa cada restricció i el va
     * introduint a dins el graf
     *
     * @param r Restriccio
     * @param g Graf
     * @param vertex_torn Vertex que representen els torns afectats per aquesta
     * restriccio (inicialment buit)
     * @return
     * @throws Error
     */
    private static Vertex recorregut_restriccio(Restriccio r, String idDoc, Graf g) throws Error {
        Vertex v = new Vertex(r.getId(), Vertex.RESTRICCIO, r);
        v.addDoctorRel(idDoc);
        g.afegirVertex(v); // afagim el vertex al graf
        switch (r.getOp()) { //comprovam l'operació que representa
            case "AND":
                R_AND and = (R_AND) r;
                Object of1_and = and.getFill1();
                Object of2_and = and.getFill2();
                //Comprovam els fills, i si son String, llavors vol dir que identifiquen un torn
                if (of1_and.getClass().equals(String.class) && of2_and.getClass().equals(String.class)) {
                    String t1 = (String) of1_and;
                    String t2 = (String) of2_and;
                    ArrayList<Vertex> torns1 = consulta_torns_afectats(t1, r.getTipus(), g);
                    ArrayList<Vertex> torns2 = consulta_torns_afectats(t2, r.getTipus(), g);
                    
                    for (int i = 0; i < torns1.size(); i++) {
                        Vertex vertex = torns1.get(i);
                        g.afegirAresta(v, vertex, /*1*/ Graf.INFINIT, 0);
                    }
                    for (int i = 0; i < torns2.size(); i++) {
                        Vertex vertex = torns2.get(i);
                        g.afegirAresta(v, vertex, /*1*/ Graf.INFINIT, 0);
                    }
                } else {
                    Vertex v1 = recorregut_restriccio((Restriccio) of1_and, idDoc, g);
                    Vertex v2 = recorregut_restriccio((Restriccio) of2_and, idDoc, g);
                    g.afegirAresta(v, v1, /*cv1*/ Graf.INFINIT, 0);
                    g.afegirAresta(v, v2, /*cv2*/ Graf.INFINIT, 0);
                }
                break;
            case "XOR":
                R_XOR xor = (R_XOR) r;
                Object of1_xor = xor.getFill1();
                Object of2_xor = xor.getFill2();
                if (of1_xor.getClass().equals(String.class) && of2_xor.getClass().equals(String.class)) {
                    String t1 = (String) of1_xor;
                    String t2 = (String) of2_xor;
                    ArrayList<Vertex> torns1 = consulta_torns_afectats(t1, r.getTipus(), g);
                    ArrayList<Vertex> torns2 = consulta_torns_afectats(t2, r.getTipus(), g);
                    for (int i = 0; i < torns1.size(); i++) {
                        Vertex vertex = torns1.get(i);
                        g.afegirAresta(v, vertex, /*1*/ Graf.INFINIT, 0);
                    }
                    for (int i = 0; i < torns2.size(); i++) {
                        Vertex vertex = torns2.get(i);
                        g.afegirAresta(v, vertex, /*1*/ Graf.INFINIT, 0);
                    }
                } else {
                    Vertex v1 = recorregut_restriccio((Restriccio) of1_xor, idDoc, g);
                    Vertex v2 = recorregut_restriccio((Restriccio) of2_xor, idDoc, g);
                    g.afegirAresta(v, v1, /*cv1*/ Graf.INFINIT, 0);
                    g.afegirAresta(v, v2, /*cv2*/ Graf.INFINIT, 0);
                }
                break;
            case "NOP":
                R_NOP nop = (R_NOP) r;
                String t = nop.getTorn();
                ArrayList<Vertex> torns = consulta_torns_afectats(t, r.getTipus(), g);
                for(int i=0; i<torns.size(); i++){
                    g.afegirAresta(v, torns.get(i), Graf.INFINIT, 0);
                }
                break;
        }
        return v;
    }

    private static ArrayList<Vertex> consulta_torns_afectats(String t, String tipus_r, Graf g) throws Error {
        ArrayList<Vertex> v_torns = new ArrayList<Vertex>();
        Plantilla plantilla = CtrlPlantilla.getPlantillaActual();
        Calendari c = plantilla.get_calendari_asoc();
        
        Dia[] any = c.getCalendari();
        
        switch (tipus_r) {
            case "D":
                String[] data = t.split("-");// separa per -
                int dia = Integer.parseInt(data[0]);
                int mes = Integer.parseInt(data[1]);
                int numDiesAnteriors = calculaDiesAnteriors(mes);

                for (int i = 0; i < 3; i++) {
                    Torn torn = null;
                    switch(i){
                        case 0:
                            torn = any[numDiesAnteriors + dia - 1].getTornMati();
                            break;
                        case 1:
                            torn = any[numDiesAnteriors + dia - 1].getTornTarda();
                            break;
                        case 2:
                            torn = any[numDiesAnteriors + dia - 1].getTornNit();
                            break;
                    }
                    if (torn != null) {
                        v_torns.add(g.getVertex(torn.toString(), Vertex.MAX));
                    }
                }
                break;
            case "H":
                int hora = Integer.parseInt(t);
                for (int i = 0; i < any.length; i++) {
                    for (int e = 0; e < 3; e++) {
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
                        if (torn != null && torn.getHora_inici() <= hora && torn.getHora_fi() > hora) {
                            v_torns.add(g.getVertex(torn.toString(), Vertex.MAX));
                       }
                    }
                }
                break;
            case "S":
                int numSetmana = Integer.parseInt(t);
                int diesAnteriors = (numSetmana - 1) * 7;
                for (int i = diesAnteriors; i < 7; i++) {
                    if (i < any.length) {
                        for (int e = 0; e < 3; e++) {
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
                            if (torn != null) {
                                v_torns.add(g.getVertex(torn.toString(), Vertex.MAX));
                            }
                        }
                    }
                }
                break;
            default:
                throw new Error("No es reconeix el tipus de la restricció");
        }
        return v_torns;
    }

    private static int calculaDiesAnteriors(int mes) {
        switch (mes) {
            case 2:
                return 31;
            case 3:
                return (31 + 28);
            case 4:
                return (31 * 2 + 28);
            case 5:
                return (31 * 2 + 30 + 28);
            case 6:
                return (31 * 3 + 30 + 28);
            case 7:
                return (31 * 3 + 30 * 2 + 28);
            case 8:
                return (31 * 4 + 30 * 2 + 28);
            case 9:
                return (31 * 4 + 30 * 3 + 28);
            case 10:
                return (31 * 5 + 30 * 3 + 28);
            case 11:
                return (31 * 5 + 31 * 4 + 28);
            case 12:
                return (31 * 6 + 31 * 4 + 28);
            default:
                return 0;
        }
    }
}
