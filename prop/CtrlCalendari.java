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
			while(llcalendaris.get(i).getId_plantilla()<c.getId_plantilla()) ++i;
			if(llcalendaris.get(i).getId_plantilla()==c.getId_plantilla()) System.out.println("El calendari per aquesta plantilla ja existeix\n");
			else llcalendaris.add(i,c);
		}
	}
	public void eliminar_calendari(int id_plt) {
		if(llcalendaris.isEmpty()) System.out.println("No existeix cap calendari per aquesta plantilla\n");
		else {
			int i=0;
			while(i<llcalendaris.size() && llcalendaris.get(i).getId_plantilla()<id_plt) ++i;
			if(llcalendaris.get(i).getId_plantilla()==id_plt) llcalendaris.remove(i);
			else System.out.println("No existeix cap calendari per aquesta plantilla\n");
		}
	}
	
	
	public static Calendari consultar_calendari(int id_plt) {
		if(llcalendaris.isEmpty()) System.out.println("No existeix cap calendari per aquesta plantilla\n");
		else {
			int i=0;
			while(i<llcalendaris.size() && llcalendaris.get(i).getId_plantilla() <id_plt) ++i;
			if(i<llcalendaris.size() && llcalendaris.get(i).getId_plantilla()==id_plt) return llcalendaris.get(i);
			else System.out.println("No existeix cap calendari per aquesta plantilla\n");
		}
		return null;
	}
	
	public static void afegir_torn(Torn t, int id_plt)  {
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
	
	public static Torn torn_data(GregorianCalendar d_i, Calendari cl) {
		int i=0;
		while(i<cl.getTorns().size() && cl.getTorns().get(i).getData_inici().before(d_i)) ++i;
		if(i<cl.getTorns().size() && cl.getTorns().get(i).getData_inici().equals(d_i)) return cl.getTorns().get(i);
		else System.out.println("No existeix el torn");
		return null;
	}
	
	public static void modificar_torn(Torn t, Torn t_nou, Calendari cl)  {
			//comprovem si el torn nou té data diferent, si es així afegim el torn modificat i eliminem el torn anterior
			if (!t.getData_inici().equals(t_nou.getData_inici()) || !t.getData_fi().equals(t_nou.getData_fi())) {
				int pos = posicio_torn_nou(t,t_nou,cl.getTorns());
				if(pos!=-1){
					cl.getTorns().add(pos,t_nou);
					eliminar_torn_data(t.getData_inici(),cl);
				}
			}
			//sino modifica la data només canviem els atributs 
			else {
				if (t.getN_min_doc()!=t_nou.getN_min_doc()) t.setN_min_doc(t_nou.getN_min_doc());
				if (t.getDoc_assignats()!=t_nou.getDoc_assignats()) t.setDoc_assignats(t_nou.getDoc_assignats());
				if (t.getPercent_sou()!=t_nou.getPercent_sou()) t.setPercent_sou(t_nou.getPercent_sou());
			}
	}
	
	public static int posicio_torn_nou(Torn t, Torn t_nou, ArrayList<Torn> torns) {
		GregorianCalendar d_inici = t_nou.getData_inici();
		GregorianCalendar d_fi = t_nou.getData_fi();
		int i=0;
		if(torns.get(i).equals(t)) ++i;
		while (i!=-1 && i<torns.size() && torns.get(i).getData_inici().before(d_fi)) {
			if (d_inici.before(torns.get(i).getData_inici()) || (d_inici.before(torns.get(i).getData_fi()))){
				System.out.println("El torn es solapa amb un altre torn ja existent");
				i=-1;
			}
			else ++i;
			if(i!=-1 && i<torns.size() && torns.get(i).equals(t)) ++i;
		}
		return i;
	}
	
	
	public static void eliminar_torn_data(GregorianCalendar d_i, Calendari cl) {
		int i=0;
		while(i<cl.getTorns().size() && d_i.before(cl.getTorns().get(i).getData_inici())) ++i;
		if(d_i.equals(cl.getTorns().get(i).getData_inici())) cl.getTorns().remove(i); 
	}
	
	
	
	public static int posicio_torn(Torn t, ArrayList<Torn> torns) {
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
	
	public static boolean mateix_torn(Torn t1, Torn t2) {
		if(t1.getData_inici().compareTo(t2.getData_inici())!=0 || t1.getData_fi().compareTo(t2.getData_fi())!=0 || t1.getN_min_doc()!=t2.getN_min_doc() || t1.getDoc_assignats()!=t2.getDoc_assignats() || t1.getPercent_sou()!=t2.getPercent_sou()) {
			return false;
		}
		return true;
	}
	

	
}