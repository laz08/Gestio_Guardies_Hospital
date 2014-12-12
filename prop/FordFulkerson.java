package prop;

import java.util.*;
import java.math.*;
import java.io.*;
import static prop.Algorisme.graf;

public class FordFulkerson extends Algorisme {

    @Override
    public void maxFlow() {
        graf.resetFlow();
        DFS(graf.getVertex("FONT", Vertex.FONT_POU));
    }

    private static boolean DFS(Vertex s) {
        boolean arriba_a_torn = false;
        switch (s.getClasse()) {
            case Vertex.DOCTOR:
                arriba_a_torn = seguent(s);
                break;
            case Vertex.RESTRICCIO:
                comprovaRestriccio(s);
                arriba_a_torn = seguent(s);
                if (!arriba_a_torn) {
                   redireccionaRestriccio(s);
                   arriba_a_torn = seguent(s);
                }
                break;
            case Vertex.MAX:
                arriba_a_torn = seguentMax(s);
                break;
        }
        return arriba_a_torn;
    }

    private static boolean seguent(Vertex s) {
        boolean arriba_a_torn = false;
        ArrayList<Integer> la = s.getArestes();
        for (int i = 0; i < la.size(); i++) {
            Aresta a = graf.getA(la.get(i));
            Vertex v = graf.getVertex(a.getw());
            if (!v.equals(s) && !v.getVisitat()) {
                a.addFlow(1);
                arriba_a_torn = DFS(v);
            }
        }
        return arriba_a_torn;
    }

    private static void comprovaRestriccio(Vertex s) {
        if (s.getId().contains("XOR")) { // si l'indentificador es de la classe XOR
            boolean canviat = false;
            int pos = 0;
            ArrayList<Integer> la = s.getArestes();
            while (!canviat && pos < la.size()) {
                Aresta a = graf.getA(la.get(pos));
                Vertex v1 = graf.getVertex(a.getv());
                if (v1.equals(s)) {
                    graf.getVertex(a.getw()).setVisitat(true);
                    canviat = true;
                }
                pos++;
            }
        }
    }
    
    public static void redireccionaRestriccio(Vertex s){
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

    private static boolean seguentMax(Vertex vm) {
        boolean arriba_a_torn = false;
        ArrayList<Integer> la = vm.getArestes();
        Vertex vt = null, vr = null;
        String doc = null;
        Aresta ar = null, at = null;
        for (int i = 0; i < la.size() && !vm.getVisitat(); i++) {
            Aresta a = graf.getA(la.get(i));
            Vertex v = graf.getVertex(a.getw());
            if (!v.equals(vm)) {
                ar = a;
                vr = graf.getVertex(a.getw());
                doc = vr.getDoctorsRel().get(0); // agafam el doctor al que fa referència la restricció
                a.addFlow(1);
                if (a.getflow() >= vm.getNumMaxRestr()) {
                    vm.setVisitat(true); // si ja s'han assignat el maxim de restriccions, es bloqueja el vertex
                }
            } else {
                vt = v; // es guarda el vertex torn relacionat amb el max
                at = a;
            }
        }

        if (!vt.getDoctorsRel().contains(doc)) {
            vt.addDoctorRel(doc);
            at.setCap(at.getcap() - 1);
            arriba_a_torn = true;
        } else { // si ja s'havia relacionat aquest doctor amb el torn s'elimina la relacio
            ar.addFlow(-1);
            vr.setVisitat(true);
        }
        return arriba_a_torn;
    }
}