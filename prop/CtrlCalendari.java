package prop;

import java.util.ArrayList;
import java.util.GregorianCalendar;

//string1.compareTo(String2); Si 0: son iguales, si 1: string1 < String2, si -1: String1 > String2

public class CtrlCalendari {
	
	private static ArrayList<Calendari> llcalendaris = new ArrayList<Calendari>();
	
	public void afegir_calendari(Calendari c) {
		if (llcalendaris.isEmpty()) llcalendaris.add(c);
		else {
			int i=0;
			while(llcalendaris.get(i).getId()<c.getId()) ++i;
			if(llcalendaris.get(i).getId()==c.getId()) System.out.println("El calendari amb aquest id ja existeix\n");
			else llcalendaris.add(i,c);
		}
	}
	public void eliminar_calendari(int id) {
		int i=0;
		while(i<llcalendaris.size() && llcalendaris.get(i).getId()<id) ++i;
		if(llcalendaris.get(i).getId()==id) llcalendaris.remove(i);
		else System.out.println("No existeix cap calendari amb aquest id\n");
	}
	
	public static Calendari consultar_calendari(int id) {
		int i=0;
		while(i<llcalendaris.size() && llcalendaris.get(i).getId()<id) ++i;
		if(i<llcalendaris.size() && llcalendaris.get(i).getId()==id) return llcalendaris.get(i);
		else System.out.println("No existeix cap calendari amb aquest id\n");
		return null;
	}
	
	public void afegir_torn(Torn t, int id_plt) {
		int i=0;
		while(i<llcalendaris.size() && llcalendaris.get(i).getId_plantilla()!=id_plt) ++i;
		if(i<llcalendaris.size() && llcalendaris.get(i).getId_plantilla()==id_plt) {
			ArrayList<Torn> lltorns = llcalendaris.get(i).getTorns();
			//comprovem que el torn que es vol afegir no es solapi amb els existents
			int pos = posicio_torn(t,lltorns);
			if(pos!=-1)lltorns.add(pos,t);
		}
		else System.out.println("No existeix cap calendari per aquesta plantilla\n");
	}
	public void afegir_torn_calendari(Torn t, Calendari cl) {
		ArrayList<Torn> lltorns = cl.getTorns();
		//comprovem que el torn que es vol afegir no es solapi amb els existents
		int pos = posicio_torn(t,lltorns);
		if(pos!=-1)lltorns.add(pos,t);
	}
	
	public boolean existeix_torn(Torn t, int id_plt) {
		int i=0;
		while(llcalendaris.get(i).getId_plantilla()!=id_plt) ++i;
		ArrayList<Torn> lltorns = llcalendaris.get(i).getTorns();
		while(lltorns.get(i).getData_inici().before(t.getData_inici())) ++i;
		return(mateix_torn(lltorns.get(i),t));
	}
	
	public Torn torn_data(GregorianCalendar d_i, Calendari cl) {
		int i=0;
		while(cl.getTorns().get(i).getData_inici().before(d_i)) ++i;
		if(cl.getTorns().get(i).getData_inici().equals(d_i)) return cl.getTorns().get(i);
		else System.out.println("No existeix el torn");
		return null;
	}
	
	public void modificar_torn(Torn t, Torn t_nou, Calendari cl){
			//comprovem si el torn nou té data diferent, si es així afegim el torn modificat i eliminem el torn anterior
			if (t.getData_inici()!=t_nou.getData_inici() || t.getData_fi()!=t_nou.getData_fi()) {
				afegir_torn(t_nou,cl.getId_plantilla());
				eliminar_torn(t,cl.getId_plantilla());
			}
			//sino modifica la data només canviem els atributs 
			else {
				if (t.getN_min_doc()!=t_nou.getN_min_doc()) t.setN_min_doc(t_nou.getN_min_doc());
				if (t.getDoc_assignats()!=t_nou.getDoc_assignats()) t.setDoc_assignats(t_nou.getDoc_assignats());
				if (t.getPercent_sou()!=t_nou.getPercent_sou()) t.setPercent_sou(t_nou.getPercent_sou());
			}
	}
	
	public void eliminar_torn(Torn t, int id_plt)  {
		int i=0;
		while(llcalendaris.get(i).getId_plantilla()!=id_plt) ++i;
		ArrayList<Torn> lltorns = llcalendaris.get(i).getTorns();
		int j=0;
		while (lltorns.get(j).getData_inici().compareTo(t.getData_inici())==1) ++j;
		if (mateix_torn(lltorns.get(j),t)) lltorns.remove(j);
		else System.out.println("No existeix el torn proporcionat");
	}
	
	
	public int posicio_torn(Torn t, ArrayList<Torn> torns) {
		GregorianCalendar d_inici = t.getData_inici();
		GregorianCalendar d_fi = t.getData_fi();
		int i=0;
		while (i!=-1 && i<torns.size() && torns.get(i).getData_inici().before(d_fi)) {
			if (d_inici.before(torns.get(i).getData_inici()) || (d_inici.before(torns.get(i).getData_fi()))){
				System.out.println("El torn es solapa amb un altre torn ja existent");
				i=-1;
			}
			else ++i;
		}
		return i;
	}
	
	public boolean mateix_torn(Torn t1, Torn t2) {
		if(t1.getData_inici().compareTo(t2.getData_inici())!=0 || t1.getData_fi().compareTo(t2.getData_fi())!=0 || t1.getN_min_doc()!=t2.getN_min_doc() || t1.getDoc_assignats()!=t2.getDoc_assignats() || t1.getPercent_sou()!=t2.getPercent_sou()) {
			return false;
		}
		return true;
	}
	
	public void escriure_plantilles() {
		for(int i=0; i<llcalendaris.size(); i++) {
			System.out.println(llcalendaris.get(i).getId());
			System.out.println(llcalendaris.get(i).getId_plantilla()); 
		}
		
	}
	
}