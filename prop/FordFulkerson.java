package prop;

import java.util.*;

import static prop.CtrlAlgorisme.graf;

public class FordFulkerson extends Algorisme {

    public FordFulkerson(boolean selSou) {
        super(selSou);
    }

    @Override
    public void maxFlow() throws Error {
        graf.resetFlow();
        Doc_Torn.removeAll();
        seguent(graf.getVertex("FONT", Vertex.FONT_POU), null);
        Entrada.guarda_assignacions(graf);
    }

    private boolean seguent(Vertex s, String doc_r_pare) {
        boolean arriba_a_torn = false;
        switch (s.getClasse()) {
            case Vertex.TORN:
                arriba_a_torn = tracta_torn(s, doc_r_pare);
                break;
            case Vertex.RESTRICCIO:
                arriba_a_torn = tracta_restriccio(s, doc_r_pare);
                break;
            case Vertex.DOCTOR:
                arriba_a_torn = tracta_doctor(s);
                break;
            case Vertex.FONT_POU:
                arriba_a_torn = tracta_font(s);
                break;
        }
        return arriba_a_torn;
    }

    /**
     * Quan li passam un Vertex torn, l'assigna o no segons el nombre minim de
     * doctors i en retorna un boolea
     *
     * @param torn Vertex que representa al torn
     * @param doctor_rel Possible doctor relacionat amb el vertex pare
     * @return Boolea que determina si s'ha assignat el torn o no
     */
    private boolean tracta_torn(Vertex vtorn, String doctor_rel) {
        boolean relacionat = false;
        if (!vtorn.getVisitat()) {
            Torn torn = (Torn) vtorn.getObjecte();
            if (torn.getMin_num_doctors() > vtorn.getNumDocRelacionats() + 1) {
                vtorn.addDoctorRel(doctor_rel);
                relacionat = true;
            } else if (torn.getMin_num_doctors() > vtorn.getNumDocRelacionats()) {
                vtorn.addDoctorRel(doctor_rel);
                vtorn.setVisitat(true);
                relacionat = true;
            }
        }
        return relacionat;
    }

    /**
     * Doant un node del tipus doctor, comprovara els seus fills i retornara
     * true si tots ells arriben a assignar-se a un torn
     *
     * @param vdoc Vertex que representa al doctor
     * @return Boolea que determina si tots els fills del doctor s'assignen a un
     * torn (true) o no(false)
     */
    private boolean tracta_doctor(Vertex vdoc) {
        boolean relacionat = true;
        ArrayList<Integer> llista_arestes = vdoc.getArestes();
        for (int i = 0; i < llista_arestes.size(); i++) {
            Aresta a = graf.getA(llista_arestes.get(i));
            Vertex fill = graf.getVertex(a.getw());
            if (!fill.equals(vdoc)) {
                boolean relacionat_particular = true;
                Doctor doc = (Doctor) vdoc.getObjecte();
                relacionat_particular = seguent(fill, doc.getdni());
                if (relacionat_particular) {
                    a.addFlow(1);
                }
                relacionat &= relacionat_particular;
            }
        }
        return relacionat;
    }

    /**
     * Donat un vertex font comprova si tots els seus fills s'assignen a un torn
     *
     * @param font Vertex font
     * @return Retorna true si s'han compit totes les restriccions de tots els
     * fills del vertex font, sino, retorna false
     */
    private boolean tracta_font(Vertex font) {
        boolean arriba = true;
        ArrayList<Integer> llista_arestes = font.getArestes();
        if (!selSou) {
            for (int i = 0; i < llista_arestes.size(); i++) {
                Aresta a = graf.getA(llista_arestes.get(i));
                Vertex fill = graf.getVertex(a.getw());
                if (!fill.equals(font)) {
                    boolean arriba_particular = true;
                    arriba_particular = seguent(fill, null);
                    if (arriba_particular) {
                        a.addFlow(1);
                    }
                    arriba &= arriba_particular;
                }
            }
        } else {
            ArrayList<Integer> fills = new ArrayList<Integer>();
            for (int i = 0; i < llista_arestes.size(); i++) {
                Aresta a = graf.getA(llista_arestes.get(i));
                Vertex fill = graf.getVertex(a.getw());
                if (!fill.equals(font)) {
                    fills.add(a.getw());
                }
            }
            fills = ordenacioPerSou(fills, 0, fills.size() - 1);
            for (int i = 0; i < fills.size(); i++) {
                boolean arriba_particular = true;
                Vertex fill = graf.getVertex(fills.get(i));
                arriba_particular = seguent(fill, null);
                if (arriba_particular) {
                    ArrayList<Integer> arestes = fill.getArestes();
                    for (int e = 0; e < arestes.size(); e++) {
                        Aresta a = graf.getA(arestes.get(e));
                        Vertex pare = graf.getVertex(a.getv());
                        if (pare.equals(font)) {
                            a.addFlow(1);
                        }
                    }
                }
                arriba &= arriba_particular;
            }
        }
        return arriba;
    }

