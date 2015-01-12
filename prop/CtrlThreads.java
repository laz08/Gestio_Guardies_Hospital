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
    private static Thread threadCtrl = new Thread((Runnable) new Supervisor());
    private static boolean[] vius = {false, false, false};
    
    public static void FordFulquerson(Algorisme algo) {
        threads[0] = new Thread(algo);
        threads[0].start();
        vius[0] = true;
        if (!threadCtrl.isAlive()) {
            threadCtrl.start();
        }
    }

    public static void EdmondsKarp(Algorisme algo) {
        threads[1] = new Thread(algo);
        threads[1].start();
        vius[1] = true;
        if (!threadCtrl.isAlive()) {
            threadCtrl.start();
        }
    }

    public static void Dijkstra(Algorisme algo) {
        threads[2] = new Thread(algo);
        threads[2].start();
        vius[2] = true;
        if(!threadCtrl.isAlive()) {
            threadCtrl.start();
        }
    }
    
    public static boolean getVius(int pos){
        return vius[pos];
    }
    
    public static void setVius(int pos, boolean viu){
        vius[pos] = viu;
    }
    
    public static Thread getThread(int pos){
        return threads[pos];
    }
}
