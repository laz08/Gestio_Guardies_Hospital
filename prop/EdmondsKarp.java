/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;
import static prop.Algorisme.graf;

/**
 *
 * @author Xisco
 */
public class EdmondsKarp extends Algorisme {

    private static ArrayList<Vertex> cua;
    // L'algorisme de Edmond's Karp no es mes que un recorregut en amplada per trobar tots els possibles
    // camins dins un graf, per tant, fem el recorregut en amplada pero no ens aturarem en trobar un cami
    // que arribi al vertex POU, sino que seguirem comprovant els altres possibles.
    // De manera que quan es troba el POU es fara un recorregut invers (cami que trobaria l'algorisme) 
    // per assignar flow a les branques

    /**
     * Aplica l'alsorisme d'Edmond's Karp per associar els doctors amb els torns
     *
     * @param selSou Si es 'true' llavors selcciona per sou; Si es 'false' només
     * es basa en complir el minim de doctors per torn
     */
    public EdmondsKarp(boolean selSou, Graf g) {
        super(selSou, g);
    }

    @Override
    public void maxFlow() {
        graf.resetFlow();                                                       // posam tot el flow a 0
        recorregut_en_amplada(graf.getVertex("FONT", Vertex.FONT_POU));         // iniciam el recorregut en amplada a partir del vertex font
    }

    /**
     * Donat un vertex s, crea un recorregut en amplada a partir d'aquest
     *
     * @param s Vertex inicial
     */
    private static void recorregut_en_amplada(Vertex s) {
        cua = new ArrayList<Vertex>();                                          // cream una cua que farem servir per el recorregut en amplada
        cua.add(s);                                                             // afagim el primer vertex 
        while (!cua.isEmpty()) {                                                // mentre la cua no sigui buida
            Vertex v = cua.get(0);                                                  // agafam el primer element de la cua
            cua.remove(0);                                                          // l'eliminam de la cua 

            if (v.getClasse() == Vertex.TORN) {                                      // si arribam a un vertex torn    
                if (selSou) {
                    Aresta a = null;
                    Vertex vp = null, v1 = null;
                    int sou_min = Integer.MAX_VALUE;
                    for (int i = 0; i < v.getArestes().size(); i++) {
                        a = graf.getA(v.getArestes().get(i));                                 //agafam una aresta
                        v1 = graf.getVertex(a.getv());
                        if (!v1.equals(v) && a.getflow() == 0 && !v1.getVisitat()) {
                            String dni_doc = v1.getDoctorsRel().get(0);
                            int sou = seleccionaSouDoctor(dni_doc);
                            if (sou_min > sou) {
                                sou_min = sou;
                                vp = v1;
                            }
                        }
                    }
                    tractaVertexTorn(vp, v, a);                                               //tractam el vertex torn
                } else {
                    boolean trobat = false;
                    Aresta a = null;
                    int pos = 0;
                    Vertex vp = null;
                    while (!trobat && pos < v.getArestes().size()) {                          // mentre no s'ha trobat el vertex pare del torn (restriccio) i queden arestes per comprovar 
                        a = graf.getA(v.getArestes().get(pos));                                 //agafam una aresta
                        vp = graf.getVertex(a.getv());                                          //agafam el vertex inicial de l'aresta
                        if (!vp.equals(v) && a.getflow() == 0 && !vp.getVisitat()) {                //si el vertex que hem agafat no es el vertex torn
                            trobat = true;                                                          //marcam com a trobat
                        }                                                                       //
                        pos++;
                    }                                                                       //
                    tractaVertexTorn(vp, v, a);                                               //tractam el vertex torn
                }
            } else {                                                                //sino
                ArrayList<Integer> adj = v.getArestes();                                //agafam les arestes adjacents al vertex
                for (int i = 0; i < adj.size(); i++) {                                  //per totes les arestes adjacents al vertex
                    Aresta a = graf.getA(adj.get(i));                                       //agafam l'aresta
                    Vertex v1 = graf.getVertex(a.getw());                                   //agafam el vertex destí de l'aresta
                    if (!v1.equals(v)) {                                                    //si el vertex destí es diferent al vertex que esteim comprovant
                        switch (v1.getClasse()) {
                            case Vertex.DOCTOR:                                                 //en el cas que trobem un doctor
                                //afagir_ordenadament en cas de seleccio per sou
                                cua.add(v1);                                                        //afagim el vertex a la cua
                                break;                                                          //
                            case Vertex.RESTRICCIO:                                             //en el cas que trobem una resticcio
                                if (!v1.getVisitat()) {                                             //si no esta marcada com a visitada
                                    cua.add(v1);                                                        //afagim el vertex a la cua
                                }                                                                   //
                                break;                                                          //
                            case Vertex.TORN:
                                cua.add(v1);
                                break;
                            case Vertex.FONT_POU:
                                break;
                        }
                    }
                }
            }
        }
    }

