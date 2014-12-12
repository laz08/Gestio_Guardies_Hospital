package prop;

import java.util.*;

public class FordFulkerson extends Algorisme {
    private static ArrayList<Vertex> cua;
    // L'algorisme de Ford Fulkerson no es mes que un recorregut en profunditat per trobar tots els possibles
    // camins dins un graf, per tant, fem el recorregut en profunditat pero no ens aturarem en trobar un cami
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
                        case Vertex.TORN:
                            if (v1.getVisitat()) {
                                v.setVisitat(true);
                            } else {
                                cua.add(v1);
                            }
                            break;
                        case Vertex.MAX:
                            cua.add(v1);
                            tractaVertexMax(v, v1, a);
                            break;
                        case Vertex.FONT_POU:
                            break;
                    }
                }
            }
        }
    }

    private static void tractaVertexMax(Vertex vp, Vertex v, Aresta a) {

        if (vp.getClasse() == Vertex.RESTRICCIO) {
            int maxRest = v.getNumMaxRestr();
            Vertex vt = graf.getVertex(v.getId(), Vertex.TORN);
            Aresta aresta = null;
            ArrayList<Integer> la = vt.getArestes();
            boolean trobat = false;
            int pos = 0;
            while(!trobat && pos<la.size()){
                aresta = graf.getA(la.get(pos));
                if(graf.getVertex(aresta.getw()).equals(vt)) trobat = true;
                pos ++;
            }

            if (vt != null && maxRest > vt.getNumDocRelacionats()) { //<-----------------
                String doc_r = vp.getDoctorsRel().get(0);
                if (!vt.getDoctorsRel().contains(doc_r)) {// <---------------
                    int cap = aresta.getcap();
                    aresta.setCap(cap - 1);
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
                pos ++;
            }
            if(vp.getClasse() == Vertex.RESTRICCIO){
                ArrayList<Restriccio> lr = CtrlRestriccio.consulta_llista_res();
                Restriccio r = null;
                boolean tro = false;
                int p = 0;
                while (!tro && p<lr.size()){
                    r  = lr.get(p);
                    String rid = r.toString();
                    if(rid.equals(vp.getId())) tro = true;
                    p++;
                }
                if(tro){
                    String op = r.getOp();
                    if (op.equals("XOR")){
                        if(cua.contains(vp)) cua.remove(vp);
                        else{
                            elimina_fills(vp);
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

    private static void elimina_fills(Vertex v){
        ArrayList<Integer> la = v.getArestes();
        boolean trobat = false;
        Aresta aresta = null;
        Vertex vf = null;
        for(int i=0; i<la.size() && !trobat; i++){
            aresta = graf.getA(la.get(i));
            vf = graf.getVertex(aresta.getw());
            if(aresta.getflow() == 0 && !vf.equals(v)){
                if(cua.contains(vf)) cua.remove(vf);
                else elimina_fills(vf);
            }
        }
    }
}