/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

/**
 *
 * @author Xisco
 */
public class CtrlThreads{

    private static Thread[] threads = new Thread[3];
    private static Supervisor s = new Supervisor();
    private static boolean supervisor = false;
    
    public static void FordFulquerson(Algorisme algo) {
        System.out.println("Ford");
        Thread ford = new Thread(algo);
        threads[0] = ford;
        ford.start();
        s.setVius(0, true);
        if (!supervisor) {
            Thread supervisor0 = new Thread(s);
            supervisor0.start();
        }
    }

    public static void EdmondsKarp(Algorisme algo) {
        System.out.println("Edmonds");
        Thread edmonds = new Thread(algo);
        threads[1] = edmonds;
        edmonds.start();
        s.setVius(1, true);
        if (!supervisor) {
            Thread supervisor1 = new Thread(s);
            supervisor1.start();
        }
    }

    public static void Dijkstra(Algorisme algo) {
        System.out.println("Dij");
        Thread dij = new Thread(algo);
        threads[2] = dij;
        dij.start();
        s.setVius(2, true);
        if (!supervisor) {
            Thread supervisor2 = new Thread(s);
            supervisor2.start();
        }
    }
    
    public static Thread getThread(int pos){
        return threads[pos];
    }
    
    public static void setSupervisor(boolean estat){
        supervisor = estat;
    }
}