    private static void tractaVertexTorn(Vertex vp, Vertex v, Aresta a) {
////        if (vp.getClasse() == Vertex.RESTRICCIO) {                          //si el vertex pare es una restriccio
        int minDoc = ((Torn) v.getObjecte()).getMin_num_doctors();              //agafam el nombre minim de doctors necessaris per aquest torn                         
        //Vertex vt = graf.getVertex(v.getId(), Vertex.TORN);
        Aresta aresta = null;
        ArrayList<Integer> la = v.getArestes();                                 //agafam la llista d'arestes relacionades amb el torn 
        boolean trobat = false;
        int pos = 0;
        while (!trobat && pos < la.size()) {                                    //mentre no s'ha trobat i queden arestes per comprovar
            aresta = graf.getA(la.get(pos));                                        //comprovam una aresta
            if (graf.getVertex(aresta.getw()).equals(v)) {                          //si el desti de l'aresta coincideix amb el node torn
                trobat = true;                                                          //marcam com a trobat
            }                                                                       // 
            pos++;
        }                                                                       //

        if (minDoc > v.getNumDocRelacionats()) {                                //si el nombre minim de doctors pel torn es major al nombre de doctors ja relacionats amb aquest
            String doc_r = vp.getDoctorsRel().get(0);                               //agafam el doctor al que pertany la restriccio

            System.out.println(!v.getDoctorsRel().contains(doc_r));

            if (!v.getDoctorsRel().contains(doc_r)) {                               //si el doctor no esta relacionat
                v.addDoctorRel(doc_r);                                                  //afagim el doctor
                puja_flow(v, a);                                                        //tornam arrere per marcar el cami seguit
            } else {                                                                //sino
                vp.setVisitat(true);                                                    //marcam la restriccio superior com a visitada per no repetir-la
            }                                                                       //
        }                                                                       //
//        } else {                                                                
//            posa_flow_min(v, a);// recorregut desde pou fins doctor posant el minim de flux
//        }
    }

