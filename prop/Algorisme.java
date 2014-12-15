package prop;

public abstract class Algorisme {
    protected static Graf graf = null;
    
    public void setGraf(Graf g){
        graf = g;
    }
    
    public static Graf getGraf(){
        return graf;
    }
    
    public abstract void maxFlow() throws Error;
}