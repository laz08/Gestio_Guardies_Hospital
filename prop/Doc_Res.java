/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.io.File;
import java.util.ArrayList;

public class Doc_Res{

    private static ArrayList<ArrayList<String>> assig = new ArrayList<ArrayList<String>>();

    public static void relaciona(String doc, int res) throws Error{
        if (res < assig.size()) {
            if (assig.get(res).contains(doc)) {
                throw new Error(
                        "La relació entre el doctor i la restricció ja existeix");
            } else {
                assig.get(res).add(doc);
            }
        } else {
            ArrayList<String> llistaDoc = new ArrayList<String>();
            llistaDoc.add(doc);
            for(int i=0; i< res; i++){
                assig.add(i, new ArrayList<String>());
            }
            assig.add(llistaDoc);
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
    
    public static void guardar() {
    	String content = "";
  		for (int i = 0; i < assig.size(); i++) {
  			for (int j = 0; j < assig.get(i).size(); j++) {
  				content += assig.get(i).get(j) + "\n";
  			}
  			content += "Fi"+"\n";
  		}
  		File file = new File("/home/acer/doc_res.txt");
    	CtrlPersistencia.guardar(content, file);
    }

    public static void carregar() throws NumberFormatException, Error {
    	File file = new File("/home/acer/doc_res.txt");
    	String content = CtrlPersistencia.carregar(file);
    	String separadors = "[ \n]";
    	String[] separat = content.split(separadors);
    	int i = 0;
    	String res = "";
    	String fi = "Fi";
    	while (i < separat.length) {
    		res = separat[i];
    		++i;
    		while (!separat[i].equals(fi)) {
    			relaciona(res, Integer.parseInt(separat[i]));
    			++i;
    		}
    		++i;
    	}
    	
    }
}