    private static void puja_flow(Vertex v, Aresta a) {
        if (v.getClasse() != Vertex.FONT_POU) {
            a.addFlow(1);
            Vertex vp = graf.getVertex(a.getv());
            boolean trobat = false;
            int pos = 0;
            ArrayList<Integer> la = vp.getArestes();
            Aresta aresta = null;
            while (!trobat && pos < la.size()) {
                aresta = graf.getA(la.get(pos));
                if (!graf.getVertex(aresta.getv()).equals(vp)) {
                    trobat = true;
                }
                pos++;
            }
            if (vp.getClasse() == Vertex.RESTRICCIO) {
                String opvp = ((Restriccio) vp.getObjecte()).getOp();
                if (opvp.equals("XOR")) {
                    elimina_fills_xor(vp, v);
                } else if (opvp.equals("AND")) {
                    for (int i = 0; i < la.size(); i++) {
                        Aresta ar = graf.getA(la.get(i));
                        if (ar.getflow() == 0 && graf.getVertex(ar.getv()).equals(vp)) {
                            Vertex vf = graf.getVertex(ar.getw());
                            if (!vf.equals(v) && vf.getClasse() == Vertex.TORN) {
                                tractaVertexTorn(vp, vf, ar);
                            } else if (!vf.equals(v)) {
                                recorregut_en_amplada(vf);
                            }
                        }
                    }
                }
            }
            puja_flow(vp, aresta);
        }
    }

//    private static void posa_flow_min(Vertex v, Aresta a) {
//        if (v.getClasse() != Vertex.FONT_POU) {
//            a.addFlow(1);
//            Vertex vp = graf.getVertex(a.getv());
//            boolean trobat = false;
//            int pos = 0;
//            ArrayList<Integer> la = vp.getArestes();
//            Aresta aresta = null;
//            while (!trobat && pos < la.size()) {
//                aresta = graf.getA(la.get(pos));
//                if (!graf.getVertex(aresta.getv()).equals(vp)) {
//                    trobat = true;
//                }
//            }
//            puja_flow(vp, aresta);
//        }
//    }
    private static void elimina_fills_xor(Vertex vp, Vertex v) {
        ArrayList<Integer> la = vp.getArestes();
        String tipusR = ((R_XOR) vp.getObjecte()).getTipus();
        boolean trobat = false;
        Aresta aresta = null;
        Vertex vf = null;
        for (int i = 0; i < la.size() && !trobat; i++) {
            aresta = graf.getA(la.get(i));
            vf = graf.getVertex(aresta.getw());
            if (aresta.getflow() == 0 && graf.getVertex(aresta.getv()).equals(vp)) {

                if (vf.getClasse() == Vertex.TORN) {

                    //Vertex vtorn = graf.getVertex(vf.getId(), Vertex.TORN);
                    //Vertex vtact = graf.getVertex(v.getId(), Vertex.TORN); // agafam el torn relacionat amb MAX
                    Torn t = (Torn) vf.getObjecte();
                    Torn tact = (Torn) v.getObjecte();
                    switch (tipusR) {
                        case "H":
                            int ht = t.getHora_inici();
                            int ha = tact.getHora_inici();

                            if (ht != ha) {
                                if (cua.contains(vf)) {
                                    cua.remove(cua.lastIndexOf(vf)); // elimina l'element nomes un cop en el cas que surti mes d'una vegada
                                } else {
                                    elimina_fills(vf);
                                }
                            } else {
                                tractaVertexTorn(vp, vf, aresta);
                            }
                            break;
                        case "D":
                            int dt = t.getPosicio();
                            int da = tact.getPosicio();

                            if (da != dt) {
                                if (cua.contains(vf)) {
                                    cua.remove(cua.lastIndexOf(vf));
                                } else {
                                    elimina_fills(vf);
                                }
                            } else {
                                tractaVertexTorn(vp, vf, aresta);
                            }
                            break;
                    }
                } else {


                    System.out.println(vf.getId());
                    vf.setVisitat(true);

                    if (cua.contains(vf)) {
                        cua.remove(vf);
                    } else {
                        elimina_fills(vf);
                    }
                }
            }
        }
    }

    private static void elimina_fills(Vertex v) {
        ArrayList<Integer> la = v.getArestes();
        boolean trobat = false;
        Aresta aresta = null;
        Vertex vf = null;
        for (int i = 0; i < la.size() && !trobat; i++) {
            aresta = graf.getA(la.get(i));
            vf = graf.getVertex(aresta.getw());
            if (aresta.getflow() == 0 && !vf.equals(v)) {
                if (cua.contains(vf)) {
                    cua.remove(vf);
                } else {
                    elimina_fills(vf);
                }
            }
        }
    }

    private static int seleccionaSouDoctor(String dni) {
        int pos = 0, sou = 0;
        boolean trobat = false;
        while (!trobat && pos < graf.numV()) {
            Vertex v = graf.getVertex(pos);
            if (v.getClasse() == Vertex.DOCTOR) {
                Doctor d = (Doctor) v.getObjecte();
                if (d.getdni().equals(dni)) {
                    sou = d.getSou();
                    trobat = true;
                }
            }
            pos++;
        }
        return sou;
    }
}
