/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;
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
            posaVertexDoctor(g,p);
            posaVertexTorn(g);
            posaRestriccions(p.getLlistaDoctors(), g);
            crear_arestes(g);
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
            ArrayList<Integer> llista_r = d.getRestriccions();
            Vertex vd = g.getVertex(d.getdni(), Vertex.DOCTOR);
            for (int e = 0; e < llista_r.size(); e++) {
                r = CtrlRestriccio.consulta_res(llista_r.get(e));
                Vertex vr = recorregut_restriccio(r, g);
                int cap_entrada;
                switch(r.getOp()){
                    case "AND":
                        cap_entrada = 2;
                        break;
                    case "XOR":
                        cap_entrada = 1;
                        break;
                    case "NOT":
                        cap_entrada = 1;
                        break;
                    case "NOP":
                        cap_entrada = 1;
                        break;
                    default:
                        throw new Error("No s'ha pogut detectar l'operació de la restricció");
                }
                g.afegirAresta(vd, vr, cap_entrada, 0);
            }
        }
    }

    private static Vertex recorregut_restriccio(Restriccio r, Graf g) throws Error {
        // NO SE COM FER-HO, ESTIC BLOQUEJAT //
        Vertex v = g.getVertex(r.toString(), Vertex.RESTRICCIO); // si el vertex ja existeix, el retornara, sino retorna null
        if(v == null){
            switch (r.getOp()) {
                case "AND":
                    R_AND and = (R_AND) r;
                    v = new Vertex(and.toString(), Vertex.RESTRICCIO);
                    if (!and.getFill1().getClass().equals(String.class)
                            && !and.getFill2().getClass().equals(String.class)) {
                        Vertex vf1 = recorregut_restriccio((Restriccio) and.getFill1(), g);
                        Vertex vf2 = recorregut_restriccio((Restriccio) and.getFill2(), g);
                        g.afegirVertex(v);
                        g.afegirAresta(v, vf2, 1, 0);
                        g.afegirAresta(v, vf2, 1, 0);
                    }
                    else{
                        String f1 = (String) and.getFill1();
                        switch(and.getTipus()){
                            case "D":
                                break;
                            case "S":
                                break;
                            case "H":
                                break;
                        }
                        String f2 = (String) and.getFill2();
                        
                    }
                    break;
                case "XOR":
                    R_XOR xor = (R_XOR) r;
                    v = new Vertex(xor.toString(), Vertex.RESTRICCIO);
                    if (!xor.getFill1().getClass().equals(String.class)
                            && !xor.getFill2().getClass().equals(String.class)) {
                        Vertex vf1 = recorregut_restriccio((Restriccio) xor.getFill1(), g);
                        Vertex vf2 = recorregut_restriccio((Restriccio) xor.getFill2(), g);
                        g.afegirVertex(v);
                        g.afegirAresta(v, vf2, 1, 0);
                        g.afegirAresta(v, vf2, 1, 0);
                    }
                    break;
                case "NOT":
                    R_NOT not = (R_NOT) r;
                    v = new Vertex(not.toString(), Vertex.RESTRICCIO);
                    if (!not.getFill().getClass().equals(String.class)) {
                        Vertex vf = recorregut_restriccio((Restriccio) not.getFill(), g);
                        g.afegirVertex(v);
                        g.afegirAresta(v, vf, 0, 0);
                    }
                    break;
                case "NOP":
                    R_NOP nop = (R_NOP) r;
                    v = new Vertex(nop.toString(), Vertex.RESTRICCIO);
                    g.afegirVertex(v);
                    break;
                default:
                    throw new Error("No s'ha pogut detectar l'opercació de la restricció");
            }
        }
        return v;
    }

    private static void crear_arestes(Graf g) {
        Plantilla p = CtrlPlantilla.getPlantillaActual();
        ArrayList<Doctor> llista_doc = p.getLlistaDoctors();
        for (int i = 0; i < llista_doc.size(); i++) {
            Doctor d = llista_doc.get(i);
            int v1 = cercaVertex(d.getdni(), Vertex.DOCTOR);
            ArrayList<Integer> llista_r = d.getRestriccions();
            for (int e = 0; i < llista_r.size(); i++) {
            }
        }
    }
}
