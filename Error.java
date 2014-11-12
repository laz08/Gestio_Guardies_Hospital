/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

public class Error extends Exception{
    
    /**
     * Crea una Excepció amb el missatge introduit per parametres
     * @param error String amb el missatge que es vol mostrar en el cas que salti l'excepció
     */
    public Error (String error){
        super(error);
    }
}
