/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import static prop.CtrlAlgorisme.graf;

/**
 *
 * @author Xisco
 */
public class Dijkstra extends Algorisme {

    private static ArrayList<Aresta> arestesPendents;

    public Dijkstra(boolean selSou) {
        super(selSou);
        preparaGraf();
    }

    @Override
    void maxFlow() throws Error {
        arestesPendents = posaDoctors();
        while (!arestesPendents.isEmpty() && !totsBloquejats()) {
            Aresta a = arestesPendents.get(0);
            arestesPendents.remove(0);
            a.addFlow(1);
            Vertex v = graf.getVertex(a.getw());
            if (v.getClasse() == Vertex.TORN) {
                if (v.getVisitat()) {
                    tornaEnrere(a);
                } else {
                    Vertex vp = graf.getVertex(a.getv());
                    v.addDoctorRel(vp.getDoctorsRel().get(0));
                    if (v.getNumDocRelacionats() == v.getNumMaxRestr()) {
                        v.setVisitat(true);
                    }
                }
            } else {
                ArrayList<Integer> la = v.getArestes();
                for (int i = 0; i < la.size(); i++) {
                    Aresta a1 = graf.getA(la.get(i));
                    Vertex v1 = graf.getVertex(a1.getw());
                    if (!v1.equals(v) && !v1.getVisitat()) {
                        if (v.getId().contains("XOR")) {
                            bloqueja_arestes(la, a1, v);
                        }
                        boolean posicionat = false;
                        int pos = 0;
                        while (!posicionat && pos < arestesPendents.size()) {
                            Aresta aresta = arestesPendents.get(pos);
                            if (aresta.getcap() > a1.getcap()) {
                                arestesPendents.add(pos, a1);
                                posicionat = true;
                            }
                            pos++;
                        }
                    }
                }
            }

        }
        Entrada.guarda_assignacions(graf);
    }

    private static ArrayList<Aresta> posaDoctors() {
        ArrayList<Aresta> arestes = new ArrayList<Aresta>();
        Vertex font = graf.getVertex("FONT", Vertex.FONT_POU);
        ArrayList<Integer> la = font.getArestes();
        for (int i = 0; i < la.size(); i++) {
            Aresta a = graf.getA(la.get(i));
            arestes.add(a);
        }
        if (selSou) {
            Collections.sort(arestes, new Comparator<Aresta>() {
                @Override
                public int compare(Aresta a1, Aresta a2) {
                    return Integer.compare(a1.getcap(), a2.getcap());
                }
            });
        }
        return arestes;
    }

    private static void tornaEnrere(Aresta a) {
        Vertex v = graf.getVertex(a.getv());
        boolean atura = false;
        while (!atura) {
            if (v.getClasse() == Vertex.FONT_POU || v.getId().contains("XOR")) {
                atura = true;
                if (v.getId().contains("XOR")) {
                    desbloqueja_arestes(v);
                }
            } else {
                a.addFlow(-1);
                ArrayList<Integer> arestes = v.getArestes();
                boolean trobat = false;
                int pos = 0;
                while (!trobat && pos < arestes.size()) {
                    Aresta a1 = graf.getA(arestes.get(pos));
                    Vertex v1 = graf.getVertex(a1.getv());
                    if (!v1.equals(v)) {
                        trobat = true;
                        v = v1;
                        a = a1;
                    }
                    pos++;
                }
            }
        }
    }

    private static void bloqueja_arestes(ArrayList<Integer> la, Aresta aresta, Vertex vertex) {
        for (int i = 0; i < la.size(); i++) {
            Aresta a = graf.getA(la.get(i));
            if (!a.equals(aresta)) {
                Vertex v = graf.getVertex(a.getw());
                if (!v.equals(vertex)) {
                    v.setVisitat(true);
                }
            }
        }
    }

    private static void desbloqueja_arestes(Vertex v) {
        ArrayList<Integer> arestes = v.getArestes();
        for (int i = 0; i < arestes.size(); i++) {
            Aresta a = graf.getA(arestes.get(i));
            Vertex v1 = graf.getVertex(a.getw());
            if (!v1.equals(v)) {
                v1.setVisitat(false);
            }
        }
    }

    private static boolean totsBloquejats() {
        boolean bloquejats = true;
        for (int i = 0; i < arestesPendents.size(); i++) {
            Vertex v = graf.getVertex(arestesPendents.get(i).getw());
            if (!v.getVisitat()) {
                bloquejats = false;
            } else {                                                               // si el vertex esta bloquejat es mou al final de la llista
                boolean colocat = false;
                for (int e = i; e < arestesPendents.size() && !colocat; e++) {      // si hi ha algun altre bloquejat el posam just abans
                    Vertex v1 = graf.getVertex(arestesPendents.get(e).getw());
                    if (v1.getVisitat()) {
                        colocat = true;
                        arestesPendents.add(e, arestesPendents.get(i));
                        arestesPendents.remove(i);
                    }
                }
                if (!colocat) {                                                   // si no hi ha cap altre bloquejat, es posa al final
                    arestesPendents.add(arestesPendents.get(i));
                    arestesPendents.remove(i);
                }
            }
        }
        return bloquejats;
    }

