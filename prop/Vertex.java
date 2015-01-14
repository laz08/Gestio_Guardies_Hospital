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
    
    /**
     * Constructora de la classe.
     * @param identif identifica quin vertex es.
     * @param c identifica quina classa de vertex es.
     * @throws Error
     */
    
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
    
    /**
     * Contructora de la classe.
     * @param identif identif identifica quin vertex es.
     * @param c identifica quina classa de vertex es.
     * @param o passa un objecte que es quedara associat amb aquesta classe
     * @throws Error
     */
    
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
    
    /**
     * Afegeix una aresta al vertex.
     * @param aresta identificador de aresta.
     */
    public void afegir_aresta(int aresta){
        if (!arestes.contains(aresta))arestes.add(aresta); 
    }
    
    /** 
     * Retorna quin tipus de vertex es.
     * @return 
     */
    public int getClasse(){
        return classe;
    }
    
    /** 
     * Retorna les arestes conecten a aquest vertex.
     * @return
     */
    public ArrayList<Integer> getArestes(){
        return arestes; 
    }
    
    /**
     * Elimina l'aresta adjacent a aquest vertex.
     * @param aresta Indentificador d'aresta
     */
    
    public void elimina_aresta(int aresta){
        arestes.remove(aresta);
    }
    
    /**
     * Retorna l'identificador del vertex.
     * @return
     */
    
    public String getId(){
        return id;
    }
    
    /**
     * Retorna el numero de doctors relacionats amb
     * aquest vertex.
     * @return
     */
    
    public int getNumDocRelacionats(){
        return doctors_rel.size();
    }
    
    /**
     * Retorna la llista de tots els doctors
     * relacionats amb aquest vertex.
     * @return
     */
    
    public ArrayList<String> getDoctorsRel(){
        return doctors_rel;
    }
    
    /**
     * Relaciona un doctor amb aquest vertex.
     * @param idDoc Identificador de doctor (DNI)
     */
    
    public void addDoctorRel(String idDoc){
        if(!doctors_rel.contains(idDoc)) doctors_rel.add(idDoc);
    }
    
    /**
     * Elimina la relació d'un doctor amb aquest
     * vertex.
     * @param idDoc Identificador de doctor (DNI)
     */
    
    public void rmDoctorRel(String idDoc){
        if(doctors_rel.contains(idDoc)) doctors_rel.remove(idDoc);
    }
    
    /**
     * Especifica el nombre maxim de restriccions per a
     * aquest vertex.
     * Només s'utilitza si el vertex és de tipus MAX.
     * @param n Numero maxim de restriccions.
     */
    
    public void setNumMaxRestr(int n){
        numMaxRestr = n; 
    }
    
    /**
     * Retorna el nombre maxim de restriccions per a
     * aquest vertex.
     * @return
     */
    
    public int getNumMaxRestr(){
        return numMaxRestr;
    }
    
    /**
     * Indica si el vertex ha estat visitat
     * durant l'execució de lalgorisme.
     * @return
     */
    
    public boolean getVisitat(){
        return visitat;
    }
    
    /**
     * Modifica el parametre visitat
     * del vertex.
     * @param v
     */
    
    public void setVisitat(boolean v){
        visitat = v;
    }
    
    /**
     * Retorna l'objecte associat al vertex.
     * @return
     */
    
    public Object getObjecte(){
        return obj;
    }
}