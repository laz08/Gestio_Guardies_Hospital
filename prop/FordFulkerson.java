package prop;

import java.util.*;
import java.math.*;
import java.io.*;
import static prop.Algorisme.graf;

public class FordFulkerson extends Algorisme {

    @Override
    public void maxFlow() throws Error {
        graf.resetFlow();
        seguent(graf.getVertex("FONT", Vertex.FONT_POU), null);
    }

    private static boolean seguent(Vertex s, String doc_r_pare) throws Error {
        boolean arriba_a_torn = false;
        if (s.getClasse() != Vertex.MAX) {
            ArrayList<Integer> la = s.getArestes();
            for (int i = 0; i < la.size() && !s.getVisitat(); i++) {
                if (s.getId().contains("XOR")) { // si es un XOR
                    Aresta a = graf.getA(la.get(i));
                    if (graf.getVertex(a.getv()).equals(s)) { // comprovam per els fills
                        Vertex vf = graf.getVertex(a.getw());
                        if (vf.getClasse() != Vertex.MAX) { // si el fill no son MAX (son restriccions)
                            Vertex w = graf.getVertex(a.getw()); // agafam un fill
                            arriba_a_torn = seguent(w, s.getDoctorsRel().get(0)); // comprovam el seguent 
                            if (arriba_a_torn) { // si arriba el torn afagim flow y bloquejam el vertex
                                a.addFlow(1);
                                s.setVisitat(true);
                            }
                        } else { // si el fill es max
                            Vertex w = graf.getVertex(a.getw()); //agafam el fill del vertex
                            arriba_a_torn = seguent(w, s.getDoctorsRel().get(0));
                            if (arriba_a_torn) {
                                Torn t = (Torn) graf.getVertex(w.getId(), Vertex.TORN).getObjecte();
                                for (int e = 0; e < la.size(); e++) { // per tots els fills 
                                    Aresta aresta = graf.getA(la.get(e));
                                    Vertex vf2 = graf.getVertex(aresta.getw());
                                    if (!vf2.equals(s)) {
                                        Torn tf = (Torn) graf.getVertex(vf2.getId(), Vertex.TORN).getObjecte();
                                        String op = ((Restriccio) s.getObjecte()).getTipus();
                                        switch (op) { // si compleixen les visitam
                                            case "D":
                                                if (t.getPosicio() == tf.getPosicio()) {
                                                    arriba_a_torn &= seguent(vf2, s.getDoctorsRel().get(0));
                                                    if (arriba_a_torn) { // si arriben a un torn, afagim flow
                                                        aresta.addFlow(1);
                                                    }
                                                }
                                                break;
                                            case "H":

                                                System.out.println(s.getId());


                                                if (t.getHora_inici() == tf.getHora_inici()) {
                                                    arriba_a_torn &= seguent(vf2, s.getDoctorsRel().get(0));
                                                    if (arriba_a_torn) {
                                                        aresta.addFlow(1);
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                }

                                if (arriba_a_torn) {
                                    s.setVisitat(true);
                                } else {
                                    desfesFillsVisitats(s);
                                }
                            }
                        }
                    }
                } else if (s.getId().contains("AND")) {
                    Aresta a = graf.getA(la.get(i));// per cada aresta filla de s
                    arriba_a_torn = true;
                    if (!graf.getVertex(a.getw()).equals(s)) {
                        arriba_a_torn &= seguent(graf.getVertex(a.getw()), s.getDoctorsRel().get(0));
                        if (arriba_a_torn) {
                            a.addFlow(1);
                        }
                    }
                    if (arriba_a_torn && i == la.size() - 1) {
                        s.setVisitat(true);
                    } else if (!arriba_a_torn) {
                        desfesFillsVisitats(s);
                        s.setVisitat(true);
                        //throw new Error("El graf no te una solució que compleixi totes les restriccions");
                    }
                } else {
                    Aresta af = graf.getA(la.get(i));
                    Vertex vf = graf.getVertex(af.getw());
                    if (!vf.equals(s)) {
                        if (s.getClasse() == Vertex.RESTRICCIO) {
                            arriba_a_torn = seguent(vf, doc_r_pare);
                        } else if (s.getClasse() == Vertex.DOCTOR){
                            arriba_a_torn = seguent(vf, s.getId());
                        }else{
                            arriba_a_torn = seguent(vf, null);
                        }
                    }
                    if (arriba_a_torn) {
                        af.addFlow(1);
                    }
                }
            }
        } else { // si es MAX retornam arriba a torn => true
            ArrayList<Integer> la = s.getArestes();
            arriba_a_torn = false;
            for (int i = 0; i < la.size(); i++) {
                Aresta a = graf.getA(la.get(i));
                Vertex v = graf.getVertex(a.getw());
                if (!v.equals(s)) {
                    if (!v.getDoctorsRel().contains(doc_r_pare)) {
                        v.getDoctorsRel().add(doc_r_pare);
                        a.setCap(a.getcap() - 1);
                        arriba_a_torn = true;
                    }
                }
                arriba_a_torn = true;
            }
        }
        return arriba_a_torn;
    }

    private static void desfesFillsVisitats(Vertex s) {
        ArrayList<Integer> la = s.getArestes();
        for (int i = 0; i < la.size(); i++) {
            Aresta a = graf.getA(la.get(i));
            if (a.getflow() > 0 && graf.getVertex(a.getv()).equals(s)) {
                a.setflow(0);
            }
        }
    }

    public static void redireccionaRestriccio(Vertex s) {
        if (s.getId().contains("XOR")) { // si l'indentificador es de la classe XOR
            boolean canviat = false;
            int pos = 0;
            ArrayList<Integer> la = s.getArestes();
            while (!canviat && pos < la.size()) {
                Aresta a = graf.getA(la.get(pos));
                Vertex v1 = graf.getVertex(a.getv());
                if (v1.equals(s)) {
                    Vertex v = graf.getVertex(a.getw());
                    v.setVisitat(!v.getVisitat()); // canviam el cami
                }
                pos++;
            }
        }
    }
}