/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;


public class CtrlPlantilla {

    //private static ArrayList<Plantilla> Cjt_plantilles = new ArrayList<Plantilla>();
    private static int plantillaActual = -1;

    // ------ PLANTILLES EN GENERAL ------

    //Pre: No existeix una plantilla amb nom = nom_plantilla
    //Post: Cjt_Plantilles ara té una nova plantilla amb el nom = nom_plantilla
    public static void creariAfegirPlantilla(String nom_plantilla){
        Plantilla p = new Plantilla(nom_plantilla);
        Cjt_plantilles.add(p);
    }

    //Pre: Existeix una plantilla a la posició pos, i pos és una posició vàlida
    //Post: S'ha esborrat del conjunt de plantilles la plantilla en la posició pos
    public static void esborrarPlantilla(int pos){
        Cjt_plantilles.remove(pos);
    }

    //Pre:
    //Post: Si existeix una plantilla al conjunt de plantilles amb el nom = n, retorna la posició
    //      en la que es troba dins el conjunt. Altrament, retorna -1 per indicar que no existeix.
    public static int existeixPlantilla(String n){
        Plantilla aux;
        for (int i = 0; i < Cjt_plantilles.size(); ++i){
            aux = (Plantilla) Cjt_plantilles.get(i);
            if(aux.getNomPlantilla().equals(n)){
                return i;
            }
        }
        return -1;
    }


    // ------ PLANTILLA EN CONCRET ------

    public static void setPlantillaActual(int p){
       plantillaActual = p;
    }

    public static Plantilla getPlantillaActual(){
        return Cjt_plantilles.get(plantillaActual);
    }


    public static String getidPlantillaActual(){
        return Cjt_plantilles.get(plantillaActual).getNomPlantilla();
    }

    public static Plantilla consultarPlantilla(int pos){
        return Cjt_plantilles.get(pos);
    }

    public static int num_plantilles(){
        return Cjt_plantilles.size();
    }

    /**
     * Afegeix un doctor a una plantilla determinada ordenat alfabeticament per nom plantilla
     * @param dni Doctor que es vol introduir
     * @param id_plantilla Plantilla a la que es vol introduir el doctor
     * pre: Doctor amb dni = dni existeix a l'hospital i no està a cap plantilla, existeix plantilla amb id = id_plantilla
     *          i també existeix doctor amb dni = dni
     * post: El doctor de l'hospital amb dni = dni pertany ara a la plantilla amb nom = id_plantilla
     */
    public static void afegirDoctorAPlantilla(String dni, String id_plantilla){
        Plantilla p;
        Doctor doc = null;

        //Busquem doctor a afegir a la plantilla
        boolean doc_trobat = false;
        for (int i = 0; i < Hospital.getHospital().size() && !doc_trobat; ++i){
            if(Hospital.getHospital().get(i).getdni().equals(dni)){
                doc = (Doctor) Hospital.getHospital().get(i);
                doc.setActiu(true);
                doc_trobat = true;
            }
        }

        //Busquem plantilla on afegir doctor i afegim doctor
        for (int i = 0; i < Cjt_plantilles.size(); ++i){
            p = Cjt_plantilles.get(i);
            if (p.getNomPlantilla().equals(id_plantilla)){
                //Sabem a quina plantilla afegir. Inserir ordenant.
                boolean trobat_lloc = false;
                for (int j = 0; j < p.getLlistaDoctors().size() && !trobat_lloc; ++j){
                    int v = p.getLlistaDoctors().get(j).getNom().compareTo(doc.getNom());
                    //Doc a inserir és més gran
                    if (v < 0){
                        //Mirem si a la següent posició hi ha un valor més gran
                        int v2 = p.getLlistaDoctors().get(j+1).getNom().compareTo(doc.getNom());
                        if (v >= 0){
                            //Hem trobat el seu lloc
                            p.getLlistaDoctors().add(j, doc);
                            trobat_lloc = true;
                        }
                    }
                    else if (v == 0){
                        p.getLlistaDoctors().add(j, doc);
                        trobat_lloc = true;
                    }
                }
                if (!trobat_lloc) p.getLlistaDoctors().add(doc);
            }
        }
    }


    //Pre: Doctor existeix i plantilla també, i doc està en la plantilla
    //Post: S'ha esborrat doctor de la plantilla indicada.
    public static void esborrarDoctordePlantilla(String dni, String id_plantilla){
        Plantilla p;
        boolean esborrat = false;
        for (int i = 0; i < Cjt_plantilles.size() && !esborrat; ++i){
            p = Cjt_plantilles.get(i);
            for (int j = 0; j < p.getLlistaDoctors().size() && !esborrat; ++j){
                if (p.getLlistaDoctors().get(j).getdni().equals(dni)){
                    esborrat = true;
                    p.getLlistaDoctors().remove(j);
                }
            }
        }
    }

    //Retorna si el doctor està assignat a una plantilla
    public static boolean docTePlantilla(String dni){
        Doctor doc;
        for (int i = 0; i < Hospital.getHospital().size(); ++i){
            doc = (Doctor) Hospital.getHospital().get(i);
            if(doc.getdni().equals(dni)){
                return doc.isActiu();
            }
        }
        return false;
    }


    //Pre: Existeix plantilla amb id = id_plantilla, existeix doctor amb dni = dni
    //Post: Retorna si el doctor està a la plantilla amb id_plantilla.
    public static boolean docEnPlantilla(String dni, String id_plantilla){
            Plantilla p;
            for (int i = 0; i < Cjt_plantilles.size(); ++i){
                p = Cjt_plantilles.get(i);
                if(p.getNomPlantilla().equals(id_plantilla)){
                    Doctor doc;
                    for(int j = 0; j < p.getLlistaDoctors().size(); ++j){
                        doc = p.getLlistaDoctors().get(j);
                        if (doc.getdni().equals(dni)) return true;
                    }
                    return false;
                }
            }
        return false;
    }



}

