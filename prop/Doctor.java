package prop;


public class Doctor {
	private String dni;
	private String correu;
	private int telefon;
	private String nom;
    private String cognom1;
    private String cognom2;
	private int sou;
	private boolean actiu;
	
	public Doctor(){
<<<<<<< HEAD
		int a;
=======
		
>>>>>>> 866e95ac90529616f1234859b9902c1d518682d7
	}
	
	public Doctor(String d) {
		dni = d;
	}
	
	public String getdni() {
		return dni;
	}

	public void setdni(String d) {
		dni = d;
	}

	public int getTelefon() {
		return telefon;
	}

	public void setTelefon(int telf) {
		telefon = telf;
	}

	public String getCorreu() {
		return correu;
	}

	public void setCorreu(String email) {
		correu = email;
	}

	public int getSou() {
		return sou;
	}

	public void setSou(int s) {
	sou = s;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nm) {
		nom = nm;
	}

    public String getCognom1(){ return cognom1; }

    public void setCognom1(String cg1){
        cognom1 = cg1;
    }

    public String getCognom2(){ return cognom2; }

    public void setCognom2(String cg2){
        cognom2 = cg2;
    }

	public boolean isActiu() {
		return actiu;
	}

	public void setActiu(boolean act) {
		actiu = act;
	}
}
