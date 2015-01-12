/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

/**
 *
 * @author Xisco
 */
public class CtrlAlgorisme {
    public static Graf graf = new Graf();
    private static Algorisme[] algorismes = new Algorisme[3]; 
    
    
    public static int getNumVertex(){
        return graf.numV();
    }
    
    public static void initGraf(){
        graf = Entrada.crea_graf();
    }
    
    public static void iniciarGraf(String plt) {
    	graf = Entrada.crea_graf_plt(plt);
    }
    
    public static int getNumArestes(){
        return graf.getNumA();
    }
    
    public static void executaFordFulkerson(boolean selSou){
        algorismes[0] = new FordFulkerson(selSou);
        CtrlThreads.FordFulquerson(algorismes[0]);
    }
    
    public static void executaEdmondsKarp(boolean selSou){
        algorismes[1] = new EdmondsKarp(selSou);
        CtrlThreads.EdmondsKarp(algorismes[1]);
    }
    
    public static void executaDijkstra(boolean selSou){
        algorismes[2] = new Dijkstra(selSou);
        CtrlThreads.Dijkstra(algorismes[2]);
    }
    
    public static Algorisme getAlgorisme(int pos){
        return algorismes[pos];
    }
    
    public static Graf getGraf(){
        return graf;
    }
}
