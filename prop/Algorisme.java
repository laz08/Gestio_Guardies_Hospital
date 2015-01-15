package prop;

public abstract class Algorisme implements Runnable{
    protected boolean selSou;
    protected long temps = -1;
    
    public Algorisme(boolean selSou){
        this.selSou = selSou;
    }
    
    abstract void maxFlow() throws Error;

    /**
     * Retorna el temps d'execució
     * @return
     */
    public long getTemps(){
        return temps;
    }
    
    @Override
    public void run(){
        try {
            long t1 = System.currentTimeMillis();
            maxFlow();
            long t2 = System.currentTimeMillis();
            temps = t2 - t1;
        } catch (Error ex) {
            System.err.println("Error durant l'execucio de l'algorisme"); 
        }
    }
}