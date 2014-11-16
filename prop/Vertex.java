package prop;

public class Vertex {
    private String identificador;
    private int classe;
    public final int DOCTOR = 0, RESTRICCIO = 1, TORN = 2;
    
    public Vertex(String id, int c) throws Error{
        identificador = id;
        if (c == DOCTOR || c == RESTRICCIO || c == TORN){
            classe = c;
        }
        else{
            throw new Error("La classe del vertex no es correcta");
        }
    }
    
    public int getClasse(){
        return classe;
    }
    
    public String getId(){
        return identificador;
    }
    
    
    
}