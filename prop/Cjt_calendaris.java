package prop;

import java.util.ArrayList;

public class Cjt_calendaris {
	
	private static ArrayList<Calendari> calendaris = new ArrayList<Calendari>();
	
	public Cjt_calendaris() {
	
	}
	
	public ArrayList<Calendari> get_llista_calendaris() {
		return calendaris;
	}
	
	public void afegir_calendari(Calendari c) {
		if (calendaris.isEmpty()) calendaris.add(c);
		else {
			int i=0;
			while(calendaris.get(i).getId()<c.getId()) ++i;
			if(calendaris.get(i).getId()==c.getId()) System.out.println("El calendari amb aquest id ja existeix\n");
			else {
				Calendari aux;
				for(int j=i; j<calendaris.size(); j++) {
					aux = calendaris.get(i);
					calendaris.add(c);
					c=aux;
				}
				calendaris.add(c);
			}
		}
	}
	
	public void eliminar_calendari(int id) {
		int i=0;
		while(calendaris.get(i).getId()<id) ++i;
		if(calendaris.get(i).getId()==id) calendaris.remove(i);
		else System.out.println("No existeix cap calendari amb aquest id\n");
	}
	
	public Calendari consultar_calendari(int id) {
		int i=0;
		while(calendaris.get(i).getId()<id) ++i;
		if(calendaris.get(i).getId()==id) return calendaris.get(i);
		else System.out.println("No existeix cap calendari amb aquest id\n");
		return null;
	}
}