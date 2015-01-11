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
public class Dijkstra extends Algorisme{
        
    private static ArrayList<Aresta> arestesPendents;
            
    public Dijkstra(boolean selSou, Graf g){
        super(selSou, g);
        preparaGraf();
    }
    
    
    @Override
    void maxFlow() throws Error {
        arestesPendents = posaDoctors();
        while(!arestesPendents.isEmpty() && !totsBloquejats()){
            Aresta a = arestesPendents.get(0);
            arestesPendents.remove(0);
            a.addFlow(1);
            Vertex v = graf.getVertex(a.getw()); 
            if(v.getClasse() == Vertex.TORN){
                if(v.getVisitat()){
                    tornaEnrere(a);
                }
                else{
                    Vertex vp = graf.getVertex(a.getv());
                    v.addDoctorRel(vp.getDoctorsRel().get(0));
                    if(v.getNumDocRelacionats() == v.getNumMaxRestr()){
                        v.setVisitat(true);
                    }
                }
            }
            else{
                ArrayList<Integer> la = v.getArestes();
                for(int i=0; i<la.size(); i++){
                    Aresta a1 = graf.getA(la.get(i));
                    Vertex v1 = graf.getVertex(a1.getw());
                    if(!v1.equals(v) && !v1.getVisitat()){
                        if(v.getId().contains("XOR")){
                            bloqueja_arestes(la,a1,v);
                        }
                        boolean posicionat = false;
                        int pos = 0;
                        while(!posicionat && pos<arestesPendents.size()){
                            Aresta aresta = arestesPendents.get(pos);
                            if(aresta.getcap()>a1.getcap()){
                                arestesPendents.add(pos, a1);
                                posicionat = true;
                            }
                            pos++;
                        }
                    }
                }
            }
            
        }
    }
    
    private static ArrayList<Aresta> posaDoctors(){
        ArrayList<Aresta> arestes = new ArrayList<Aresta>();
        Vertex font = graf.getVertex("FONT", Vertex.FONT_POU);
        ArrayList<Integer> la = font.getArestes();
        for(int i=0; i<la.size(); i++){
            Aresta a = graf.getA(la.get(i)); 
            arestes.add(a);
        }
        return arestes;
    }
    
    private static void tornaEnrere(Aresta a){
        Vertex v = graf.getVertex(a.getv());
        boolean atura = false;
        while(!atura){
            if(v.getClasse() == Vertex.FONT_POU || v.getId().contains("XOR")) {
                atura = true;
                if (v.getId().contains("XOR"))desbloqueja_arestes(v);
            }
            else {
                a.addFlow(-1);
                ArrayList<Integer> arestes = v.getArestes();
                boolean trobat = false;
                int pos = 0;
                while (!trobat && pos<arestes.size()){
                    Aresta a1 = graf.getA(arestes.get(pos));
                    Vertex v1 = graf.getVertex(a1.getv());
                    if(!v1.equals(v)){
                        trobat = true;
                        v = v1;
                        a = a1;
                    }
                    pos ++;
                }
            }
        }
    }
    
    
    private static void bloqueja_arestes(ArrayList<Integer> la, Aresta aresta, Vertex vertex){
        for(int i=0; i<la.size(); i++){
            Aresta a = graf.getA(la.get(i));
            if(!a.equals(aresta)){
                Vertex v = graf.getVertex(a.getw());
                if(!v.equals(vertex)){
                    v.setVisitat(true);
                }
            }
        }
    }
    
    private static void desbloqueja_arestes(Vertex v){
        ArrayList<Integer> arestes = v.getArestes();
        for(int i=0; i<arestes.size(); i++){
            Aresta a = graf.getA(arestes.get(i));
            Vertex v1 = graf.getVertex(a.getw());
            if(!v1.equals(v)){
                v1.setVisitat(false);
            }
        }
    }
    
    private static boolean totsBloquejats(){
        boolean bloquejats = true;
        for(int i=0; i<arestesPendents.size(); i++){
            Vertex v = graf.getVertex(arestesPendents.get(i).getw());
            if(!v.getVisitat()) bloquejats = false;
            else{                                                               // si el vertex esta bloquejat es mou al final de la llista
                boolean colocat = false;
                for(int e = i; e<arestesPendents.size() && !colocat; e++){      // si hi ha algun altre bloquejat el posam just abans
                    Vertex v1 = graf.getVertex(arestesPendents.get(e).getw());
                    if(v1.getVisitat()){                                        
                        colocat = true;
                        arestesPendents.add(e,arestesPendents.get(i));
                        arestesPendents.remove(i);
                    }
                }
                if(!colocat){                                                   // si no hi ha cap altre bloquejat, es posa al final
                    arestesPendents.add(arestesPendents.get(i));
                    arestesPendents.remove(i);
                }
            }
        }
        return bloquejats;
    }
    
    private static void preparaGraf(){
        ArrayList<Integer> vertex = seleccionaTorns();
        while(!vertex.isEmpty()){
            int nv = vertex.get(0);
            vertex.remove(0);
            Vertex v = graf.getVertex(nv);
            if(v.getClasse() != Vertex.FONT_POU){
                int min_sortida = cercaArestaSortida(v);
                ArrayList<Integer> vertex_pare = marcaArestaEntrada(min_sortida, v);
                for(int i=0; i<vertex_pare.size(); i++){
                    vertex.add(vertex_pare.get(i));
                }
            }
        }
    }
    
    private static int cercaArestaSortida(Vertex v){
        ArrayList<Integer> la = v.getArestes();
        int min = Integer.MAX_VALUE;
        for(int i=0; i<la.size(); i++){
            Aresta a = graf.getA(la.get(i));
            Vertex v1 = graf.getVertex(a.getw());
            if(!v1.equals(v) && min > a.getcap()){
                min = a.getcap();
            }
        }
        return min;
    }
    
    private static ArrayList<Integer> marcaArestaEntrada(int min, Vertex v){
        ArrayList<Integer> la = v.getArestes();
        ArrayList<Integer> lvEntrada = new ArrayList<Integer>();
        for(int i=0; i<la.size(); i++){
            Aresta a = graf.getA(la.get(i));
            Vertex v1 = graf.getVertex(a.getv());
            if(!v1.equals(v)){
                lvEntrada.add(a.getv());
                a.setCap(min);
            }
        }
        return lvEntrada;
    }
    
    private static ArrayList<Integer> seleccionaTorns(){
        ArrayList<Integer> torns = new ArrayList<Integer>();
        Vertex pou = graf.getVertex("POU", Vertex.FONT_POU);
        ArrayList<Integer> la = pou.getArestes();
        for(int i=0; i<la.size(); i++){
            Vertex v = graf.getVertex(graf.getA(la.get(i)).getv());
            if(v.getClasse() == Vertex.TORN){ // en teoria innecessaria pero per si acas...
                torns.add(i);
            }
        }
        return torns;
    }
    
}
