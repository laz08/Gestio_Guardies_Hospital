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

    @Override
    public void maxFlow() {
        graf.resetFlow();
        recorregut_en_amplada(graf.getVertex("FONT", Vertex.FONT_POU));
    }

    private static void recorregut_en_amplada(Vertex s) {
        cua = new ArrayList<Vertex>();
        cua.add(s);
        while (!cua.isEmpty()) {
            Vertex v = cua.get(0);
            cua.remove(0);
            if (v.getClasse() == Vertex.MAX) {
                boolean trobat = false;
                Aresta a = null;
                int pos = 0;
                Vertex vp = null;
                while(!trobat && pos < v.getArestes().size()){
                    a = graf.getA(v.getArestes().get(pos));
                    vp = graf.getVertex(a.getv());
                    if(!vp.equals(v) && a.getflow()==0 && !vp.getVisitat()){
                        trobat = true;
                    }
                    pos++; 
                }
                
                
                //System.out.println("Seguiment 1: "+v.getClasse());
                
                
                tractaVertexMax(vp,v,a);
            } else {
                ArrayList<Integer> adj = v.getArestes();
                for (int i = 0; i < adj.size(); i++) {
                    Aresta a = graf.getA(adj.get(i));
                    Vertex v1 = graf.getVertex(a.getw());
                    if (!v1.equals(v)) {
                        switch (v1.getClasse()) {
                            case Vertex.DOCTOR:
                                cua.add(v1);
                                break;
                            case Vertex.RESTRICCIO:
                                if (!v1.getVisitat()) {
                                    cua.add(v1);
                                }
                                break;
                            case Vertex.MAX:
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

    private static void tractaVertexMax(Vertex vp, Vertex v, Aresta a) {
        if (vp.getClasse() == Vertex.RESTRICCIO) {
            
            //System.out.println("Seguiment 2: "+ v.getClasse() +"----> MAX: "+Vertex.MAX);
            
            int maxRest = v.getNumMaxRestr();
            Vertex vt = graf.getVertex(v.getId(), Vertex.TORN);
            Aresta aresta = null;
            ArrayList<Integer> la = vt.getArestes();
            boolean trobat = false;
            int pos = 0;
            while (!trobat && pos < la.size()) {
                aresta = graf.getA(la.get(pos));
                if (graf.getVertex(aresta.getw()).equals(vt)) {
                    trobat = true;
                }
                pos++;
            }
            // tenim (vp)----a----(v)---aresta---(vt)
            //    Restriccio      Max            Torn
            if (maxRest > vt.getNumDocRelacionats()) {
                String doc_r = vp.getDoctorsRel().get(0);
                if (!vt.getDoctorsRel().contains(doc_r)) {
                    int cap = aresta.getcap();
                    aresta.setCap(cap - 1);
                    vt.addDoctorRel(doc_r);
                    puja_flow(v, a);
                    // si es troba una restriccio XOR, s'ha de cercar l'altre fill i eliminar-lo de la cua igual que cualsevol vertex que hagui pogut sortir d'aquest
                } else {
                    vp.setVisitat(true);
                }

            }
        } else {
            posa_flow_min(v, a); // recorregut desde pou fins doctor posant el minim de flux
        }
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
                }
                else if(opvp.equals("AND")){
                    for(int i=0; i<la.size(); i++){
                        Aresta ar = graf.getA(la.get(i));
                        if(ar.getflow()==0 && graf.getVertex(ar.getv()).equals(vp)){
                            Vertex vf = graf.getVertex(ar.getw());
                            if(!vf.equals(v)){
                                tractaVertexMax(vp, vf, ar);
                            }
                        }
                    }
                }
            }
            puja_flow(vp, aresta);
        }
    }

    private static void posa_flow_min(Vertex v, Aresta a) {
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
            }
            puja_flow(vp, aresta);
        }
    }

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
                
                if (vf.getClasse() == Vertex.MAX) {
                    
                    Vertex vtorn = graf.getVertex(vf.getId(), Vertex.TORN);
                    Vertex vtact = graf.getVertex(v.getId(), Vertex.TORN); // agafam el torn relacionat amb MAX
                    Torn t = (Torn) vtorn.getObjecte();
                    Torn tact = (Torn) vtact.getObjecte();
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
                            }
                            else{
                                tractaVertexMax(vp, vf, aresta);
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
                            }
                            else{
                                tractaVertexMax(vp, vf, aresta);
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
}
