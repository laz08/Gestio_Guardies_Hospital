
package prop;

import java.io.File;
import java.util.TreeSet;


public class CtrlPlantilla {

    private static TreeSet<Plantilla> Cjt_plantilles = new TreeSet<Plantilla>(new compNomP());
    private static Plantilla plantillaActual = null;


    // ------ PLANTILLES EN GENERAL ------

    /**
     * Pre: No existeix una plantilla amb nom = nom_plantilla
     * Post: Cjt_Plantilles ara té una nova plantilla amb el nom = nom_plantilla
     * @param nom_plantilla
     */
    public static void creariAfegirPlantilla(String nom_plantilla){
        Plantilla p = new Plantilla(nom_plantilla);
        Cjt_plantilles.add(p);
    }

    //Pre:  -
    //Post: S'ha esborrat del conjunt de plantilles la plantilla amb nom nom_p
    public static void esborrarPlantilla(String nom_p){
        Plantilla p = new Plantilla(nom_p);
        //Creem Dummy i el passem
        for(Doctor doc: p.getLlistaDoctorsNom()) {
           doc.setActiu(false);
        }
        Cjt_plantilles.remove(p);
    }

    //Pre:  -
    //Post: Retorna un booleà indicant si una plantilla al conjunt de plantilles amb el nom = nom_p existeix
    public static boolean existeixPlantilla(String nom_p){
        Plantilla aux = new Plantilla(nom_p);
        return Cjt_plantilles.contains(aux);
    }

    public static TreeSet<Plantilla> getCjt_plantilles(){
        return Cjt_plantilles;
    }

    // ------ PLANTILLA EN CONCRET ------

    //Pre: Existeix plantilla amb nom_p
    //Post: plantillaActual passa a ser la plantilla amb nom_p
    public static void setPlantillaActual(String nom_p){
       Plantilla p = new Plantilla(nom_p);
        p = Cjt_plantilles.ceiling(p);
        plantillaActual = p;
    }

    public static Plantilla getPlantillaActual(){
        return plantillaActual;
    }

    public static void plantillaActualANull(){
        plantillaActual = null;
    }

    public static Plantilla consultarPlantilla(String nom_p){
        Plantilla p = new Plantilla(nom_p);
        return Cjt_plantilles.ceiling(p);
    }

    public static int num_plantilles(){
        return Cjt_plantilles.size();
    }




    /**
     *
     * @param dni Doctor que es vol introduir
     * @param nom_plantilla Plantilla a la que es vol introduir el doctor
     * pre: Doctor amb dni = dni existeix a l'hospital i no està a cap plantilla, existeix plantilla amb id = id_plantilla
     *          i també existeix doctor amb dni = dni
     * post: El doctor de l'hospital amb dni = dni pertany ara a la plantilla amb nom = id_plantilla
     */
    public static void afegirDoctorAPlantilla(String dni, String nom_plantilla){
        Doctor doc = CtrlHospital.getDoctor(dni);
        doc.setActiu(true);
        Plantilla p = Cjt_plantilles.ceiling(new Plantilla(nom_plantilla));
        p.getLlistaDoctorsDNI().add(doc);
        p.getLlistaDoctorsNom().add(doc);
    }

    /**
     * PRE: Doctor no està actiu (no pertany a cap plantilla)
     * POST: Doctor ara està actiu i pertany a la plantilla p
     * @param doc
     * @param p
     */
    public static void afegirDoctorAPlantilla(Doctor doc, Plantilla p){
        doc.setActiu(true);
        p.getLlistaDoctorsDNI().add(doc);
        p.getLlistaDoctorsNom().add(doc);
    }


    //Pre: Doctor existeix i plantilla també, i doc està en la plantilla
    //Post: S'ha esborrat doctor de la plantilla indicada.
    public static void esborrarDoctordePlantilla(String dni, String id_plantilla){
        Doctor doc = CtrlHospital.getDoctor(dni);
        doc.setActiu(false);
        Plantilla p = Cjt_plantilles.ceiling(new Plantilla(id_plantilla));
        p.getLlistaDoctorsNom().remove(doc);
        p.getLlistaDoctorsDNI().remove(doc);
    }

