package prop;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class LlistatErrorRestriccio extends PanelLlistatError{
	private CtrlVistaRestriccio ctrlvr;
	private JLabel textres = new JLabel("LLISTAT DE RESTRICCIONS", SwingConstants.CENTER);
	
	public LlistatErrorRestriccio(CtrlVistaRestriccio cvr) {
		ctrlvr = cvr;
        error.setText(" ");
		remove(scroll1);
		add(textres, BorderLayout.NORTH);
		add(scroll1);

        setPreferredSize(new Dimension(343, 250));
        setMaximumSize(new Dimension(343, 250));
	}

    /**
     * Actualitza el llistat de restriccions (i tradueix les de torns per algo més amigable)
     * @param arrayList
     */
	public void actualitza(ArrayList<String> arrayList) {
        model1.clear();
		for (int i = 0; i < arrayList.size(); ++i) {
            //Traducció de la restricció per torns i dia
            String r_trad = tradueixRestriccio(arrayList.get(i));
            model1.addElement(r_trad);
		}
	}

    private String tradueixRestriccio(String r){
        String r_trad = "";
        if(r.charAt(0) == 'H'){
            //Es per torn
            r_trad = "H";
            for(int j = 1; j < r.length(); ++j){
                if(r.charAt(j) == '5'){
                    r_trad += "MATI";
                }
                else if(r.charAt(j) == '1'){
                    ++j;
                    r_trad += "TARDA";
                }
                else if(r.charAt(j) == '2'){
                    ++j;
                    r_trad += "NIT";
                }
                else r_trad += r.charAt(j);
            }
            return r_trad;
        }
        else{
            //Es per dia
            r_trad = "D";

            for (int j = 1; j < r.length(); ++j){
                char a = r.charAt(j);
                if(a == '-'){
                    //Comença un mes
                    ++j;
                    r_trad += " ";
                    a = r.charAt(j);
                    if(a == '0'){
                        //GENER
                        r_trad += "GENER";
                    }
                    else if(a == '1'){
                        //FEBRER, NOVEMBRE O DESEMBRE
                        ++j;
                        a = r.charAt(j);
                        if(a == '0'){
                            //NOVEMBRE
                            r_trad += "NOVEMBRE";
                        }
                        else if(a == '1'){
                            //DESEMBRE
                            r_trad += "DESEMBRE";
                        }
                        else{
                            r_trad += "FEBRER";
                            --j;
                        }
                    }
                    else if (a == '2'){
                        //MARÇ
                        r_trad += "MARÇ";
                    }
                    else if (a == '3'){
                        //ABRIL
                        r_trad += "ABRIL";
                    }
                    else if (a == '4'){
                        //MAIG
                        r_trad += "MAIG";
                    }
                    else if (a == '5'){
                        //JUNY
                        r_trad += "JUNY";
                    }
                    else if (a == '6'){
                        //JULIOL
                        r_trad += "JULIOL";
                    }
                    else if (a == '7'){
                        //AGOST
                        r_trad += "AGOST";
                    }
                    else if (a == '8'){
                        //SETEMBRE
                        r_trad += "SETEMBRE";
                    }
                    else if (a == '9'){
                        //OCTUBRE
                        r_trad += "OCTUBRE";
                    }
                }
                else
                    r_trad += a;
            }


            return r_trad;
        }

    }


}
