/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;


public class CtrlPlantilla {

    private ArrayList<Plantilla> Cjt_plantilla = new ArrayList<Plantilla>();
    void CtrlPlantilla(){

    }



    /**
     * Afegeix un doctor a una plantilla determinada ordenat alfabeticament per nom plantilla
     * @param d Doctor que es vol introduir
     * @param p Plantilla a la que es vol introduir el doctor
     * pre: Doctor amb dni = dni existeix a l'hospital
     * post: El doctor de l'hospital amb dni = dni pertany ara a la plantilla amb nom = id_plantilla
     */
    public void afegirDoctor(String dni, String id_plantilla) throws Error {
        CtrlHospital CtrlDoc = new CtrlHospital();
        Doctor d = consulta_doctor(dni);
        //COMPROVAM QUE EL DOCTOR NO ESTIGUI DINS CAP ALTRA PLANTILLA
        for(int i=0; i<num_plantilles(); i++){
            p = consulta_plantilla(i); //retorna la plantilla que correspon a la posicio i
            llistaDoctors = p.get_llista_doctors(); 
            Doctor d1;
            for(int e=0; e<llistaDoctors.size(); e++){
                d1 = (Doctor) llistaDoctors.get(e);
                if(d1.getDni() == d.getDni()){
                    throw new Error("El doctor ja esta assignat a una plantilla");
                }
            }
        }
        //INSERIM EL DOCTOR A LA PLANTILLA ORDENAT ALFABETICAMENT
        p = consulta_plantilla(id_plantilla);
        llistaDoctors = p.get_llista_doctors();
        String nomD = d.getNom();
        int ascii = (int) nomD.charAt(0); // obtenim el valor decimal del caracter
        if (ascii >= 97) { // si es una lletra minuscula es retorna el valor equivalent amb majuscula
            ascii -= 32;
        }
        Doctor dLlista;
        if (llistaDoctors.isEmpty()) {
            llistaDoctors.add(d);
        } else {
            for (int i = 0; i < llistaDoctors.size(); i++) {
                dLlista = llistaDoctors.get(i);
                String nomDllista = dLlista.getNom();
                int caracter = (int) nomDllista.charAt(0);
                if (caracter >= 97) {
                    caracter -= 32;
                }
                if (ascii > caracter) {
                    llistaDoctors.add(i, d);
                }
            }
        }
    }

    /**
     * Elimina un determinat doctor de la plantilla
     * @param d Doctor que es vol eliminar de la plantilla
     * @param p Plantilla a la que es fa referència
     * @throws Error 
     */
    public void treureDoctor(String dni, String id_plantilla) throws Error{
        Doctor d = consulta_doctor(dni);
        Plantilla p= consulta_plantilla(id_plantilla);
        ArrayList llista = p.get_llista_doctors();
        if(llista.isEmpty()) throw new Error("La llista es buida");
        llista.remove()
        llista.remove(d);
    }
    
    /**
     * Retorna un valor boolea que indica si ja existeix un doctor determinat a la plantilla
     * @param dni DNI del doctor
     * @param id_plantilla Identificador de la plantilla
     * @return
     */
    public boolean existeixDoctor(String dni, String id_plantilla) {
        Doctor d;
        Plantilla p = consulta_plantilla();
        ArrayList llista = p.get_llista_doctors();
        for (int i = 0; i < llista.size(); i++) {
            d = (Doctor) llista.get(i);
            if (d.getdni() == dni) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna la posició de la plantilla que ocupa un doctor determinat, en cas que no existeixi el doctor, retorna -1
     * @param dni DNI del doctor
     * @param id_plantilla Identificador de la plantilla que es vol consultar
     * @return Posició que ocupa el doctor
     */
    public int existeixDoctor_pos(String dni, String id_plantilla) {
        Doctor d;
        Plantilla p = consulta_plantilla(id_plantilla);
        ArrayList llista = p.get_llista_doctors();
        for (int i = 0; i < llista.size(); ++i) {
            d = (Doctor) llista.get(i);
            if (d.getdni() == dni) {
                return i;
            }
        }
        return -1; 
    }

    /**
     * Donada una posició i una plantilla, retorna el doctor al que fa referència aquesta posició
     * @param pos Posició dins la plantilla
     * @param id_plantilla Identificador de la plantilla
     * @return Doctor al que es fa referència amb la posicio i la plantilla
     */
    public Doctor consultarDoctor(int pos, String id_plantilla) throws Error {
        Plantilla p = consulta_plantilla(id_plantilla);
        ArrayList llista = p.get_llista_doctors();
        if(llista.size()<=pos) throw new Error("No existeix la posició indicada dins la plantilla");
        return llista.get(pos);

    }
}