    /**
     * PRE: Doctor doc pertany a la plantilla
     * POST: Doctor ja no pertany a cap plantilla i està inactiu
     * @param doc
     * @param plantilla
     */
    public static void esborrarDoctorDePlantilla(Doctor doc, Plantilla plantilla){
        doc.setActiu(false);
        plantilla.getLlistaDoctorsDNI().remove(doc);
        plantilla.getLlistaDoctorsNom().remove(doc);
    }

    //Retorna si el doctor està assignat a una plantilla
    public static boolean docTePlantilla(String dni){
        return CtrlHospital.getDoctor(dni).isActiu();
    }


    //Pre: Existeix plantilla amb id = id_plantilla, existeix doctor amb dni = dni
    //Post: Retorna si el doctor està a la plantilla amb id_plantilla.
    public static boolean docEnPlantilla(String dni, String id_plantilla){
            Plantilla p = Cjt_plantilles.ceiling(new Plantilla(id_plantilla));
            return p.getLlistaDoctorsDNI().contains(new Doctor(dni));
    }

    public static boolean existeixCalendari(String plt) {
		Plantilla aux = new Plantilla(plt);
		return Cjt_plantilles.contains(aux);
    }
    
    public static void crearCalendari(String plt, int any, int anyf) {
    	setPlantillaActual(plt);
    	Plantilla p = getPlantillaActual();
    	crearCalendariAssociatAPlantilla(p,any,anyf);
    }
    
    public static void eliminarCalendari(String plt) {
    	setPlantillaActual(plt);
    	Plantilla p = getPlantillaActual();
    	desassociarDeCalendari(p);
    }
    
    public static void crearCalendariAssociatAPlantilla(Plantilla p, int any, int anyf){
        // !!!!!!!!!! ================= !!!!!!!!!!!!!!!!!!!!!
        Calendari C = CtrlCalendari.CrearIAfegirCalendari(p.getNomPlantilla(), any, anyf);
        p.set_calendari_asoc(C);
    }

    public static void desassociarDeCalendari(Plantilla p){
        Calendari C = p.get_calendari_asoc();
        C = null;
        p.set_calendari_asoc(null);
    }

    /**
     * Retorna tots els doctors de la plantilla plt
     * @param plt
     * @return
     */
    public static String getPlantillaespecifica(String plt) {
    	String content ="";
    	Plantilla p = consultarPlantilla(plt);
    	for(Doctor doc: p.getLlistaDoctorsNom()) {
    		content += doc.getdni() + " " + doc.getNom() + " " + doc.getCognom1() + " " + doc.getCognom2() + "\n";
    	}
    	return content;
    	
    }
    
    public static String getLlista_plantilles() {
    	String content = "";
  		for (Plantilla p: Cjt_plantilles) {
  			content += p.getNomPlantilla() + "\n"; 
  		}
    	return content;
    }

    public static void guardar(File f) {
        String content = "";
        String fi = "Fi";
        for (Plantilla p: Cjt_plantilles) {
            content += p.getNomPlantilla() + "\n"; //+ p.get_calendari_asoc().getId() + "\n";

            for (Doctor doc: p.getLlistaDoctorsDNI()) {
                content = content + doc.getdni() + " " + doc.getNom() + " " + doc.getCognom1() + " "
                        + doc.getCognom2() + " " + doc.getSou() + " " + doc.getTelefon() + " " + doc.getCorreu() + "\n";
            }
            content += (fi + "\n");
        }
        CtrlPersistencia.guardar(content, f);
    }

    public static void carregar(File f) {
        String content = CtrlPersistencia.carregar(f);
        String separadors = "[ \n]";
        String[] separat = content.split(separadors);
        int i = 0;
        String np = "";
        String fi = "Fi";
        while (i < separat.length) {
            np = separat[i];
            creariAfegirPlantilla(np);
            ++i;
            String dnid = "";
            while (!separat[i].equals(fi)) {
                dnid = separat[i];
                CtrlHospital.creariAfegirDoctor(separat[i], separat[++i], separat[++i], separat[++i], Integer.parseInt(separat[++i]), Integer.parseInt(separat[++i]), separat[++i]);
                afegirDoctorAPlantilla(dnid, np);
                ++i;
            }
            ++i;
        }
    }


}

