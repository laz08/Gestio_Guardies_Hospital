package prop;

import java.util.ArrayList;

public class Vertex {
    public static final int DOCTOR = 0, RESTRICCIO = 1, TORN = 2, FONT_POU=3;
    
    private String id;
    private int classe;
    private ArrayList<Integer> arestes;
    private ArrayList<String> doctors_rel ;
    private int numMaxRestr = -1; // nomes serveix en el cas del vertex MAX
    private boolean visitat = false; // s'utilitzara per el recorregut del graf
    private Object obj = null;
    
    public Vertex(String identif, int c) throws Error{
        id = identif;
        doctors_rel = new ArrayList<String>();
        arestes = new ArrayList<Integer>();
        if (c == DOCTOR || c == RESTRICCIO || c == TORN || c == FONT_POU){
            classe = c;
        }
        else{
            throw new Error("La classe del vertex no es correcta");
        }
    }
    
    
    public Vertex(String identif, int c, Object o) throws Error{
        id = identif;
        doctors_rel = new ArrayList<String>();
        arestes = new ArrayList<Integer>();
        obj = o;
        if (c == DOCTOR || c == RESTRICCIO || c == TORN || c == FONT_POU){
            classe = c;
        }
        else{
            throw new Error("La classe del vertex no es correcta");
        }
    }
    
    public void afegir_aresta(int aresta){
        if (!arestes.contains(aresta))arestes.add(aresta); 
    }
    
    public int getClasse(){
        return classe;
    }
    
    public ArrayList<Integer> getArestes(){
        return arestes; 
    }
    
    public void elimina_aresta(int aresta){
        arestes.remove(aresta);
    }
    
    public String getId(){
        return id;
    }
    
    public int getNumDocRelacionats(){
        return doctors_rel.size();
    }
    
    public ArrayList<String> getDoctorsRel(){
        return doctors_rel;
    }
    
    public void addDoctorRel(String idDoc){
        if(!doctors_rel.contains(idDoc)) doctors_rel.add(idDoc);
    }
    
    public void rmDoctorRel(String idDoc){
        if(doctors_rel.contains(idDoc)) doctors_rel.remove(idDoc);
    }
    
    public void setNumMaxRestr(int n){
        numMaxRestr = n; 
    }
    
    public int getNumMaxRestr(){
        return numMaxRestr;
    }
    
    public boolean getVisitat(){
        return visitat;
    }
    
    public void setVisitat(boolean v){
        visitat = v;
    }
    
    public Object getObjecte(){
        return obj;
    }
}