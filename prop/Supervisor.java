/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

/**
 *
 * @author Xisco
 */
public class Supervisor implements Runnable{

    
    @Override
    public void run() {
        while (algunViu()) {
            comprova_vius();
        }
    }

    private boolean algunViu() {
        boolean viu = false;
        int pos = 0;
        while (!viu && pos < 3) {
            if (CtrlThreads.getVius(pos)) {
                viu = true;
            }
        }
        return viu;
    }

    private void comprova_vius() {
        for (int i = 0; i < 3; i++) {
            if (CtrlThreads.getVius(i) && !CtrlThreads.getThread(i).isAlive()) {
                CtrlThreads.setVius(i, false);
                actualitza(i);
            }
        }
    }

    private void actualitza(int nThread) {
        long temps;
        Algorisme algorisme;
        switch (nThread) {
            case 0:
                algorisme = CtrlAlgorisme.getAlgorisme(0);
                temps = algorisme.getTemps();
                
                break;
            case 1:
                algorisme = CtrlAlgorisme.getAlgorisme(1);
                temps = algorisme.getTemps();
                break;
            case 2:
                algorisme = CtrlAlgorisme.getAlgorisme(2);
                temps = algorisme.getTemps();
                break;
        }
        
    }
    
}
