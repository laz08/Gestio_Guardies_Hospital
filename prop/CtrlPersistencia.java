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