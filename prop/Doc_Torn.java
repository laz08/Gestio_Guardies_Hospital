/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;

public class Doc_Torn {
    private static ArrayList<Relacio> torns = new ArrayList<Relacio>();
    private static ArrayList<Relacio> doctors = new ArrayList<Relacio>();
    
    public static void addRel(Doctor d, Torn t){
        Relacio rt = new Relacio(t);
        Relacio rd = new Relacio(d);
        
        boolean existeixt = false;
        int post = 0;
        while (post < torns.size() && !existeixt){
            if(t.equals(torns.get(post).getObj())){
                existeixt = true;
            }
            if (!existeixt) post++;
        }
        
        
        boolean existeixd = false;
        int posd = 0;
        while (posd < torns.size() && !existeixd){
            if(t.equals(torns.get(posd).getObj())){
                existeixd = true;
            }
            if (!existeixd) posd++;
        }
        
        
        if(existeixd){
            doctors.get(posd).addRelacio(rt);
        }
        else{
            rd.addRelacio(rt);
            doctors.add(rd);
        }
        
        
    }
    
    public static void removeRel(Doctor d, Torn t){
        boolean existeixt = false;
        int post = 0;
        while (post < torns.size() && !existeixt){
            if(t.equals(torns.get(post).getObj())){
                existeixt = true;
            }
            if (!existeixt) post++;
        }
        if(existeixt){
            torns.get(post).rmRelacio(d);
        }
        
        boolean existeixd = false;
        int posd = 0;
        while (posd < torns.size() && !existeixd){
            if(t.equals(torns.get(post).getObj())){
                existeixd = true;
            }
            if (!existeixd) posd++;
        }
        if(existeixd){
            doctors.get(post).rmRelacio(t);
        }
    }
    
    public static void removeAll(){
        torns.clear();
        doctors.clear();
    }
    
    public static ArrayList<Torn> getRel(Doctor d){
        ArrayList<Torn> ts = new ArrayList<Torn>();
        boolean existeixd = false;
        int posd = 0;
        while (posd < torns.size() && !existeixd){
            if(d.equals(torns.get(posd).getObj())){
                existeixd = true;
            }
            if (!existeixd) posd++;
        }
        if(existeixd){
            ArrayList<Relacio> rt = torns.get(posd).getRelacionats();
            for(int i=0; i<rt.size(); i++){
                ts.add((Torn) rt.get(i).getObj());
            }
        }
        return ts;
    }
    
    
    public static ArrayList<Doctor> getRel(Torn t){
        ArrayList<Doctor> ts = new ArrayList<Doctor>();
        boolean existeix = false;
        int pos = 0;
        while (pos < doctors.size() && !existeix){
            if(t.equals(doctors.get(pos).getObj())){
                existeix = true;
            }
            if (!existeix) pos++;
        }
        if(existeix){
            ArrayList<Relacio> rd = torns.get(pos).getRelacionats();
            for(int i=0; i<rd.size(); i++){
                ts.add((Doctor) rd.get(i).getObj());
            }
        }
        return ts;
    }
    
}