    private boolean tracta_restriccio(Vertex vr, String doctor) {
        Restriccio restriccio = (Restriccio) vr.getObjecte();
        boolean arriba = true;
        switch (restriccio.getOp()) {
            case "XOR":
                arriba = tracta_xor(vr, doctor);
                break;
            case "AND":
                arriba = tracta_and(vr, doctor);
                break;
            case "NOP":
                arriba = tracta_nop(vr, doctor);
                break;
        }
        return arriba;
    }

    /**
     * Donat un vertex que representa una restricció te tipus NOP retorna un
     * boolea que marca si s'ha pogut assignar un torn a tots els seus fills
     *
     * @param vnop Vertex que representa la retricció NOP
     * @param doctor Doctor relacionat amb el vertex
     * @return Boolea que marca si s'ha pogut relacionar un torn amb els seus
     * fills o no
     */
    private boolean tracta_nop(Vertex vnop, String doctor) {
        ArrayList<Integer> llista_arestes = vnop.getArestes();
        Restriccio restriccio = (Restriccio) vnop.getObjecte();
        boolean arriba;
        if(restriccio.getTipus().equals("D"))arriba = true;
        else arriba = false;
        for (int i = 0; i < llista_arestes.size(); i++) {
            Aresta a = graf.getA(llista_arestes.get(i));
            Vertex fill = graf.getVertex(a.getw());
            if (!fill.equals(vnop)) {
                boolean arriba_particular = true;
                arriba_particular = seguent(fill, doctor);
                if (arriba_particular) {
                    a.addFlow(1);
                }
                if(restriccio.getTipus().equals("D"))arriba &= arriba_particular;
                else arriba |= arriba_particular;
            }
        }
        return arriba;
    }

    /**
     * Donat un vèrtex excollira el primer fill al que es pugui asignar un torn
     * o dia complet en cas que existeixi
     *
     * @param vxor Vèrtex que representa la restriccio XOR
     * @param doctor Doctor relacionat amb el vèrtex
     * @return Retorna true si ha trobat alguna assignacio amb un dia o torn
     * complet, en cas contrari retorna false
     */
    private boolean tracta_xor(Vertex vxor, String doctor) {
        boolean arriba = false;
        ArrayList<Integer> llista_arestes = vxor.getArestes();
        for (int i = 0; i < llista_arestes.size() && !arriba; i++) {
            Aresta a = graf.getA(llista_arestes.get(i));
            Vertex fill = graf.getVertex(a.getw());
            if (!fill.equals(vxor)) {
                arriba = seguent(fill, doctor);
                if (!arriba) {
                    elimina_relacionats(fill, doctor);
                }
            }
        }
        return arriba;
    }

    /**
     * Donat un vèrtex escollira tots els fills als que es puguin assignar un
     * torn o dia complets
     *
     * @param vand Vèrtex que representa la restriccio AND
     * @param doctor Doctor relacionat amb el vèrtex
     * @return Retorna true si tots s'han pogut assignar, si algun no ho ha
     * pogut fer, retorna false
     */
    private boolean tracta_and(Vertex vand, String doctor) {
        boolean arriba = true;
        ArrayList<Integer> llista_arestes = vand.getArestes();
        for (int i = 0; i < llista_arestes.size(); i++) {
            Aresta a = graf.getA(llista_arestes.get(i));
            Vertex fill = graf.getVertex(a.getw());
            if (!fill.equals(vand)) {
                boolean arriba_particular = true;
                arriba_particular &= seguent(fill, doctor);
                if (!arriba_particular) {
                    elimina_relacionats(fill, doctor);
                }
                arriba &= arriba_particular;
            }
        }
        return arriba;
    }

    /**
     * Donat un vertex, elimina la relació amb tots els seus fills
     *
     * @param vertex Vertex del que es volen eliminar les relacions amb els
     * fills
     * @param doc Doctor relacionat amb el vertexs
     */
    private void elimina_relacionats(Vertex vertex, String doc) {
        ArrayList<Integer> llista_arestes = vertex.getArestes();
        for (int i = 0; i < llista_arestes.size(); i++) {
            Aresta a = graf.getA(llista_arestes.get(i));
            Vertex fill = graf.getVertex(a.getw());
            if (!fill.equals(vertex) && a.getflow() > 0) {
                a.addFlow(-1);
                fill.rmDoctorRel(doc);
            }
        }
    }
    ////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////

   
    /**
     * QuckSort segons el sou dels doctors que es veuen a la llista de vertex
     *
     * @param llista Llista de vertex que fan referencia als doctors
     * @param inf linit inferior de la llista
     * @param sup limit superior de la llista
     * @return llista ordenada segons els limits superior i inferior
     */
    private static ArrayList<Integer> ordenacioPerSou(ArrayList<Integer> llista, int inf, int sup) {
        int idx = particio(llista, inf, sup);

        if (inf < idx && sup - inf >= 2) {
            llista = ordenacioPerSou(llista, inf, idx);
        }
        if (sup > idx && sup - inf >= 2) {
            llista = ordenacioPerSou(llista, idx, sup);
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
    private static int particio(ArrayList<Integer> llista, int inf, int sup) {
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
}
