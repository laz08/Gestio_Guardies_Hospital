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
        while(it.hasNext()) { // cream nodes de tipus doctor i els afagim al graf
            Doctor d = it.next();
            Vertex v = new Vertex(d.getdni(), Vertex.DOCTOR);
            Vertex font = g.getVertex("FONT", Vertex.FONT_POU);
            g.afegirVertex(v);
            g.afegirAresta(font, v, Graf.INFINIT, 0);
        }
    }

    private static void posaVertexTorn(Graf g) throws Error {
        Plantilla plantilla = CtrlPlantilla.getPlantillaActual();
        Dia[] dia = plantilla.get_calendari_asoc().getCalendari();
        for (int i = 0; i < dia.length; i++) { // cream nodes de tipus torn i els afagim al graf
            for(int t=0; t<3; t++){
            Torn torn = dia[i].getTorn_concret(t);
            if(torn != null){
                Vertex vmax = new Vertex(torn.toString(), Vertex.MAX);
                g.afegirVertex(vmax);
                Vertex v = new Vertex(torn.toString(), Vertex.TORN);
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
        while (it_doc.hasNext()){
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
                graf.eliminaAresta(arestes.get(e));
            }
        }
        //relacionam la font amb els doctors y el pou amb els torns
        Vertex pou = graf.getVertex("POU", Vertex.FONT_POU);
        Vertex font = graf.getVertex("FONT", Vertex.FONT_POU);
        Plantilla p = CtrlPlantilla.getPlantillaActual();
        //ArrayList<Doctor> llista_doc = p.getLlistaDoctors();
        Iterator<Doctor> it_doc = p.getLlistaDoctorsDNI().iterator();
        Dia[] any = p.get_calendari_asoc().getCalendari();
        //ArrayList<Torn> llistaTorns = CtrlCalendari.consultar_calendari(p.getNomPlantilla()).getTorns();
        for (int i = 0; i < graf.numV(); i++) {
            Vertex v = graf.getVertex(i);
            if (v.getClasse() == Vertex.DOCTOR) {
                graf.afegirAresta(font, v, Graf.INFINIT, 0);
            } else if (v.getClasse() == Vertex.TORN) {
                boolean trobat = false;
                int nDia = 0;
                int capacitat = 0;
                while (!trobat && nDia < any.length) {
                    int ntorn=0;
                    while(!trobat && ntorn < 3){
                        Torn torn = any[nDia].getTorn_concret(i);
                        if (torn != null && torn.toString().equals(v.getId())) {
                            capacitat = torn.getMin_num_doctors();
                            trobat = true;
                        }
                    }
                    nDia ++;
                }
                graf.afegirAresta(pou, v, capacitat, 0);
            } else if (v.getClasse() == Vertex.MAX) {
                ArrayList<String> doc_rel = v.getDoctorsRel();
                while(it_doc.hasNext()){
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
                Vertex vtorn = g.getVertex(v.getId(), Vertex.TORN);
                int arestes = v.getArestes().size(); // agafam les entrades que té
                g.afegirAresta(v, vtorn, numDoc - arestes, 0);
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
        Vertex v = new Vertex(r.toString(), Vertex.RESTRICCIO);
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
            case "NOT": // POSSIBLEMENT S'ELIMINI
                R_NOT not = (R_NOT) r;
                Object of_not = not.getFill();
                if (of_not.getClass().equals(String.class)) {
                    String t = (String) of_not;
                    ArrayList<Vertex> torns = consulta_torns_afectats(t, r.getTipus(), g);
                } else {
                    Vertex v1 = recorregut_restriccio((Restriccio) of_not, idDoc, g);
                }
                break;
            case "NOP":
                R_NOP nop = (R_NOP) r;
                String t = nop.getTorn();
                ArrayList<Vertex> torns = consulta_torns_afectats(t, r.getTipus(), g);
                break;
        }
        return v;
    }

    private static ArrayList<Vertex> consulta_torns_afectats(String t, String tipus_r, Graf g) throws Error {
        ArrayList<Vertex> v_torns = new ArrayList<Vertex>();
        Plantilla plantilla = CtrlPlantilla.getPlantillaActual();
        //String nomp = plantilla.getNomPlantilla();
        Dia[] any = plantilla.get_calendari_asoc().getCalendari();
        switch (tipus_r) {
            case "D":
                String[] data = t.split("-");// separa per -
                int dia = Integer.parseInt(data[0]);
                int mes = Integer.parseInt(data[1]);
                int numDiesAnteriors = 0;
                switch(mes){
                    case 2:
                        numDiesAnteriors =31;
                        break; 
                    case 3:
                        numDiesAnteriors = 31 + 28;
                        break; 
                    case 4:
                        numDiesAnteriors = 31*2 + 28;
                        break; 
                    case 5:
                        numDiesAnteriors = 31*2 + 30 + 28;
                        break; 
                    case 6:
                        numDiesAnteriors = 31*3 + 30 + 28;
                        break;
                    case 7:
                        numDiesAnteriors = 31*3 + 30*2 + 28;
                        break; 
                    case 8:
                        numDiesAnteriors = 31*4 + 30*2 + 28;
                        break;
                    case 9:
                        numDiesAnteriors = 31*4 + 30*3 + 28;
                        break; 
                    case 10:
                        numDiesAnteriors = 31*5 + 30*3 + 28;
                        break;
                    case 11:
                        numDiesAnteriors = 31*5 + 31*4 + 28;
                        break;
                    case 12:
                        numDiesAnteriors = 31*6 + 31*4 + 28;
                        break;
                }
                
                for (int i = 0; i < 3; i++) {
                    Torn torn = any[numDiesAnteriors+dia-1].getTorn_concret(i);
                    if (torn != null) v_torns.add(g.getVertex(torn.toString(), Vertex.MAX));
                }
                break;
            case "H":
                int hora = Integer.parseInt(t);
                for(int i=0; i< any.length; i++){
                    for(int e=0; e<3; e++){
                        Torn torn = any[i].getTorn_concret(e);
                        if(torn!=null && torn.getHora_inici()<=hora && torn.getHora_fi()>hora){
                            v_torns.add(g.getVertex(torn.toString(), Vertex.MAX));
                        }
                    }
                }
                break;
            case "S":
                int numSetmana = Integer.parseInt(t);
                int diesAnteriors = (numSetmana-1)*7;
                
                for (int i = diesAnteriors; i < 7; i++) {
                    if (i<any.length){
                        for(int e=0; e<3; e++){
                            Torn torn = any[i].getTorn_concret(e);
                            if(torn != null){
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
}