    private static void preparaGraf() {
        ArrayList<Integer> vertex = seleccionaTorns();
        while (!vertex.isEmpty()) {
            int nv = vertex.get(0);
            vertex.remove(0);
            Vertex v = graf.getVertex(nv);
            if (v.getClasse() != Vertex.FONT_POU) {
                int min_sortida = cercaArestaSortida(v);
                ArrayList<Integer> vertex_pare = marcaArestaEntrada(min_sortida, v);
                for (int i = 0; i < vertex_pare.size(); i++) {
                    vertex.add(vertex_pare.get(i));
                }
            }
        }
    }

    private static int cercaArestaSortida(Vertex v) {
        ArrayList<Integer> la = v.getArestes();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < la.size(); i++) {
            Aresta a = graf.getA(la.get(i));
            Vertex v1 = graf.getVertex(a.getw());
            if (!v1.equals(v) && min > a.getcap()) {
                min = a.getcap();
            }
        }
        return min;
    }

    private static ArrayList<Integer> marcaArestaEntrada(int min, Vertex v) {
        ArrayList<Integer> lvEntrada = new ArrayList<Integer>();
        if (!selSou) {
            ArrayList<Integer> la = v.getArestes();
            for (int i = 0; i < la.size(); i++) {
                Aresta a = graf.getA(la.get(i));
                Vertex v1 = graf.getVertex(a.getv());
                if (!v1.equals(v)) {
                    lvEntrada.add(a.getv());
                    a.setCap(min);
                }
            }
        } else {
            if (v.getClasse() == Vertex.TORN) {
                ArrayList<Integer> torns = seleccionaTorns();

                torns = ordena(torns, 0, torns.size() - 1, "torn");
                int posTorn = trobaTorn(v, torns);
                ArrayList<Integer> la = v.getArestes();
                for (int i = 0; i < la.size(); i++) {
                    Aresta a = graf.getA(la.get(i));
                    Vertex v1 = graf.getVertex(a.getv());
                    if (!v1.equals(v)) {
                        lvEntrada.add(a.getv());
                        a.setCap(posTorn);
                    }
                }
            } else if (v.getClasse() == Vertex.DOCTOR) {
                ArrayList<Integer> doctors = seleccionaDoctors();
                doctors = ordena(doctors, 0, doctors.size() - 1, "doc");
                int posDoc = trobaDoc(v, doctors);
                ArrayList<Integer> la = v.getArestes();
                for (int i = 0; i < la.size(); i++) {
                    Aresta a = graf.getA(la.get(i));
                    Vertex v1 = graf.getVertex(a.getv());
                    if (!v1.equals(v)) {
                        lvEntrada.add(a.getv());
                        a.setCap(posDoc);
                    }
                }
            } else {
                ArrayList<Integer> la = v.getArestes();
                for (int i = 0; i < la.size(); i++) {
                    Aresta a = graf.getA(la.get(i));
                    Vertex v1 = graf.getVertex(a.getv());
                    if (!v1.equals(v)) {
                        lvEntrada.add(a.getv());
                        a.setCap(min);
                    }
                }
            }

        }
        return lvEntrada;
    }

    private static ArrayList<Integer> seleccionaTorns() {
        ArrayList<Integer> torns = new ArrayList<Integer>();
        Vertex pou = graf.getVertex("POU", Vertex.FONT_POU);
        ArrayList<Integer> la = pou.getArestes();
        for (int i = 0; i < la.size(); i++) {
            Vertex v = graf.getVertex(graf.getA(la.get(i)).getv());
            if (v.getClasse() == Vertex.TORN) { // en teoria innecessaria pero per si acas...
                torns.add(graf.getA(la.get(i)).getv());
            }
        }
        return torns;
    }

    private static ArrayList<Integer> seleccionaDoctors() {
        ArrayList<Integer> doctors = new ArrayList<Integer>();
        Vertex font = graf.getVertex("FONT", Vertex.FONT_POU);
        ArrayList<Integer> la = font.getArestes();
        for (int i = 0; i < la.size(); i++) {
            Vertex v = graf.getVertex(graf.getA(la.get(i)).getw());
            if (v.getClasse() == Vertex.DOCTOR) { // en teoria innecessaria pero per si acas...
                doctors.add(graf.getA(la.get(i)).getw());
            }
        }
        return doctors;
    }

    private static int trobaTorn(Vertex torn, ArrayList<Integer> torns) {
        int posTorn = -1;
        int pos = 0;
        boolean trobat = false;
        while (!trobat && torns.size() > pos) {
            Vertex t = graf.getVertex(torns.get(pos));
            if (t.equals(torn)) {
                trobat = true;
                posTorn = pos;
            }
            pos++;
        }
        return posTorn;
    }

    private static int trobaDoc(Vertex doctor, ArrayList<Integer> doctors) {
        int posDoc = -1;
        int pos = 0;
        boolean trobat = false;
        while (!trobat && doctors.size() > pos) {
            Vertex t = graf.getVertex(doctors.get(pos));
            if (t.equals(doctor)) {
                trobat = true;
                posDoc = pos;
            }
            pos++;
        }
        return posDoc;
    }

    /**
     * QuckSort segons el sou dels doctors que es veuen a la llista de vertex
     *
     * @param llista Llista de vertex que fan referencia als doctors
     * @param inf linit inferior de la llista
     * @param sup limit superior de la llista
     * @return llista ordenada segons els limits superior i inferior
     */
    private static ArrayList<Integer> ordena(ArrayList<Integer> llista, int inf, int sup, String doc_torn) {
        int idx;
        if (doc_torn.equals("doc")) {
            idx = particioPerSou(llista, inf, sup);
        } else {
            idx = particioPerPercent(llista, inf, sup);
        }
        if (inf < idx) {
            llista = ordena(llista, inf, idx, doc_torn);
        }
        if (sup > idx && sup - inf >= 2) {
            llista = ordena(llista, idx, sup, doc_torn);
        }
        return llista;
    }

    /**
     * Divideix en 2 la llista que s'hi passa i les ordena de forma independent
     *
     * @param llista llista completa que es vol dividir i ordenar
     * @param inf limit inferior de la llista que es vol ordenar
     * @param sup limit superior de la llista que es vol ordenar
     * @return llista dividida en 2 i ordenada
     */
    private static int particioPerSou(ArrayList<Integer> llista, int inf, int sup) {
        int i = inf, j = sup;
        int tmp;
        int indx = (i + j) / 2;
        Vertex v = graf.getVertex(llista.get(indx));
        Doctor doc = (Doctor) v.getObjecte();
        int pivot = doc.getSou();
//        
        while (i < j) {
            v = graf.getVertex(llista.get(i));
            doc = (Doctor) v.getObjecte();
            int sou_i = doc.getSou();
            v = graf.getVertex(llista.get(j));
            doc = (Doctor) v.getObjecte();
            int sou_j = doc.getSou();
            if (sou_i >= pivot && pivot >= sou_j) {
                tmp = llista.get(i);
                llista.set(i, llista.get(j));
                llista.set(j, tmp);
                if (i < indx) {
                    i++;
                }
                if (j > indx) {
                    j--;
                }
            } else if (sou_i > pivot) {
                tmp = llista.get(i);
                llista.set(i, llista.get(indx));
                llista.set(indx, tmp);
                pivot = sou_i;
                if (i < indx) {
                    i++;
                }
            } else if (sou_j < pivot) {
                tmp = llista.get(j);
                llista.set(j, llista.get(indx));
                llista.set(indx, tmp);
                pivot = sou_j;
                if (j > indx) {
                    j--;
                }
            } else {
                if (i < indx) {
                    i++;
                }
                if (j > indx) {
                    j--;
                }
            }

        }
        return indx;
    }

    private static int particioPerPercent(ArrayList<Integer> llista, int inf, int sup) {
        int i = inf, j = sup;
        int tmp;
        int indx = (i + j) / 2;
        Vertex v = graf.getVertex(llista.get(indx));
        Torn torn = (Torn) v.getObjecte();
        float pivot = torn.getPercent_sou();
//        
        while (i < j) {
            v = graf.getVertex(llista.get(i));
            torn = (Torn) v.getObjecte();
            float percent_i = torn.getPercent_sou();
            v = graf.getVertex(llista.get(j));
            torn = (Torn) v.getObjecte();
            float percent_j = torn.getPercent_sou();
            if (percent_i >= pivot && pivot >= percent_j) {
                tmp = llista.get(i);
                llista.set(i, llista.get(j));
                llista.set(j, tmp);
                if (i < indx) {
                    i++;
                }
                if (j > indx) {
                    j--;
                }
            } else if (percent_i > pivot) {
                tmp = llista.get(i);
                llista.set(i, llista.get(indx));
                llista.set(indx, tmp);
                pivot = percent_i;
                if (i < indx) {
                    i++;
                }
            } else if (percent_j < pivot) {
                tmp = llista.get(j);
                llista.set(j, llista.get(indx));
                llista.set(indx, tmp);
                pivot = percent_j;
                if (j > indx) {
                    j--;
                }
            } else {
                if (i < indx) {
                    i++;
                }
                if (j > indx) {
                    j--;
                }
            }

        }
        return indx;
    }
}
