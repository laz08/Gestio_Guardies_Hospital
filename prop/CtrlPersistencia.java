package prop;

import java.io.*;

public class CtrlPersistencia {

    /**
     * Guardar el contingut content al fitxer f
     * @param content
     * @param f
     */
	public static void guardar(String content, File f) {
		File file = f;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * Retorna un string resultant de la lectura del fitxer f
     * @param f
     * @return
     */
	public static String carregar(File f) {
		BufferedReader br = null;
 		String line = "";
 		String total = "";
		try {

			br = new BufferedReader(new FileReader(f.getAbsolutePath()));
 
			while ((line = br.readLine()) != null) {
				total += line + " ";
			}
			total += "\n";
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
 		return total;
	}
}