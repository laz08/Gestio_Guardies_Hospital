package prop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CtrlPersistencia {

	public static void guardar(String content, String arxiu) {
		Path path = Paths.get(System.getProperty("user.dir"), arxiu);
		File file = new File(path.toString());

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			System.out.println("Fet");
			bw.close();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String carregar(String arxiu) {
		Path path = Paths.get(System.getProperty("user.dir"), arxiu); 
		BufferedReader br = null;
 		String line = "";
 		String total = "";
		try {

			br = new BufferedReader(new FileReader(path.toString()));
 
			while ((line = br.readLine()) != null) {
				total += line + " ";
			}
			total += "\n";
 			System.out.println(total);
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