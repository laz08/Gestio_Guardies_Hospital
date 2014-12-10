package prop;

import java.util.ArrayList;

public class Vertex {
    private String id;
    private int classe;
    public static final int DOCTOR = 0, RESTRICCIO = 1, TORN = 2, FONT_POU=3, MAX = 4;
    private int capacitat_acumulada = 0; // capacitat total que ha d'entrar al vertex
    private ArrayList<Integer> arestes;
    private ArrayList<String> doctors_rel = new ArrayList<String>();
    
    public Vertex(String identif, int c) throws Error{
        id = identif;
        arestes = new ArrayList<Integer>();
        if (c == DOCTOR || c == RESTRICCIO || c == TORN || c == FONT_POU || c == MAX){
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
    
    public void setCapacitatAcumulada(int ca){
        capacitat_acumulada = ca;
    }
 
    public int getCapacitatAcumulada(){
        return capacitat_acumulada;
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
}