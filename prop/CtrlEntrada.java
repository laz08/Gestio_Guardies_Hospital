/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
            posaRestriccions(p.getLlistaDoctors(), g);
        } catch (Error e) {
            System.err.println("Hi ha hagut problemes a l'hora de crear el graf" + e);
        }
        return g;
    }

    private static void posaVertexDoctor(Graf g, Plantilla p) throws Error {
        int ndoctors = p.getLlistaDoctors().size();
        for (int i = 0; i < ndoctors; i++) { // cream nodes de tipus doctor i els afagim al graf
            Doctor d = p.getLlistaDoctors().get(i);
            Vertex v = new Vertex(d.getdni(), Vertex.DOCTOR);
            Vertex font = g.getVertex("FONT", Vertex.FONT_POU);
            g.afegirVertex(v);
            g.afegirAresta(font, v, Graf.INFINIT, 0);
        }
    }

    private static void posaVertexTorn(Graf g) throws Error {
        int num = CtrlPlantilla.getidPlantillaActual();
        ArrayList<Torn> llistaTorns = Cjt_calendaris.consultar_calendari(num).getTorns();
        int ntorns = llistaTorns.size();
        for (int i = 0; i < ntorns; i++) { // cream nodes de tipus torn i els afagim al graf
            Torn t = llistaTorns.get(i);
            Vertex v = new Vertex(t.toString(), Vertex.TORN);
            Vertex pou = g.getVertex("POU", Vertex.FONT_POU);
            g.afegirVertex(v);
            g.afegirAresta(v, pou, t.getN_min_doc(), 0);
        }
    }

    private static void posaRestriccions(ArrayList<Doctor> doc, Graf g) throws Error {
        Doctor d;
        Restriccio r;
        for (int i = 0; i < doc.size(); i++) {
            d = doc.get(i);
            ArrayList<Vertex> v_torn = new ArrayList<Vertex>(); // guarda els vertex consultats per restriccions
            ArrayList<Integer> llista_r = d.getRestriccions();
            Vertex vd = g.getVertex(d.getdni(), Vertex.DOCTOR);
            for (int e = 0; e < llista_r.size(); e++) {
                r = CtrlRestriccio.consulta_res(llista_r.get(e));
                Vertex vr = recorregut_restriccio(r, g, v_torn);
                g.afegirAresta(vd, vr, vr.getCapacitatAcumulada(), 0);
            }
            int num = CtrlPlantilla.getidPlantillaActual();
            ArrayList<Torn> llistaTorns = Cjt_calendaris.consultar_calendari(num).getTorns();
            for (int x=0; x<llistaTorns.size(); x++){
                Torn t = llistaTorns.get(x);
                boolean trobat = false;
                for(int y=0; y<v_torn.size(); y++){
                    if (v_torn.get(y).getId().equals(t.toString()))trobat=true;
                }
                if(!trobat){
                    Vertex v = g.getVertex(t.toString(), Vertex.TORN);
                    g.afegirAresta(vd, v, 1, 0);
                }
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
    private static Vertex recorregut_restriccio(Restriccio r, Graf g, ArrayList<Vertex> vertex_torn) throws Error {
        Vertex v = new Vertex(r.toString(), Vertex.RESTRICCIO);
        g.afegirVertex(v);
        switch (r.getOp()) {
            case "AND":
                R_AND and = (R_AND) r;
                Object of1_and = and.getFill1();
                Object of2_and = and.getFill2();
                if (of1_and.getClass().equals(String.class) && of2_and.getClass().equals(String.class)) {
                    String t1 = (String) of1_and;
                    String t2 = (String) of2_and;
                    ArrayList<Vertex> torns1 = consulta_torns_afectats(t1, r.getTipus(), g);
                    ArrayList<Vertex> torns2 = consulta_torns_afectats(t2, r.getTipus(), g);
                    int capacitat = 0;
                    for (int i = 0; i < torns1.size(); i++) {
                        Vertex vertex = torns1.get(i);
                        vertex_torn.add(vertex);
                        g.afegirAresta(v, vertex, 1, 0);
                        capacitat++;
                    }
                    for (int i = 0; i < torns2.size(); i++) {
                        Vertex vertex = torns2.get(i);
                        vertex_torn.add(vertex);
                        g.afegirAresta(v, vertex, 1, 0);
                        capacitat++;
                    }
                    v.setCapacitatAcumulada(capacitat);
                } else {
                    Vertex v1 = recorregut_restriccio((Restriccio) of1_and, g, vertex_torn);
                    Vertex v2 = recorregut_restriccio((Restriccio) of2_and, g, vertex_torn);
                    int cv1 = v1.getCapacitatAcumulada();
                    int cv2 = v2.getCapacitatAcumulada();
                    v.setCapacitatAcumulada(cv1 + cv2);
                    g.afegirAresta(v, v1, cv1, 0);
                    g.afegirAresta(v, v2, cv2, 0);
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
                    int capacitat1 = 0, capacitat2 = 0;
                    for (int i = 0; i < torns1.size(); i++) {
                        Vertex vertex = torns1.get(i);
                        vertex_torn.add(vertex);
                        g.afegirAresta(v, vertex, 1, 0);
                        capacitat1++;
                    }
                    for (int i = 0; i < torns2.size(); i++) {
                        Vertex vertex = torns2.get(i);
                        vertex_torn.add(vertex);
                        g.afegirAresta(v, vertex, 1, 0);
                        capacitat2++;
                    }
                    
                    if (capacitat1>capacitat2) v.setCapacitatAcumulada(capacitat1);
                    else v.setCapacitatAcumulada(capacitat2);
                } else {
                    Vertex v1 = recorregut_restriccio((Restriccio) of1_xor, g, vertex_torn);
                    Vertex v2 = recorregut_restriccio((Restriccio) of2_xor, g, vertex_torn);
                    int cv1 = v1.getCapacitatAcumulada();
                    int cv2 = v2.getCapacitatAcumulada();
                    int cap = 0;
                    if (cv1 > cv2) {
                        cap = cv1;
                    } else {
                        cap = cv2;
                    }
                    v.setCapacitatAcumulada(cv1 + cv2);
                    g.afegirAresta(v, v1, cv1, 0);
                    g.afegirAresta(v, v2, cv2, 0);
                }
                break;
            case "NOT":
                R_NOT not = (R_NOT) r;
                Object of_not = not.getFill();
                if (of_not.getClass().equals(String.class)) {
                    String t = (String) of_not;
                    ArrayList<Vertex> torns = consulta_torns_afectats(t, r.getTipus(),g);
                    int capacitat = 0;
                    for (int i = 0; i < torns.size(); i++) {
                        Vertex vertex = torns.get(i);
                        vertex_torn.add(vertex);
                        g.afegirAresta(v, vertex, 0, 0);
                        capacitat++;
                    }
                    v.setCapacitatAcumulada(capacitat);
                    
                } else {
                    Vertex v1 = recorregut_restriccio((Restriccio) of_not, g, vertex_torn);
                    v.setCapacitatAcumulada(1);
                    g.afegirAresta(v, v1, 0, 0);
                }
                break;
            case "NOP":
                R_NOP nop = (R_NOP) r;
                String t = nop.getTorn();
                ArrayList<Vertex> torns = consulta_torns_afectats(t, r.getTipus(), g);
                int capacitat = 0;
                for (int i = 0; i < torns.size(); i++) {
                    Vertex vertex = torns.get(i);
                    vertex_torn.add(vertex);
                    g.afegirAresta(v, vertex, 1, 0);
                    capacitat++;
                }
                v.setCapacitatAcumulada(capacitat);

                break;
        }
        return v;
    }
    
    private static ArrayList<Vertex> consulta_torns_afectats(String t, String tipus_r, Graf g) throws Error{
        ArrayList<Vertex> v_torns = new ArrayList<Vertex>();
        int num = CtrlPlantilla.getidPlantillaActual();
        ArrayList<Torn> llistaTorns = Cjt_calendaris.consultar_calendari(num).getTorns();
        switch (tipus_r){
            case "D":
                String [] data = t.split("-");// separa per -
                int dia = Integer.parseInt(data[0]);
                int mes = Integer.parseInt(data[1]);
                for(int i = 0; i < llistaTorns.size(); i++){
                    Torn torn = llistaTorns.get(i);
                    GregorianCalendar d_i = torn.getData_inici();
                    GregorianCalendar d_f = torn.getData_fi();
                    if(d_i.get(Calendar.MONTH) <= mes && d_f.get(Calendar.MONTH) >= mes 
                            && d_i.get(Calendar.DAY_OF_MONTH)<=dia && d_f.get(Calendar.DAY_OF_MONTH)>=dia){
                        v_torns.add(g.getVertex(torn.toString(), Vertex.TORN));
                    }
                }
                break; 
            case "H":
                String[] h = t.split(":");
                int hora = Integer.parseInt(h[0]);
                int min = Integer.parseInt(h[1]);
                int sec = Integer.parseInt(h[2]);
                for(int i = 0; i < llistaTorns.size(); i++){
                    Torn torn = llistaTorns.get(i);
                    GregorianCalendar d_i = torn.getData_inici();
                    GregorianCalendar d_f = torn.getData_fi();
                    if(d_i.get(Calendar.HOUR_OF_DAY) <= hora && d_f.get(Calendar.HOUR_OF_DAY) >= hora 
                            && d_i.get(Calendar.MINUTE)<=min && d_f.get(Calendar.MINUTE)>=min
                            && d_i.get(Calendar.SECOND)<=sec && d_f.get(Calendar.SECOND)>=sec){
                        v_torns.add(g.getVertex(torn.toString(), Vertex.TORN));
                    }
                }
                break;
            case "S":
                int numSetmana = Integer.parseInt(t);
                for(int i = 0; i < llistaTorns.size(); i++){
                    Torn torn = llistaTorns.get(i);
                    GregorianCalendar d_i = torn.getData_inici();
                    GregorianCalendar d_f = torn.getData_fi();
                    if(d_i.get(Calendar.WEEK_OF_YEAR) <= numSetmana && d_f.get(Calendar.WEEK_OF_YEAR) >= numSetmana){
                        v_torns.add(g.getVertex(torn.toString(), Vertex.TORN));
                    }
                }
                break;
            default:
                throw new Error("No es reconeix el tipus de la restricció"); 
        }
        return v_torns;
    }
    
}
