/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;

public class CtrlRestriccio {
    private static ArrayList<Restriccio> restriccions = new ArrayList<Restriccio>();
    
    public static void afegir_res(Restriccio r){
        if(existeix(r) == -1)restriccions.add(r);
    }
    
    public static void elimina_res(Restriccio r) throws Error{
        int pos = existeix(r);
        if (pos != -1) restriccions.remove(pos);
        else throw new Error("No existeix la restricció que es vol eliminar");
    }
    
    public static ArrayList<Restriccio> consulta_llista_res(){
        return restriccions;
    }
    
    public static Restriccio consulta_res(int i){
        return restriccions.get(i);
    }
    
    public static int existeix(Restriccio r){
        int trobat = -1;
        int i=0;
        Restriccio res;
        while(trobat == -1 && i < restriccions.size()){
            res = restriccions.get(i);
            if (res.equals(r)) trobat = i;
            i++;
        }
        return trobat;
    }
    
}
