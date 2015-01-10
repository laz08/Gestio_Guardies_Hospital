package prop;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Algorisme implements Runnable{
    protected static Graf graf = null;
    
    public void setGraf(Graf g){
        graf = g;
    }
    
    public static Graf getGraf(){
        return graf;
    }
    
    abstract void maxFlow() throws Error;
    
    @Override
    public void run(){
        try {
            maxFlow();
        } catch (Error ex) {
            System.err.println("Error durant l'execucio de l'algorisme"); 
        }
    }
}