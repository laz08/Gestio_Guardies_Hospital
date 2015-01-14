/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.io.File;
import java.util.ArrayList;

public class Doc_Res{

    private static ArrayList<ArrayList<String>> assig = new ArrayList<ArrayList<String>>();

    /**
     * Relaciona el doctor amb dni doc i la restriccio res
     * @param doc
     * @param res
     */
    public static void relaciona(String doc, int res){
        if (res < assig.size()) {
            if (assig.get(res).contains(doc)) {
               /* throw new Error(
                        "La relació entre el doctor i la restricció ja existeix");
                        */
            } else {
                assig.get(res).add(doc);
            }
        } else {
            ArrayList<String> llistaDoc = new ArrayList<String>();
            llistaDoc.add(doc);
            for(int i=assig.size(); i< res; i++){
                assig.add(i, new ArrayList<String>());
            }
            assig.add(llistaDoc);
        }
    }

    /**
     * Esborra la relació entre el doctor amb dni doc i la restricció res
     * @param doc
     * @param res
     */
    public static void elimina(String doc, int res) {
        if(res < assig.size()){
            assig.get(res).remove(doc);
        }
        else{
          //  throw new Error("La relació entre el doctor i la restricció no existeix");
        }
    }

    /**
     * Retorna el llistat de restriccions associades al doctor doc
     * @param doc
     * @return
     */
    public static ArrayList<Integer> getRestriccions(String doc){
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i = 0; i< assig.size(); i++){
            if(assig.get(i).contains(doc)) res.add(i);
        }
        return res;
    }

    /**
     * Retorna les restriccions no associades al doctor doc
     * @param doc
     * @return
     */
    public static ArrayList<Integer> getRestriccionsNoAssociades(String doc){
        //Restriccions associades al doctor
        ArrayList<Integer> res = getRestriccions(doc);
        ArrayList<Integer> resNoAss  = new ArrayList<Integer>();

        //Ens quedem amb les no associades
        int s = CtrlRestriccio.consulta_llista_res().size();
        for(int i = 0; i < s; ++i){
            boolean associada = false;
            for (int j = 0; j < res.size() && !associada; ++j) {
                if (i == res.get(j)) associada = true;
            }
            if(!associada) resNoAss.add(i);
        }
        return resNoAss;
    }

    /**
     * Guarda les relacions
     * @param f
     */
    public static void guardar(File f) {
    	String content = "";
  		for (int i = 0; i < assig.size(); i++) {
  			for (int j = 0; j < assig.get(i).size(); j++) {
  				content += assig.get(i).get(j) + "\n";
  			}
  			content += "Fi"+"\n";
  		}
    	CtrlPersistencia.guardar(content, f);
    }

    /**
     * Carrega les relacions
     * @param f
     * @throws NumberFormatException
     * @throws Error
     */
    public static void carregar(File f) throws NumberFormatException, Error {
    	String content = CtrlPersistencia.carregar(f);
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
