package prop;

import java.util.*;
import java.math.*;
import java.io.*;
import static prop.Algorisme.graf;

public class FordFulkerson extends Algorisme {

    @Override
    public void maxFlow() throws Error {
        graf.resetFlow();
        Doc_Torn.removeAll();
        seguent(graf.getVertex("FONT", Vertex.FONT_POU), null);
    }

    private static boolean seguent(Vertex s, String doc_r_pare) throws Error {
        boolean arriba_a_torn = false;
        if (s.getClasse() != Vertex.TORN) {                                     //si no es un torn
            ArrayList<Integer> la = s.getArestes();                                 //agafam el conjunt d'arrestes que surt del vertex
            for (int i = 0; i < la.size() && !s.getVisitat(); i++) {                //per cada una de elles comprovam 
                if (s.getId().contains("XOR")) {                                        //si es un XOR
                    Aresta a = graf.getA(la.get(i));                                        //agafam l'aresta
                    if (graf.getVertex(a.getv()).equals(s)) {                               //si l'aresta "surt" del vertex
                        Vertex vf = graf.getVertex(a.getw());                                   //seleccionam el vertex fill
                        if (vf.getClasse() != Vertex.TORN) {                                    //si el fill no es un TORN (es una restriccio)
                            arriba_a_torn = seguent(vf, s.getDoctorsRel().get(0));                  //comprovam el seguent vertex 
                            if (arriba_a_torn) {                                                    //si arriba el torn 
                                a.addFlow(1);                                                           //augmentam el flow en 1
                                s.setVisitat(true);                                                     //bloquejam el vertex perque es un XOR
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
        } else {                                                                //si el vertex es un torn
            int numD = s.getNumDocRelacionats();                                    //agafam el numero de doctors ja assignats
            int numMD = ((Torn)s.getObjecte()).getMin_num_doctors();                //agafam el numero minim de doctors que ha de tenir el torn (el que s'ha de complir)
            if(numD<numMD){                                                         //si no s'ha arribat a aquest minim
                s.addDoctorRel(doc_r_pare);                                             //associal el doctor 
                arriba_a_torn = true;                                                   //marcam la booleana com a cert
            }
            else{                                                                   //sino
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