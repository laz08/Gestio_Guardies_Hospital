package prop;

import java.util.ArrayList;
import java.util.GregorianCalendar;

//string1.compareTo(String2); Si 0: son iguales, si 1: string1 < String2, si -1: String1 > String2

public class CtrlCalendari {
	
	public void afegir_torn(Torn t, int id_plt) throws Error {
		Calendari c;
		c = consultar_calendari(id_plt); //retorna calendari 
		ArrayList <Torn> llTorns = c.getTorns();
		//comprovem que el torn que es vol afegir no es solapi amb els existents
		int pos = posicio_torn(t,llTorns);
		int i;
		for (i=pos; i < llTorns.size(); i++) {
			Torn taux = llTorns.get(i);
			llTorns.add(i,t);
			t=taux;
		}
		llTorns.add(i,t);
	}
	
	public void modificar_torn(Torn t, Torn t_nou, int id_plt) throws Error {
		//comprovem si el torn nou té data diferent, si es així afegim el torn modificat i eliminem el torn anterior
		if (t.getData_inici()!=t_nou.getData_inici() || t.getData_fi()!=t_nou.getData_fi()) {
			afegir_torn(t_nou,id_plt);
			eliminar_torn(t,id_plt);
		}
		//sino modifica la data només canviem els atributs 
		else {
			if (t.getN_min_doc()!=t_nou.getN_min_doc()) t.setN_min_doc(t_nou.getN_min_doc());
			if (t.getDoc_assignats()!=t_nou.getDoc_assignats()) t.setDoc_assignats(t_nou.getDoc_assignats());
			if (t.getPercent_sou()!=t_nou.getPercent_sou()) t.setPercent_sou(t_nou.getPercent_sou());
		}
	}
	
	public void eliminar_torn(Torn t, int id_plt) throws Error {
		Calendari c;
		c = consultar_calendari(id_plt); //retorna calendari 
		ArrayList <Torn> llTorns = c.getTorns();
		int i=0;
		while (llTorns.get(i).getData_inici().compareTo(t.getData_inici())==1) ++i;
		if (mateix_torn(llTorns.get(i),t)) llTorns.remove(i);
		else throw new Error("No existeix el torn proporcionat");
	}
	
	public Calendari consultar_calendari(int id_plt) throws Error {
		Cjt_calendaris c = new Cjt_calendaris();
		ArrayList<Calendari> calendaris = c.get_llista_calendaris();
		int i =0;
		while (i<calendaris.size() && calendaris.get(i).getId_plantilla()!=id_plt) ++i;
		if(calendaris.get(i).getId_plantilla()==id_plt) return calendaris.get(i);
		else throw new Error("No hi ha cap plantilla amb el identificador proporcionat");
	}
	
	public int posicio_torn(Torn t, ArrayList<Torn> torns) throws Error {
		GregorianCalendar d_inici = t.getData_inici();
		GregorianCalendar d_fi = t.getData_fi();
		int i=0;
		while (i<torns.size() && torns.get(i).getData_inici().compareTo(d_fi)!=-1 ) {
			if (d_inici.compareTo(torns.get(i).getData_inici())==-1 || torns.get(i).getData_fi().compareTo(d_inici)==-1){
				throw new Error("El torn es solapa amb un altre torn ja existent");
			}
			++i;
		}
		return i;
	}
	
	
	public boolean mateix_torn(Torn t1, Torn t2) {
		if(t1.getData_inici().compareTo(t2.getData_inici())!=0 || t1.getData_fi().compareTo(t2.getData_fi())!=0 || t1.getN_min_doc()!=t2.getN_min_doc() || t1.getDoc_assignats()!=t2.getDoc_assignats() || t1.getPercent_sou()!=t2.getPercent_sou()) {
			return false;
		}
		return true;
	}
	
}