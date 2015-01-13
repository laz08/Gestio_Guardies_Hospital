package prop;

import java.util.*;

import static prop.CtrlAlgorisme.graf;

public class FordFulkerson extends Algorisme {

    public FordFulkerson(boolean selSou) {
        super(selSou);
    }

    @Override
    public void maxFlow() throws Error {
        
        
        
        System.out.println("Max flow Ford");
        
        graf.resetFlow();
        Doc_Torn.removeAll();
        seguent(graf.getVertex("FONT", Vertex.FONT_POU), null);
        Entrada.guarda_assignacions(graf);
    }

    private boolean seguent(Vertex s, String doc_r_pare) throws Error {
        boolean arriba_a_torn = false;
        if (s.getClasse() != Vertex.TORN) {                                     //si no es un torn
            ArrayList<Integer> la = s.getArestes();                                 //agafam el conjunt d'arrestes que surt del vertex
            if (s.getClasse() == Vertex.FONT_POU && selSou) {                        //si s'ha de tenir en compte el sou
                ArrayList<Integer> lv = new ArrayList<Integer>();
                for (int i = 0; i < la.size(); i++) {
                    lv.add(graf.getA(la.get(i)).getw());
                }
                lv = ordenacioPerSou(lv, 0, lv.size() - 1);                               //ordenam la llista segons els sous
                for (int i = 0; i < lv.size(); i++) {
                    int vertex = lv.get(i);
                    boolean trobat = false;
                    for (int e = i + 1; e < la.size() && !trobat; e++) {
                        if (vertex == graf.getA(la.get(e)).getw()) {
                            int tmp = la.get(i);
                            la.set(i, la.get(e));
                            la.set(e, tmp);
                            trobat = true;
                        }
                    }
                }
            }                                                                       //
            for (int i = 0; i < la.size() && !s.getVisitat(); i++) {                //per cada una de elles comprovam 
                if (s.getId().contains("XOR")) {                                        //si es un XOR
                    Aresta a;
                    if (selSou) {                                                           //si s'ha de calcular segons el sou
                        ArrayList<Float> valors = new ArrayList<Float>();
                        for (int e = 0; e < la.size(); e++) {                                   //per cada aresta que surt del pertex
                            Vertex vf = graf.getVertex(graf.getA(la.get(e)).getw());                //comprovam un vertex fill
                            if (!vf.equals(s)) {                                                    //si son fills del vertex actual
                                valors.add(consultaPercent(vf));                                        //enmagatzema el persentatge del fill
                            } //
                            else {                                                                   //si no son fills del vertex actual
                                valors.add(Float.MAX_VALUE);                                            //marcam el valor com a maxim
                            }                                                                       //
                        }
                        int posMin = valors.indexOf(trobaMinim(valors));                            //agafam el valor minim de valors i en treim l'index
                        a = graf.getA(la.get(posMin));                                              //consultam l'aresta que correspon a aquest inkdex
                    } else {                                                                // sino
                        a = graf.getA(la.get(i));                                               //agafam l'aresta
                    }
                    if (graf.getVertex(a.getv()).equals(s)) {                               //si l'aresta "surt" del vertex
                        Vertex vf = graf.getVertex(a.getw());                                   //seleccionam el vertex fill
                        if (vf.getClasse() != Vertex.TORN) {                                    //si el fill no es un TORN (es una restriccio)
                            arriba_a_torn = seguent(vf, s.getDoctorsRel().get(0));                  //comprovam el seguent vertex 
                            if (arriba_a_torn) {                                                    //si arriba el torn 
                                a.addFlow(1);                                                           //augmentam el flow en 1
                                s.setVisitat(true);                                                     //bloquejam el vertex perque es un XOR
                            }                                                                       //
                        } else {                                                                //si el fill es un TORN
                            arriba_a_torn = seguent(vf, s.getDoctorsRel().get(0));                  //comporvam que es pugui accedir a aquest torn (no esta ple)
                            if (arriba_a_torn) {                                                    //si es pot accedir a aquest torn
                                Torn t = (Torn) graf.getVertex(vf.getId(), Vertex.TORN).getObjecte();   //seleccionam l'objecte torn que representa el vertex
                                for (int e = 0; e < la.size(); e++) {                                   //per totes les arestes que surten del vertex actual
                                    Aresta aresta = graf.getA(la.get(e));
                                    Vertex vf2 = graf.getVertex(aresta.getw());
                                    if (!vf2.equals(s)) {                                                   // si l'aresta "surt del vertex"
                                        Torn tf = (Torn) graf.getVertex(vf2.getId(), Vertex.TORN).getObjecte(); //agafam el fill del vertex
                                        String op = ((Restriccio) s.getObjecte()).getTipus();                   //agafam el tips de restriccio (Dia o Hora)
                                        switch (op) {
                                            case "D":                                                           //en el cas de dia
                                                if (t.getPosicio() == tf.getPosicio()) {                            //si la posicio del primer torn visitat i la del segon coincideixen (es el mateix dia)
                                                    arriba_a_torn &= seguent(vf2, s.getDoctorsRel().get(0));            //comprovam que es pugui associar 
                                                    if (arriba_a_torn) {                                                //si fins al moment tots han arribat al torn
                                                        aresta.addFlow(1);                                                  //agafim 1 al flow de l'aresta actual
                                                    }
                                                }
                                                break;
                                            case "H":                                                           //en el cas de hores
                                                if (t.getHora_inici() == tf.getHora_inici()) {                      //si la hora d'inici del torn coincideix
                                                    arriba_a_torn &= seguent(vf2, s.getDoctorsRel().get(0));            //comprovam que es pugui associar
                                                    if (arriba_a_torn) {                                                // si fins al moment tots han arribat al torn
                                                        aresta.addFlow(1);                                                  //agafim 1 al flow de l'aresta acual
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                }

                                if (arriba_a_torn) {                                                // si totes les arestes comprovades han arriabt al torn         
                                    s.setVisitat(true);                                                 // marcam el vertex vom a visitat
                                } else {                                                            // sino
                                    desfesFillsVisitats(s);                                             //eliminam tot el flow que s'ha afagit
                                }
                            }
                        }
                    }
                } else if (s.getId().contains("AND")) {                                 // si es un AND
                    Aresta a = graf.getA(la.get(i));                                        //agafam l'aresta
                    if (!graf.getVertex(a.getw()).equals(s)) {                              //si l'aresta "surt" del vertex
                        Vertex vf = graf.getVertex(a.getw());                                   //seleccionam el vertex fill
                        if (vf.getClasse() != Vertex.TORN) {                                    //si el fill no es un TORN (es una restriccio)
                            arriba_a_torn &= seguent(vf, s.getDoctorsRel().get(0));                  //comprovam el seguent vertex 
                            if (arriba_a_torn) {                                                    //si arriba el torn 
                                a.addFlow(1);                                                           //bloquejam el vertex perque es un XOR
                            }
                        } else {                                                                //si el fill es un TORN
                            arriba_a_torn = seguent(vf, s.getDoctorsRel().get(0));                  //comporvam que es pugui accedir a aquest torn (no esta ple)
                            if (arriba_a_torn) {                                                    //si es pot accedir a aquest torn
                                Torn t = (Torn) graf.getVertex(vf.getId(), Vertex.TORN).getObjecte();   //seleccionam l'objecte torn que representa el vertex
                                for (int e = 0; e < la.size(); e++) {                                   //per totes les arestes que surten del vertex actual
                                    Aresta aresta = graf.getA(la.get(e));
                                    Vertex vf2 = graf.getVertex(aresta.getw());
                                    if (!vf2.equals(s)) {                                                   // si l'aresta "surt del vertex"
                                        Torn tf = (Torn) graf.getVertex(vf2.getId(), Vertex.TORN).getObjecte(); //agafam el fill del vertex
                                        String op = ((Restriccio) s.getObjecte()).getTipus();                   //agafam el tips de restriccio (Dia o Hora)
                                        switch (op) {
                                            case "D":                                                           //en el cas de dia
                                                if (t.getPosicio() == tf.getPosicio()) {                            //si la posicio del primer torn visitat i la del segon coincideixen (es el mateix dia)
                                                    arriba_a_torn &= seguent(vf2, s.getDoctorsRel().get(0));            //comprovam que es pugui associar 
                                                    if (arriba_a_torn) {                                                //si fins al moment tots han arribat al torn
                                                        aresta.addFlow(1);                                                  //agafim 1 al flow de l'aresta actual
                                                    }
                                                }
                                                break;
                                            case "H":                                                           //en el cas de hores
                                                if (t.getHora_inici() == tf.getHora_inici()) {                      //si la hora d'inici del torn coincideix
                                                    arriba_a_torn &= seguent(vf2, s.getDoctorsRel().get(0));            //comprovam que es pugui associar
                                                    if (arriba_a_torn) {                                                // si fins al moment tots han arribat al torn
                                                        aresta.addFlow(1);                                                  //agafim 1 al flow de l'aresta acual
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (!arriba_a_torn) {                                               // alguna de les arestes no arriba al torn          
                        s.setVisitat(true);                                                 // marcam el node perque no es torni a comprovar
                        desfesFillsVisitats(s);                                             //eliminam tot el flow que s'ha afagit
                    }
                } else {                                                            // si no es cap dels anteriors
                    Aresta af = graf.getA(la.get(i));
                    Vertex vf = graf.getVertex(af.getw());
                    if (!vf.equals(s)) {
                        if (s.getClasse() == Vertex.RESTRICCIO) {
                            arriba_a_torn = seguent(vf, doc_r_pare);
                        } else if (s.getClasse() == Vertex.DOCTOR) {
                            arriba_a_torn = seguent(vf, s.getId());
                        } else {
                            arriba_a_torn = seguent(vf, null);
                        }
                    }
                    if (arriba_a_torn) {
                        af.addFlow(1);
                    }
                }
            }
        } else {                                                                //si el vertex es un torn
            int numD = s.getNumDocRelacionats();                                    //agafam el numero de doctors ja assignats
            int numMD = ((Torn) s.getObjecte()).getMin_num_doctors();                //agafam el numero minim de doctors que ha de tenir el torn (el que s'ha de complir)
            if (numD < numMD) {                                                         //si no s'ha arribat a aquest minim
                s.addDoctorRel(doc_r_pare);                                             //associal el doctor 
                arriba_a_torn = true;                                                   //marcam la booleana com a cert
            } else {                                                                   //sino
                arriba_a_torn = false;                                                  //marcam la booleana com a fals
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

    private static float consultaPercent(Vertex v) {
        if (v.getClasse() == Vertex.TORN) {
            Torn t = (Torn) v.getObjecte();
            if (t.getMin_num_doctors() > 0) {
                return t.getPercent_sou();
            } else {
                return -1;
            }
        } else {
            ArrayList<Integer> la = v.getArestes();
            ArrayList<Float> percent = new ArrayList<Float>();
            for (int i = 0; i < la.size(); i++) {
                Vertex vf = graf.getVertex(graf.getA(la.get(i)).getw());
                if (!vf.equals(v)) {
                    percent.add(consultaPercent(vf));
                }
            }
            return trobaMinim(percent);
        }
    }

    private static float trobaMinim(ArrayList<Float> llista) {
        float min = Integer.MAX_VALUE;
        for (int i = 0; i < llista.size(); i++) {
            if (llista.get(i) != -1 && llista.get(i) < min) {
                min = llista.get(i);
            }
        }
        return min;
    }
//    public static void redireccionaRestriccio(Vertex s) {
//        if (s.getId().contains("XOR")) { // si l'indentificador es de la classe XOR
//            boolean canviat = false;
//            int pos = 0;
//            ArrayList<Integer> la = s.getArestes();
//            while (!canviat && pos < la.size()) {
//                Aresta a = graf.getA(la.get(pos));
//                Vertex v1 = graf.getVertex(a.getv());
//                if (v1.equals(s)) {
//                    Vertex v = graf.getVertex(a.getw());
//                    v.setVisitat(!v.getVisitat()); // canviam el cami
//                }
//                pos++;
//            }
//        }
//    }
}
