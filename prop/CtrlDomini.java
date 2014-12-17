package prop;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.TreeSet;
import java.util.ArrayList;

public class CtrlDomini {


    //CASOS D'US


    //-------------HOSPITAL-------------

    public static boolean existeixDoctoraHospital(String d) {
        return CtrlHospital.existeixDoctor(d);
    }

    /**
     * PRE: No existeix cap doctor amb dni = d a l'hospital
     * POST: S'ha creat doctor amb tots els paràmetres a l'hospital
     * @param d
     * @param n
     * @param cg1
     * @param cg2
     * @param s
     * @param telf
     * @param mail
     */
    public static void creaDoctor(String d, String n, String cg1, String cg2, int s, int telf, String mail) {
        CtrlHospital.creariAfegirDoctor(d, n, cg1, cg2, s, telf, mail);
    }
    public static Doctor consultaDoctor(String dni){
        return CtrlHospital.getDoctor(dni);
    }

    /**
     * PRE: Existeix doctor amb aquest dni a l'hospital.
     * POST: Queda esborrat doctor de l'hospital
     * @param dni
     */
    public static void eliminaDoctorHospital(String dni){
        CtrlHospital.eliminarDoctor(dni);
    }
    public static TreeSet<Doctor> consultaHospital_dni(){
        return CtrlHospital.getHospital_dni();
    }
    public static TreeSet<Doctor> consultaHospital_nom(){
        return CtrlHospital.getHospital_nom();
    }
    public static void guardarDoctors(){
    	File file = null;
        CtrlHospital.guardar(file);
    }
    public static void carregarDoctors(){
    	File file = null;
        CtrlHospital.carregar(file);
    }

    //-------------PLANTILLA-------------

    public static boolean existeixPlantilla(String nom_p){
        return CtrlPlantilla.existeixPlantilla(nom_p);
    }
    public static void crearPlantilla(String nom_p){
        CtrlPlantilla.creariAfegirPlantilla(nom_p);
    }
    public static void esborraPlantilla(String nom_p){
        CtrlPlantilla.esborrarPlantilla(nom_p);
    }

    public static Plantilla consultaPlantilla(String nom_p){
        return CtrlPlantilla.consultarPlantilla(nom_p);
    }
    public static void afegirDoctorAPlantilla(Doctor doc, Plantilla plantilla){
        CtrlPlantilla.afegirDoctorAPlantilla(doc, plantilla);
    }
    public static void esborrarDoctorDePlantilla(Doctor doc, Plantilla plantilla){
        CtrlPlantilla.esborrarDoctorDePlantilla(doc, plantilla);
    }

    public static void associaCalendariPlantilla(Plantilla p, int any_i, int any_f){
        CtrlPlantilla.crearCalendariAssociatAPlantilla(p, any_i, any_f);
    }
    public static void desassociaCalendariIEsborra(Plantilla p){
        CtrlPlantilla.desassociarDeCalendari(p);
    }
    public static void guardarPlantilles(){


        //CtrlPlantilla.guardar();
    }
    public static void carregarPlantilles(){
        //CtrlPlantilla.carregar();
    }
    public static TreeSet<Plantilla> llistarPlantilles(){
        return CtrlPlantilla.getCjt_plantilles();
    }
    public static void setPlantillaActual(String nom_p){
        CtrlPlantilla.setPlantillaActual(nom_p);
    }
    public static Plantilla getPlantillaActual(){
        return CtrlPlantilla.getPlantillaActual();
    }
    public static void plantillaActualANull(){
        CtrlPlantilla.plantillaActualANull();
    }

    //-------------RESTRICCIONS-------------
    public static void novaRestriccio(String exp){
        CtrlRestriccio.nova_res(exp);
    }

    public static ArrayList<Restriccio> consultaLlistaRestriccions(){
        return CtrlRestriccio.consulta_llista_res();
    }

    public static Restriccio consultaRestriccio(int pos){
        return CtrlRestriccio.consulta_res(pos);
    }
    public static void eliminaRestriccio(Restriccio restriccio){
        CtrlRestriccio.elimina_res(restriccio);
    }

    public static void guardarRestriccions(){
    	File file = null;
        CtrlRestriccio.guardar(file);
    }

    public static void carregarRestriccions(){
        //CtrlRestriccio.carregar();
    }

    //-------------CALENDARI-------------

    public static boolean existeixCalendari(String nom_p){
        return CtrlCalendari.existeixCalendari(nom_p);
    }
    
    public static void crearCalendari(String nom_p, int any_i, int any_f){
        CtrlCalendari.crearIafegirCalendari(nom_p, any_i, any_f);
    }
    
    public static Calendari consultaCalendari(String nom_p){
        return CtrlCalendari.consultarCalendari(nom_p);
    }
    
    public static void modificarDiaFestiu(String nom, GregorianCalendar d, boolean b) {
    	CtrlCalendari.modificarDiaFestiu(nom, d, b);
    }
    
    public static void modificarPercentatge(String nom, GregorianCalendar d, int horari, float p) {
    	CtrlCalendari.modificarPercentatgeTorn(p, d, nom, horari);
    }
    
    public static void modificarMinim(String nom, GregorianCalendar d, int horari, int p) {
    	CtrlCalendari.modificarMinimTorn(p, d, nom, horari);
    }
    
    public static void borrarDia(String nom, GregorianCalendar d) {
    	CtrlCalendari.borrarDia(nom, d);
    }
    
    public static void borrarTorn(String nom, GregorianCalendar d, int horari) {
    	CtrlCalendari.borrarTornHorari(nom, d, horari);
    }
    
    public static void esborrarCalendari(String nom_p){
        CtrlCalendari.eliminarCalendari(nom_p);
    }
    
    public static void guardarCalendaris(){
    	File file = new File("/home/acer/Plantilles.txt");
        CtrlCalendari.guardar(file);
    }
    public static void carregarCalendaris(){
    	File file = new File("/home/acer/Plantilles.txt");
        CtrlCalendari.carregar(file);
    }
    public static TreeSet<Calendari> llistarCalendaris(){
        return CtrlCalendari.getLlcalendaris();
    }
    //-------------CARREGAR-------------
    public static void carregarGeneral(){
    	File file = new File("/home/acer/Plantilles.txt");
        CtrlPlantilla.carregar(file);
        CtrlHospital.carregar(file);
        CtrlCalendari.carregar(file);

    }

    //-------------GUARDAR-------------
    public static void guardarGeneral(){
    	File file = new File("/home/acer/Plantilles.txt");
        CtrlPlantilla.guardar(file);
        CtrlHospital.guardar(file);
        CtrlCalendari.guardar(file);
        CtrlRestriccio.guardar(file);
    }


    //----------ALGORISMES-------------
    public static Graf crear_graf(){
        return CtrlEntrada.crea_graf();
    }
    public static void aplicaEdmondsKarp(Graf graf) throws Error {
        Algorisme alg = new EdmondsKarp();
        alg.setGraf(graf);
        alg.maxFlow();
    }

    public static void aplicaFordFulkerson(Graf graf) throws Error{
        Algorisme alg = new FordFulkerson();
        alg.setGraf(graf);
        alg.maxFlow();
    }

}