/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;
import static prop.CtrlAlgorisme.graf;

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
    public EdmondsKarp(boolean selSou) {
        super(selSou);
        cua = new ArrayList<Vertex>();
    }

    @Override
    public void maxFlow() {
        graf.resetFlow();                                                       // posam tot el flow a 0
        recorregut_en_amplada(graf.getVertex("FONT", Vertex.FONT_POU));         // iniciam el recorregut en amplada a partir del vertex font
        Entrada.guarda_assignacions(graf);
    }

    private void recorregut_en_amplada(Vertex vertex) {
        afegir_vertex(vertex);
        while (!cua.isEmpty()) {
            Vertex fill = cua.remove(0);
            switch (fill.getClasse()) {
                case Vertex.DOCTOR:
                    tractar_doctor(fill);
                    break;
                case Vertex.FONT_POU:
                    tractar_font(fill);
                    break;
                case Vertex.RESTRICCIO:
                    tractar_restriccio(fill);
                    break;
            }
        }
    }

    private void tractar_doctor(Vertex vdoctor) {
        ArrayList<Integer> llista_arestes = vdoctor.getArestes();
//        Doctor doctor = (Doctor) vdoctor.getObjecte();
        for (int i = 0; i < llista_arestes.size(); i++) {
            Aresta aresta = graf.getA(llista_arestes.get(i));
            Vertex fill = graf.getVertex(aresta.getw());
            if (!fill.equals(vdoctor)) {
                fill.addDoctorRel(vdoctor.getId());
                afegir_vertex(fill);
            }
        }
    }

    private void tractar_font(Vertex vfont) {
        ArrayList<Integer> llista_arestes = vfont.getArestes();
        for (int i = 0; i < llista_arestes.size(); i++) {
            Aresta aresta = graf.getA(llista_arestes.get(i));
            Vertex fill = graf.getVertex(aresta.getw());
            if (!fill.equals(vfont)) {
                afegir_vertex(fill);
            }
        }
    }

    private void tractar_restriccio(Vertex vrestriccio) {
        Restriccio restriccio = (Restriccio) vrestriccio.getObjecte();
        switch (restriccio.getOp()) {
            case "AND":
            case "XOR":
                tracta_and_xor(vrestriccio);
                break;
            case "NOP":
                tracta_nop(vrestriccio);
                break;
        }
    }

    private void tracta_and_xor(Vertex vrestriccio) {
        ArrayList<Integer> llista_arestes = vrestriccio.getArestes();
        for (int i = 0; i < llista_arestes.size(); i++) {
            Aresta aresta = graf.getA(llista_arestes.get(i));
            Vertex fill = graf.getVertex(aresta.getw());
            if (!fill.equals(vrestriccio)) {
                fill.addDoctorRel(vrestriccio.getDoctorsRel().get(0));
                afegir_vertex(fill);
            }
        }
    }

    private void tracta_nop(Vertex vrestriccio) {
        Restriccio restriccio = (Restriccio) vrestriccio.getObjecte();
        ArrayList<Integer> llista_arestes = vrestriccio.getArestes();
        boolean relacionats;
        if (restriccio.getTipus().equals("D")) {
            relacionats = true;
        } else {
            relacionats = false;
        }
        for (int i = 0; i < llista_arestes.size(); i++) {
            Aresta aresta = graf.getA(llista_arestes.get(i));
            Vertex fill = graf.getVertex(aresta.getw());
            if (!fill.equals(vrestriccio)) {
                boolean relacionat;
                if (restriccio.getTipus().equals("D")) {
                    relacionat = relacionar_torn(fill, vrestriccio.getDoctorsRel().get(0));
                    relacionats &= relacionat;
                } else {
                    relacionat = relacionar_torn(fill, vrestriccio.getDoctorsRel().get(0));
                    relacionats |= relacionat;
                }
                if (relacionat) {
                    aresta.addFlow(1);
                }
            }
        }
        if (restriccio.getTipus().equals("D") && !relacionats) {
            elimina_relacionats(vrestriccio);
        }

        if (relacionats) {
            fixar(vrestriccio, null);
        }
    }

    private void afegir_vertex(Vertex vertex) {
        if(vertex.getClasse() == Vertex.DOCTOR && selSou){
            Doctor doctor = (Doctor) vertex.getObjecte();
            boolean posicionat = false;
            for(int i=0; i<cua.size() && !posicionat; i++){
                Vertex v = cua.get(i);
                if(v.getClasse() == Vertex.DOCTOR){
                    Doctor doc = (Doctor) v.getObjecte();
                    if(doctor.getSou() < doc.getSou()){
                        cua.add(i,vertex);
                        posicionat = true;
                    }
                }
            }
            if(!posicionat) cua.add(vertex);
        }
        else{
            cua.add(vertex);
        }
    }

    private boolean relacionar_torn(Vertex vtorn, String doctor) {
        boolean relacionat;
        Torn torn = (Torn) vtorn.getObjecte();
        if (torn.getMin_num_doctors() > vtorn.getNumDocRelacionats()) {
            vtorn.addDoctorRel(doctor);
            relacionat = true;
        } else {
            relacionat = false;
        }
        return relacionat;
    }

    private void elimina_relacionats(Vertex vertex) {
        ArrayList<Integer> llista_arestes = vertex.getArestes();
        for (int i = 0; i < llista_arestes.size(); i++) {
            Aresta aresta = graf.getA(llista_arestes.get(i));
            Vertex fill = graf.getVertex(aresta.getw());
            if (!fill.equals(vertex)) {
                aresta.addFlow(-1);
                fill.rmDoctorRel(vertex.getDoctorsRel().get(0));
            }
        }
    }

    /**
     * Donat un vertex fa un recorregut invers del graf i elimina les posibles
     * variants desde XORs que hagi trobat pel camí
     *
     * @param vertex
     */
    private void fixar(Vertex vertex, Aresta aresta_fill) {
        if (vertex.getClasse() == Vertex.RESTRICCIO) {
            Restriccio restriccio = (Restriccio) vertex.getObjecte();
            ArrayList<Integer> llista_arestes = vertex.getArestes();
            if (aresta_fill == null || !restriccio.getOp().equals("XOR")) {
                for (int i = 0; i < llista_arestes.size(); i++) {
                    Aresta aresta = graf.getA(llista_arestes.get(i));
                    Vertex pare = graf.getVertex(aresta.getv());
                    if (!pare.equals(vertex)) {
                        fixar(pare, aresta);
                        aresta.addFlow(1);
                    }
                }
            } else {
                for (int i = 0; i < llista_arestes.size(); i++) {
                    Aresta aresta = graf.getA(llista_arestes.get(i));
                    Vertex fill = graf.getVertex(aresta.getw());
                    if (!fill.equals(vertex) && !aresta.equals(aresta_fill)) {
                        elimina_fills(fill);
                    } else if (fill.equals(vertex)) {
                        Vertex pare = graf.getVertex(aresta.getv());
                        fixar(pare, aresta);
                        aresta.addFlow(1);
                    }
                }
            }
        }
    }
    
    private void elimina_fills(Vertex vertex){
        if(cua.contains(vertex)){
            cua.remove(vertex);
        }
        else{
            ArrayList<Integer> llista_arestes = vertex.getArestes();
            for(int i=0; i<llista_arestes.size(); i++){
                Aresta aresta = graf.getA(llista_arestes.get(i));
                Vertex fill = graf.getVertex(aresta.getw());
                if(!fill.equals(vertex)){
                    elimina_fills(fill);
                }
            }
        }
    }
}