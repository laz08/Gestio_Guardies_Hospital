/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;

public class Doc_Res {

    private static ArrayList<ArrayList<String>> assig = new ArrayList<ArrayList<String>>();

    public static void relaciona(String doc, int res) throws Error {
        if (res < assig.size()) {
            if (assig.get(res).contains(doc)) {
                throw new Error(
                        "La relació entre el doctor i la restricció ja existeix");
            } else {
                assig.get(res).add(doc);
            }
        } else {
            ArrayList llistaDoc = new ArrayList();
            llistaDoc.add(doc);
            assig.add(res, llistaDoc);
        }
    }

    public static void elimina(String doc, int res) throws Error {
        if(res < assig.size()){
            assig.get(res).remove(doc);
        }
        else{
            throw new Error("La relació entre el doctor i la restricció no existeix");
        }
    }
    
    public static ArrayList<Integer> getRestriccions(String doc){
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i = 0; i< assig.size(); i++){
            if(assig.get(i).contains(doc)) res.add(i);
        }
        return res;
    }
}
