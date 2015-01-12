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
    
    public static int getNumVertex(){
        return graf.numV();
    }
    
    public static void initGraf(){
        graf = Entrada.crea_graf();
    }
    
    public static int getNumArestes(){
        return graf.getNumA();
    }

    public static Graf getGraf(){
        return graf;
    }
}
